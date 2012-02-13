/**
 * 
 */
package org.openforis.idm.validation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openforis.idm.metamodel.IdmInterpretationError;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.Node;
import org.openforis.idm.model.Record;
import org.openforis.idm.model.RecordContext;
import org.openforis.idm.model.expression.ExpressionFactory;
import org.openforis.idm.model.expression.InvalidPathException;
import org.openforis.idm.model.expression.RequiredExpression;

/**
 * @author M. Togna
 * @author G. Miceli
 */
public class MinCountValidator implements Validator<Entity> {

	private NodeDefinition nodeDefinition;

	public MinCountValidator(NodeDefinition nodeDefinition) {
		this.nodeDefinition = nodeDefinition;
	}

	public NodeDefinition getNodeDefinition() {
		return nodeDefinition;
	}
	
	@Override
	public boolean validate(Entity parent) {
		String name = nodeDefinition.getName();
		int minCount = getEffectiveMinCount(parent);
		if ( minCount == 0 ) {
			return true;
		} else {
			int nonEmptyCount = 0;
			List<Node<?>> childNodes = parent.getAll(name);
			for ( Node<?> child : childNodes ) {
				if ( !child.isEmpty() ) {
					nonEmptyCount++;;
					if ( nonEmptyCount >= minCount ) {
						return true;
					}
				}
			}
			return false;
		}
	}

	private int getEffectiveMinCount(Entity parent) {
		Integer minCount = nodeDefinition.getMinCount();
		String requiredExpression = nodeDefinition.getRequiredExpression();
		// requiredExpression is only considered if minCount and required are not set
		if ( minCount==null && StringUtils.isNotBlank(requiredExpression) ) {
			Record record = parent.getRecord();
			RecordContext context = record.getContext();
			ExpressionFactory expressionFactory = context.getExpressionFactory();
			RequiredExpression expr = expressionFactory.createRequiredExpression(requiredExpression);
			try {
				return expr.evaluate(parent) ? 1 : 0;
			} catch (InvalidPathException e) {
				throw new IdmInterpretationError("Error evaluating required expression", e);
			}
		} else {
			return minCount == null ? 0 : minCount;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("('");
		sb.append(nodeDefinition.getName());
		sb.append("')");
		return sb.toString();
	}
}