package vasilenko.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import vasilenko.web.WebConfig;

import javax.servlet.Filter;



public class AlTrackerWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    public static final String CHARACTER_ENCODING = "UTF-8";

    public AlTrackerWebInitializer(){
        super();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
            return new Class<?>[] {RootConfig.class};
        }

    @Override
    protected Class<?>[] getServletConfigClasses() {
            return new Class<?>[] { WebConfig.class };
        }

    @Override
    protected String[] getServletMappings() {
            return new String[] { "/" };
        }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }
}


