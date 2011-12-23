/**
 * 
 */
package org.openforis.idm.metamodel;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author G. Miceli
 * @author M. Togna
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "labels", "descriptions",  "wellKnownText" })
@XmlRootElement(name = "spatialReferenceSystem")
public class SpatialReferenceSystem {

	@XmlAttribute(name = "srid")
	private String id;

	@XmlElement(name = "label", type = LanguageSpecificText.class)
	private List<LanguageSpecificText> labels;

	@XmlElement(name = "description", type = LanguageSpecificText.class)
	private List<LanguageSpecificText> descriptions;

	@XmlElement(name = "wkt")
//	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private String wellKnownText;

	public String getId() {
		return this.id;
	}

	public List<LanguageSpecificText> getLabels() {
		return Collections.unmodifiableList(this.labels);
	}

	public List<LanguageSpecificText> getDescriptions() {
		return Collections.unmodifiableList(this.descriptions);
	}

	public String getWellKnownText() {
		return this.wellKnownText;
	}
//	
//	private static class CDATAAdapter extends XmlAdapter<String, String> {
//
//		@Override
//		public String marshal(String v) throws Exception {
//			return v==null ? null : "<![CDATA[" + v + "]]>";
//		}
//
//		@Override
//		public String unmarshal(String v) throws Exception {
//			return v;
//		}
//	}
}
