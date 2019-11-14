package com.hanrabong.web.cfg;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hanrabong.web.enums.Path;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"} ;
	}
	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionNoHandlerFound","true");
        
        MultipartConfigElement multipartConfig = new MultipartConfigElement(Path.UPLOAD_PATH.toString()
                , 20971520, 41943040, 20971520);
        
        registration.setMultipartConfig(multipartConfig);
    }
}
