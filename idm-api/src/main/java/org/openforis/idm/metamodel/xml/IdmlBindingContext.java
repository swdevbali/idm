package org.openforis.idm.metamodel.xml;

import org.openforis.idm.metamodel.DefaultSurveyContext;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.metamodel.SurveyContext;
import org.openforis.idm.metamodel.xml.internal.ConfigurationXmlAdapter;
import org.openforis.idm.metamodel.xml.internal.DefaultConfigurationAdapter;

/**
 * @author G. Miceli
 */
public class IdmlBindingContext {
	//private final JAXBContext surveyJaxbContext;
	protected static final ConfigurationXmlAdapter DEFAULT_CONFIG_ADAPTER;
	private Class<? extends Survey> surveyClass;
	private SurveyContext surveyContext;
	
	private Class<? extends ConfigurationWrapperConverter<?>> configurationWrapperConverterClass;

	public IdmlBindingContext() {
		this(new DefaultSurveyContext());
	}
	
	public IdmlBindingContext(SurveyContext surveyContext) {
		this(Survey.class, surveyContext, null);
	}
	
	public IdmlBindingContext(Class<? extends Survey> surveyClass, SurveyContext surveyContext, Class<? extends ConfigurationWrapperConverter<?>> configurationWrapperConverterClass) {
//		try {
			this.surveyClass = surveyClass;
			//this.surveyJaxbContext = JAXBContext.newInstance(surveyClass);
			this.surveyContext = surveyContext;
			this.configurationWrapperConverterClass = configurationWrapperConverterClass;
//		} catch (JAXBException e) {
//			throw new RuntimeException(e);
//		}
	}
	
	static {
		DEFAULT_CONFIG_ADAPTER = new ConfigurationXmlAdapter(DefaultConfigurationAdapter.getInstance());
	}
	
//	public ConfigurationAdapter<? extends Configuration> getConfigurationAdapter() {
//		return configurationAdapter;
//	}
//
//	public void setConfigurationAdapter(ConfigurationAdapter<? extends Configuration> configurationAdapter) {
//		this.configurationAdapter = configurationAdapter;
//	}
	
	
	
	public SurveyMarshaller createSurveyMarshaller(){
//		try {
			/*
			Marshaller marshaller = surveyJaxbContext.createMarshaller();
			if ( configurationAdapter == null ) {
				marshaller.setAdapter(DEFAULT_CONFIG_ADAPTER);
			} else {
				marshaller.setAdapter(new ConfigurationXmlAdapter(configurationAdapter));
			}
			return new SurveyMarshaller(marshaller);
			*/
			return new SurveyMarshaller();
//		} catch (JAXBException e) {
//			throw new RuntimeException(e);
//		}
	}
//	
//	public SurveyUnmarshaller createSurveyUnmarshaller(){
//		try {
//			Unmarshaller unmarshaller = surveyJaxbContext.createUnmarshaller();
//			if ( configurationAdapter == null ) {
//				unmarshaller.setAdapter(DEFAULT_CONFIG_ADAPTER);
//			} else {
//				unmarshaller.setAdapter(new ConfigurationXmlAdapter(configurationAdapter));
//			}
//			return new SurveyUnmarshaller(surveyClass, surveyContext);
//		} catch (JAXBException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public SurveyUnmarshaller createSurveyUnmarshaller(){
		return new SurveyUnmarshaller(surveyClass, surveyContext, configurationWrapperConverterClass);
	}

/*	
	static JAXBContext getInstance() {
		return JAXB_CONTEXT;
	}
	
	static ConfigurationXmlAdapter getDefaultConfigurationAdapter() {
		return DEFAULT_CONFIG_ADAPTER;
	}
*/	
}
