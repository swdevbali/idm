/**
 * 
 */
package org.openforis.idm.model.expression;

import org.junit.Assert;
import org.junit.Test;
import org.openforis.idm.model.Coordinate;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.Record;

/**
 * @author M. Togna
 * 
 */
public class LookupFunctionTest extends AbstractExpressionTest {

	public static Coordinate TEST_COORDINATE = Coordinate.parseCoordinate("SRID=EPSG:21035;POINT(805750 9333820)");

	@Test
	public void testLookupFunction() throws InvalidExpressionException {
		Record record = getRecord();
		Entity rootEntity = record.getRootEntity();
		String expr = "idm:lookup('sampling_design', 'plot_centre', 'id', 1)";

		DefaultValueExpression expression = getRecordContext().getExpressionFactory().createDefaultValueExpression(expr);
		Object object = expression.evaluate(rootEntity, null);
		Assert.assertEquals(TEST_COORDINATE, object);

		expr = "idm:lookup('sampling_design', 'plot_centre', 'id')";
		expression = getRecordContext().getExpressionFactory().createDefaultValueExpression(expr);
		object = expression.evaluate(rootEntity, null);
		Assert.assertEquals(TEST_COORDINATE, object);
	}

}
