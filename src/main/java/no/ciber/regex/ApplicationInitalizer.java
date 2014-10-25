package no.ciber.regex;

import no.ciber.regex.facade.FacadeConfig;
import no.ciber.regex.web.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This software is written by Michael on 20/10/2014.
 */
public class ApplicationInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                FacadeConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
         };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
