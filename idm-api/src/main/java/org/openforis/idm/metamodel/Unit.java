/**
 * 
 */
package org.openforis.idm.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;*/
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openforis.idm.util.CollectionUtil;

/**
 * @author G. Miceli
 * @author M. Togna
 * @author K. Waga
 */
//@XmlAccessorType(XmlAccessType.FIELD)
@Order(attributes = { "id", "name", "dimension", "conversionFactor" }, elements = { "labels", "abbreviations" })
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Attribute(name = "id")
	private int id;

	@Attribute(name = "name")
	private String name;

	@Attribute(name = "dimension")
	private String dimension;

	@Attribute(name = "conversionFactor", required = false)
	private Float conversionFactor;

	/*@XmlElement(name = "label", type = LanguageSpecificText.class)
	private List<LanguageSpecificText> labels;*/
	@ElementList(entry="label", type = LanguageSpecificText.class, required=false)
	private List<LanguageSpecificText> labels;

	/*@XmlElement(name = "abbreviation", type = LanguageSpecificText.class)
	private List<LanguageSpecificText> abbreviations;*/
	@ElementList(entry="abbreviation", type = LanguageSpecificText.class, required=false)
	private List<LanguageSpecificText> abbreviations;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public Number getConversionFactor() {
		return this.conversionFactor;
	}

	public void setConversionFactor(Float conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	public List<LanguageSpecificText> getLabels() {
		return CollectionUtil.unmodifiableList(labels);
	}
	
	public String getLabel(String language) {
		if (labels != null ) {
			return LanguageSpecificText.getText(labels, language);
		} else {
			return null;
		}
	}
	
	public void addLabel(LanguageSpecificText label) {
		if ( labels == null ) {
			labels = new ArrayList<LanguageSpecificText>();
		}
		labels.add(label);
	}

	public void setLabel(String language, String description) {
		if ( labels == null ) {
			labels = new ArrayList<LanguageSpecificText>();
		}
		LanguageSpecificText.setText(labels, language, description);
	}
	
	public void removeLabel(String language) {
		LanguageSpecificText.remove(labels, language);
	}

	public List<LanguageSpecificText> getAbbreviations() {
		return CollectionUtil.unmodifiableList(abbreviations);
	}

	public String getAbbreviation(String language) {
		if (abbreviations != null ) {
			return LanguageSpecificText.getText(abbreviations, language);
		} else {
			return null;
		}
	}
	
	public void addAbbreviation(LanguageSpecificText label) {
		if ( abbreviations == null ) {
			abbreviations = new ArrayList<LanguageSpecificText>();
		}
		abbreviations.add(label);
	}

	public void setAbbreviation(String language, String description) {
		if ( abbreviations == null ) {
			abbreviations = new ArrayList<LanguageSpecificText>();
		}
		LanguageSpecificText.setText(abbreviations, language, description);
	}
	
	public void removeAbbreviation(String language) {
		LanguageSpecificText.remove(abbreviations, language);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("name", name)
			.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviations == null) ? 0 : abbreviations.hashCode());
		result = prime * result + ((conversionFactor == null) ? 0 : conversionFactor.hashCode());
		result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
		result = prime * result + id;
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (abbreviations == null) {
			if (other.abbreviations != null)
				return false;
		} else if (!abbreviations.equals(other.abbreviations))
			return false;
		if (conversionFactor == null) {
			if (other.conversionFactor != null)
				return false;
		} else if (!conversionFactor.equals(other.conversionFactor))
			return false;
		if (dimension == null) {
			if (other.dimension != null)
				return false;
		} else if (!dimension.equals(other.dimension))
			return false;
		if (id != other.id)
			return false;
		if (labels == null) {
			if (other.labels != null)
				return false;
		} else if (!labels.equals(other.labels))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
