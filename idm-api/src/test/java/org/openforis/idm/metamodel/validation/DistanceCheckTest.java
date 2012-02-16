/**
 * 
 */
package org.openforis.idm.metamodel.validation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openforis.idm.model.Coordinate;
import org.openforis.idm.model.CoordinateAttribute;

/**
 * @author M. Togna
 * 
 */
public class DistanceCheckTest extends ValidatorTest {

	@Test
	public void testValidMaxDistance() {
		String coordStr = "SRID=EPSG:21035;POINT(805750 9333820)";
		Coordinate coord = Coordinate.parseCoordinate(coordStr);
		CoordinateAttribute vehicleLocation = cluster.addValue("vehicle_location", coord);
		ValidationResults results = vehicleLocation.validate();
		Assert.assertFalse(containsDistanceCheck(results.getErrors()));
	}

	@Test
	public void testErrorMaxDistance() {
		String coordStr = "SRID=EPSG:21035;POINT(915750 9333820)";
		Coordinate coord = Coordinate.parseCoordinate(coordStr);
		CoordinateAttribute vehicleLocation = cluster.addValue("vehicle_location", coord);
		ValidationResults results = vehicleLocation.validate();
		Assert.assertTrue(containsDistanceCheck(results.getErrors()));
	}

	@Test
	public void testWarnMaxDistance() {
		String coordStr = "SRID=EPSG:21035;POINT(885750 9333820)";
		Coordinate coord = Coordinate.parseCoordinate(coordStr);
		CoordinateAttribute vehicleLocation = cluster.addValue("vehicle_location", coord);
		ValidationResults results = vehicleLocation.validate();
		Assert.assertFalse(containsDistanceCheck(results.getErrors()));
		Assert.assertTrue(containsDistanceCheck(results.getWarnings()));
	}

	private boolean containsDistanceCheck(List<ValidationResult> errors) {
		for (ValidationResult result : errors) {
			if (result.getValidator() instanceof DistanceCheck) {
				return true;
			}
		}
		return false;
	}

}
