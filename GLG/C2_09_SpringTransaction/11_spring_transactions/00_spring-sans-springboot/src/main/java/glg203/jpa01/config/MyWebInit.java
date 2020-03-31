package glg203.jpa01.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
       return new Class[] {
            DemoConfig.class
       };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


    
}