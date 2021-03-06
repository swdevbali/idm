/**
 * 
 */
package org.openforis.idm.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;*/
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Transient;

import org.openforis.idm.metamodel.xml.internal.XmlInit;
import org.openforis.idm.metamodel.xml.internal.XmlParent;
import org.openforis.idm.util.CollectionUtil;

/**
 * @author G. Miceli
 * @author M. Togna
 * @author K. Waga
 */
//@XmlAccessorType(XmlAccessType.FIELD)
@Order(elements = { "entity" })
public class Schema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*@XmlElement(name = "entity", type = EntityDefinition.class)
	private List<EntityDefinition> rootEntityDefinitions;*/
	@ElementList(inline=true, entry="entity", type=EntityDefinition.class)
	private List<EntityDefinition> rootEntityDefinitions;

	@Transient
	private Map<String, NodeDefinition> definitionsByPath;

	@Transient
	private Map<Integer, NodeDefinition> definitionsById;
	
	@Transient
	@XmlParent
	private Survey survey;

	@Transient
	private int lastDefinitionId;
	
	public Schema() {
		definitionsByPath = new HashMap<String, NodeDefinition>(); 
		definitionsById = new HashMap<Integer, NodeDefinition>();
		lastDefinitionId = 0;
	}
	
	public Survey getSurvey() {
		return survey;
	}
	
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	public NodeDefinition getByPath(String absolutePath) {
		return definitionsByPath.get(absolutePath);
	}
	
	public NodeDefinition getById(int id) {
		return definitionsById.get(id);
	}
	
	@XmlInit
	void reindexDefinitions() {
		 definitionsById.clear();
		 definitionsByPath.clear();
		 for (EntityDefinition entityDefn : getRootEntityDefinitions()) {
			entityDefn.traverse(new NodeDefinitionVisitor() {
				@Override
				public void visit(NodeDefinition definition) {
					indexByPath(definition);
					indexById(definition);
				}
			});
		}
	}

	void indexByPath(NodeDefinition definition) {
		String path = definition.getPath();
		definitionsByPath.put(path, definition);
	}

	void indexById(NodeDefinition definition) {
		Integer id = definition.getId();
		if ( id != null ) {
			definitionsById.put(id, definition);
		}
	}

	public Set<String> getDefinedPaths() {
		return Collections.unmodifiableSet(definitionsByPath.keySet());
	}

	public Collection<NodeDefinition> getAllDefinitions() {
		return Collections.unmodifiableCollection(definitionsByPath.values());
	}
	
	public List<EntityDefinition> getRootEntityDefinitions() {
		return CollectionUtil.unmodifiableList(rootEntityDefinitions);
	}

	public void addRootEntityDefinition(EntityDefinition defn) {
		defn.setId(nextNodeDefinitionId());
		if ( rootEntityDefinitions == null) {
			rootEntityDefinitions = new ArrayList<EntityDefinition>();
		}
		rootEntityDefinitions.add(defn);
		defn.setSchema(this);
		indexById(defn);
		indexByPath(defn);
	}
	
	protected int nextNodeDefinitionId() {
		if ( lastDefinitionId == 0 ) {
			lastDefinitionId = calculateLastUsedDefinitionId();
		}
		return lastDefinitionId++;
	}
	
	protected int calculateLastUsedDefinitionId() {
		int result = 0;
		Stack<NodeDefinition> stack = new Stack<NodeDefinition>();
		List<EntityDefinition> rootEntityDefinitions = getRootEntityDefinitions();
		stack.addAll(rootEntityDefinitions);
		while ( ! stack.isEmpty() ) {
			NodeDefinition nodeDefn = stack.pop();
			result = Math.max(result, nodeDefn.getId());
			if ( nodeDefn instanceof EntityDefinition ) {
				List<NodeDefinition> childDefinitions = ((EntityDefinition) nodeDefn).getChildDefinitions();
				stack.addAll(childDefinitions);
			}
		}
		return result;
	}

	public void removeRootEntityDefinition(String name) {
		EntityDefinition defn = getRootEntityDefinition(name);
		rootEntityDefinitions.remove(defn);
		reindexDefinitions();
	}
	
	public EntityDefinition getRootEntityDefinition(String name) {
		return (EntityDefinition) getByPath("/"+name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rootEntityDefinitions == null) ? 0 : rootEntityDefinitions.hashCode());
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
		Schema other = (Schema) obj;
		if (rootEntityDefinitions == null) {
			if (other.rootEntityDefinitions != null)
				return false;
		} else if (!rootEntityDefinitions.equals(other.rootEntityDefinitions))
			return false;
		return true;
	}
	
	
}
