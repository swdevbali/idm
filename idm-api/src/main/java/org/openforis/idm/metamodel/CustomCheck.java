/**
 * 
 */
package org.openforis.idm.metamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openforis.idm.model.Attribute;
import org.openforis.idm.model.expression.CheckExpression;
import org.openforis.idm.model.expression.InvalidPathException;
import org.openforis.idm.validation.ValidationContext;

/**
 * @author G. Miceli
 * @author M. Togna
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class CustomCheck extends Check {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "expr")
	private String expression;

	public String getExpression() {
		return this.expression;
	}

	public boolean execute(ValidationContext validationContext, Attribute<? extends AttributeDefinition, ?> attribute) throws InvalidPathException {
		CheckExpression checkExpression = validationContext.getExpressionFactory().createCheckExpression(getExpression());
		boolean b = checkExpression.evaluate(attribute);
		return b;
	}
}
