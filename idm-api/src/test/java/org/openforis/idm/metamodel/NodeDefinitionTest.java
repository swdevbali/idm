package org.openforis.idm.metamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * @author G. Miceli
 */
public class NodeDefinitionTest {

	@Test
	public void testGetPathAtRoot() {
		NodeDefinition mock = mock(NodeDefinition.class);
		when(mock.getName()).thenReturn("cluster");
		when(mock.getPath()).thenCallRealMethod();

		String path = mock.getPath();
		assertEquals("/cluster", path);
	}

	@Test
	public void testGetPathAtSecondLevel() {
		EntityDefinition parentMock = mock(EntityDefinition.class);
		NodeDefinition mock = mock(NodeDefinition.class);
		when(parentMock.getName()).thenReturn("cluster");
		when(mock.getName()).thenReturn("plot");
		when(mock.getParentDefinition()).thenReturn(parentMock);
		when(mock.getPath()).thenCallRealMethod();

		String path = mock.getPath();
		assertEquals("/cluster/plot", path);
	}
	
	@Test
	public void testRootEntityDefinitionIsMultiple() {
		NodeDefinition defn = new EntityDefinition();
		defn.setSchema(new Schema());
		assertTrue(defn.isMultiple());
	}
}
