package org.openforis.idm;

import java.io.InputStream;
import java.net.URL;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openforis.idm.metamodel.DefaultSurveyContext;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.metamodel.SurveyContext;
import org.openforis.idm.metamodel.xml.SurveyUnmarshaller;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.Record;

/**
 * @author G. Miceli
 * @author M. Togna
 */
public abstract class AbstractTest {

	protected static Survey survey;
	protected Entity cluster;
	protected Entity household;
	protected Record record;

	@BeforeClass
	public static void setUp() throws Exception {
		URL idm = ClassLoader.getSystemResource("test.idm.xml");
		InputStream is = idm.openStream();
		SurveyContext surveyContext = new DefaultSurveyContext();
		SurveyUnmarshaller parser = new SurveyUnmarshaller(surveyContext);
		survey = parser.unmarshal(is);
	}

	@Before
	public void createCluster() {
		this.record = new Record(survey, "2.0");
		this.cluster = record.createRootEntity("cluster");
		Record record2 = new Record(survey, "2.0");
		this.household = record2.createRootEntity("household");
	}
}
