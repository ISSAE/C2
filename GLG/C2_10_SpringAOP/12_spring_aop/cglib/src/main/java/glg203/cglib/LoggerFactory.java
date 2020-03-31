package glg203.cglib;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * LoggerFactory
 */
public class LoggerFactory {

    @SuppressWarnings("unchecked")
    public static <T> T creerLogger(T object, PrintWriter out) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(new LoggerCallback(out));
        return (T)enhancer.create();
    }

    private static class LoggerCallback implements MethodInterceptor {
        private final PrintWriter out;
        
        public LoggerCallback(PrintWriter out) {
            this.out=out;
        }                
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            out.println("Appel de "+ method.getName()+ Arrays.asList(args));
            return proxy.invokeSuper(obj, args);            
        }

    }
}