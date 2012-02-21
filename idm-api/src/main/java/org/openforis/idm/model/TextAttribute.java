package org.openforis.idm.model;

import org.openforis.idm.metamodel.TextAttributeDefinition;

/**
 * @author G. Miceli
 * @author M. Togna
 */
public class TextAttribute extends AtomicAttribute<TextAttributeDefinition, String> {

	public TextAttribute(TextAttributeDefinition definition) {
		super(definition, String.class);
	}

	@Override
	public String createValue(String string) {
		return string;
	}
	
}
