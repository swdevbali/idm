/**
 * 
 */
package org.openforis.idm.metamodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openforis.idm.model.Node;
import org.openforis.idm.model.TaxonAttribute;
import org.openforis.idm.model.TaxonOccurrence;
import org.openforis.idm.model.Value;

/**
 * @author G. Miceli
 * @author M. Togna
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"name", "relevantExpression","required", "requiredExpression", "multiple", "minCount", "maxCount", "sinceVersionName", "deprecatedVersionName",
		"labels", "prompts", "descriptions", "attributeDefaults", "checks"})
public class TaxonAttributeDefinition extends AttributeDefinition {

	private static final long serialVersionUID = 1L;

	private static final FieldDefinition<?>[] FIELD_DEFINITIONS = {
			new FieldDefinition<String>("code", "c", "code", String.class), 
			new FieldDefinition<String>("scientific_name", "s", "name", String.class), 
			new FieldDefinition<String>("vernacular_name", "v", "vn", String.class), 
			new FieldDefinition<String>("language_code", "l", "lang", String.class), 
			new FieldDefinition<String>("language_variety", "lv", "lang_var", String.class)
	};
	
	@Override
	public Node<?> createNode() {
		return new TaxonAttribute(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TaxonOccurrence createValue(String string) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<FieldDefinition<?>> getFieldDefinitions() {
		return Collections.unmodifiableList(Arrays.asList(FIELD_DEFINITIONS));
	}

	@Override
	public Class<? extends Value> getValueType() {
		return TaxonOccurrence.class;
	}
}
