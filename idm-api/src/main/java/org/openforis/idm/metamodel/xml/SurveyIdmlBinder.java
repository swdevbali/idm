package org.openforis.idm.metamodel.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.openforis.idm.metamodel.DefaultSurveyContext;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.metamodel.SurveyContext;
import org.openforis.idm.metamodel.xml.internal.marshal.SurveyPullReader;
import org.openforis.idm.metamodel.xml.internal.unmarshal.SurveySerializer;

/**
 * Load a Survey object from IDML
 * 
 * @author G. Miceli
 */
public class SurveyIdmlBinder {
	private SurveyContext surveyContext;
	private List<ApplicationOptionsBinder<?>> optionsBinders;

	public SurveyIdmlBinder(SurveyContext surveyContext) {
		this.surveyContext = surveyContext;
		this.optionsBinders = new ArrayList<ApplicationOptionsBinder<?>>();
	}

	// TODO Remove
	public static void main(String[] args) {
		try {
			File f = new File("../idm-test/src/main/resources/test.idm.xml");
			InputStream is = new FileInputStream(f);
			SurveyContext ctx = new DefaultSurveyContext();
			
			SurveyIdmlBinder unmarshaller = new SurveyIdmlBinder(ctx); 
			unmarshaller.unmarshal(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addApplicationOptionsBinder(ApplicationOptionsBinder<?> binder) {
		optionsBinders.add(binder);
	}

	/**
	 * 
	 * @param type
	 * @return the first binder which supports the specified type, or null
	 * if none found
	 */
	public ApplicationOptionsBinder<?> getApplicationOptionsBinder(String type) {
		for (ApplicationOptionsBinder<?> binder : optionsBinders) {
			if ( binder.isSupported(type) ) {
				return binder;
			}
		}
		return null;
	}
	
	public SurveyContext getSurveyContext() {
		return surveyContext;
	}
	
	synchronized
	public void marshal(InputStream is, Survey survey) throws XmlParseException, IOException {
		SurveySerializer ser = new SurveySerializer(this);
		// TODO implement
		throw new UnsupportedOperationException();
	}
		
	synchronized
	public Survey unmarshal(InputStream is) throws XmlParseException, IOException {
		SurveyPullReader surveyReader = new SurveyPullReader(this);
		surveyReader.parse(is, "UTF-8");
		return surveyReader.getSurvey();
	}	
}