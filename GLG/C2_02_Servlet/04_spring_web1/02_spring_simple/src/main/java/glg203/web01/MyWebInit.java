package glg203.web01;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
       return new Class[] {
            MyWebConfig.class
       };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


    
}