package com.bsplat.system.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = { "com.bsteel.baofc.controller" })
public class SpringWebApplicationConfiguration extends
		WebMvcConfigurationSupport {

	private static final String MESSAGES_SOURCE = "messages";
	private static final String VIEWS_PREF = "/WEB-INF/jsp/";
	private static final String VIEWS_SUFF = ".jsp";
	
	//Bean ID constant variable
	public static final String BEAN_ID_URL_MANAGER = "urlmanager";
	public static final String BEAN_ID_OWNER_VALIDATOR = "ownerValidator";

	/**
	 * The form-based controllers within this application provide @RequestMapping
	 * - annotations at the type level for path mapping URLs and @RequestMapping
	 * - at the method level for request type mappings (e.g., GET and POST). -
	 * In contrast, ClinicController - which is not form-based - provides - @RequestMapping
	 * only at the method level for path mapping URLs. - -
	 * DefaultAnnotationHandlerMapping is driven by these annotations and is -
	 * enabled by default with Java 5+.
	 * 
	 * This bean processes annotated handler methods, applying
	 * PetClinic-specific PropertyEditors - for request parameter binding. It
	 * overrides the default AnnotationMethodHandlerAdapter.
	 */

	/**
	 * <bean class=
	 * "org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter"
	 * > <property name="messageConverters"> <util:list id="beanList"> <ref
	 * bean="mappingJacksonHttpMessageConverter"/> </util:list> </property>
	 * <property name="webBindingInitializer"> <bean class=
	 * "org.springframework.samples.petclinic.web.ClinicBindingInitializer"/>
	 * </property> </bean>
	 */
	@Bean
	public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter() {
		AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter = new AnnotationMethodHandlerAdapter();
		// annotationMethodHandlerAdapter.setMessageConverters(appingJacksonHttpMessageConverter());
//		annotationMethodHandlerAdapter
//				.setWebBindingInitializer(new ClinicBindingInitializer());
		return annotationMethodHandlerAdapter;
	}

	/**
	 * <bean id="mappingJacksonHttpMessageConverter" class=
	 * "org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"
	 * />
	 */
	/*
	@Bean
	public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
		return new MappingJacksonHttpMessageConverter();
	}
	*/
	/**
	 * <!-- - This bean resolves specific types of exceptions to corresponding
	 * logical - view names for error views. The default behaviour of
	 * DispatcherServlet - is to propagate all exceptions to the servlet
	 * container: this will happen - here with all other types of exceptions.
	 * -->
	 */
	/**
	 * <bean class=
	 * "org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
	 * <property name="exceptionMappings"> <props> <prop
	 * key="org.springframework.web.servlet.PageNotFound">pageNotFound</prop>
	 * <prop
	 * key="org.springframework.dao.DataAccessException">dataAccessFailure<
	 * /prop> <prop key="org.springframework.transaction.TransactionException">
	 * dataAccessFailure</prop> </props> </property> </bean>
	 */
	/*
	 * xiaoxinghui comment for exception handler
	 * 
	 * @Bean public SimpleMappingExceptionResolver
	 * simpleMappingExceptionResolver() { SimpleMappingExceptionResolver
	 * simpleMapping = new SimpleMappingExceptionResolver(); Properties pro =
	 * new Properties(); pro.put("org.springframework.web.servlet.PageNotFound",
	 * "pageNotFound"); pro.put("org.springframework.dao.DataAccessException",
	 * "dataAccessFailure");
	 * pro.put("org.springframework.transaction.TransactionException",
	 * "dataAccessFailure"); simpleMapping.setExceptionMappings(pro); return
	 * simpleMapping; }
	 */
	/**
	 * <!-- - This view resolver delegates to the InternalResourceViewResolver
	 * and BeanNameViewResolver, - and uses the requested media type to pick a
	 * matching view. When the media type is 'text/html', - it will delegate to
	 * the InternalResourceViewResolver's JstlView, otherwise to the -
	 * BeanNameViewResolver. Note the use of the expression language to refer to
	 * the contentType - property of the vets view bean, setting it to
	 * 'application/vnd.springsource.samples.petclinic+xml'. -->
	 */
	/**
	 * <bean class=
	 * "org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
	 * <property name="mediaTypes"> <map> <entry key="xml"
	 * value="#{vets.contentType}"/> <entry key="atom"
	 * value="#{visits.contentType}"/> </map> </property> <property name="order"
	 * value="0"/> </bean>
	 */
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver contentNegot = new ContentNegotiatingViewResolver();
		Map<String, String> map = new HashMap<String, String>();
		// map.put("xml", marshallingView().getContentType());
//		map.put("atom", visitsAtomViews().getContentType());
		contentNegot.setMediaTypes(map);
		contentNegot.setOrder(0);
		return contentNegot;
	}

	/**
	 * <!-- - The BeanNameViewResolver is used to pick up the visits view name
	 * (below). - It has the order property set to 2, which means that this will
	 * - be the first view resolver to be used after the delegating content -
	 * negotiating view resolver. -->
	 */

	/**
	 * <bean class="org.springframework.web.servlet.view.BeanNameViewResolver"
	 * p:order="1"/>
	 */
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanName = new BeanNameViewResolver();
		beanName.setOrder(1);
		return beanName;
	}

	/**
	 * <!--
	 * 
	 * - This bean configures the 'prefix' and 'suffix' properties of -
	 * InternalResourceViewResolver, which resolves logical view names -
	 * returned by Controllers. For example, a logical view name of "vets" -
	 * will be mapped to "/WEB-INF/jsp/vets.jsp". -->
	 */

	/**
	 * <bean class=
	 * "org.springframework.web.servlet.view.InternalResourceViewResolver"
	 * p:prefix="/WEB-INF/jsp/" p:suffix=".jsp" p:order="2"/>
	 * */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(VIEWS_PREF);
		resolver.setSuffix(VIEWS_SUFF);
		resolver.setOrder(2);
		return resolver;
	}

	/**
	 * <!-- - The AtomView rendering a Atom feed of the visits -->
	 */
	/**
	 * <bean id="visits"
	 * class="org.springframework.samples.petclinic.web.VisitsAtomView"/>
	 * */
/*	@Bean(name = "visitsAtomView")
	public VisitsAtomView visitsAtomViews() {
		return new VisitsAtomView();
	}
*/
	/**
	 * <bean id="vets"
	 * class="org.springframework.web.servlet.view.xml.MarshallingView">
	 * <property name="contentType"
	 * value="application/vnd.springsource.samples.petclinic+xml"/> <property
	 * name="marshaller" ref="marshaller"/> </bean>
	 * */
	/*
	 * @Bean(name="marshallingView") public MarshallingView marshallingView(){
	 * MarshallingView marshall = new MarshallingView();
	 * marshall.setContentType(
	 * "application/vnd.springsource.samples.petclinic+xml");
	 * //marshall.setMarshaller(marshaller()); return marshall; }
	 *//***
	 * <oxm:jaxb2-marshaller id="marshaller"> <oxm:class-to-be-bound
	 * name="org.springframework.samples.petclinic.Vets"/>
	 * </oxm:jaxb2-marshaller>
	 */
	/*
	 * public Marshaller marshaller(){ Marshaller marshaller = null; try {
	 * JAXBContext jaxb = JAXBContext.newInstance(
	 * "org.springframework.samples.petclinic.Vets" ); marshaller =
	 * (Marshaller)jaxb.createMarshaller(); } catch (JAXBException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } return marshaller; }
	 */

	/**
	 * <bean id="utilDateConverter" class=
	 * "org.springframework.samples.petclinic.validation.UtilDateConverter"/>
	 */
	/*
	 * @Bean(name = "utilDateConverter") public DateEntityValidator
	 * utilDateConverter() { return new DateEntityValidator(); }
	 */
	/**
	 * <bean id="messageSource"
	 * class="org.springframework.context.support.ResourceBundleMessageSource"
	 * p:basename="messages"/>
	 * */
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(MESSAGES_SOURCE);
		return messageSource;
	}

	/*
	 * <bean id="urlmanager"
	 * class="org.springframework.samples.petclinic.common.UrlManager">
	 * <constructor-arg> <array>
	 * <value>org.springframework.samples.petclinic.common.UrlMappings</value>
	 * </array> </constructor-arg> </bean> <bean id="ownerValidator"
	 * class="org.springframework.samples.petclinic.validation.OwnerValidator"
	 * />
	 */

	/*@Bean(name = BEAN_ID_URL_MANAGER)
	public UrlManager urlManager() {
		String[] arg = new String[] { "com.bsteel.baofc.common.UrlMappings" };

		return new UrlManager(arg);
	}
*/
/*	@Bean(name = BEAN_ID_OWNER_VALIDATOR)
	public OwnerValidator ownerValidator() {
		return new OwnerValidator();
	}*/
	
	@Bean(name = "Aspect Bean")
	public ControllerModifier controllerModifier()  {
		return new ControllerModifier();
	}
}
