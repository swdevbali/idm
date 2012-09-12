package org.openforis.idm.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Workaround for JAXB since @XmlAnyElement, @XmlElementWrapper and @XmlJavaTypeAdapter 
 * wouldn't play nice together
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@Order
public class ConfigurationWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@XmlAnyElement
//	@ElementList(inline = true, required = false)
//	@Convert(ConfigurationXmlAdapter.class)
	List<Configuration> list;

	public void addConfiguration(Configuration config) {
		if ( list == null ) {
			list = new ArrayList<Configuration>();
		}
		list.add(config);
	}
	
	public void setConfiguration(int index, Configuration config) {
		list.set(index, config);
	}
	
	void removeConfiguration(Configuration config) {
		list.remove(config);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		ConfigurationWrapper other = (ConfigurationWrapper) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
}
