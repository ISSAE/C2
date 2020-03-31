package glg203.proxy.pourImplementer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * DTOFactory. Crée des implémentations d'interfaces DTO (getter+setters)
 * fondées sur des maps.
 */
public class DTOFactory {

    @SuppressWarnings(value = "unchecked")
    public static <T> T creerObjet(Class<T> clazz) {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> classes[] = { clazz };
        return (T) Proxy.newProxyInstance(currentClassLoader, classes,
                new ImplementationHandler());
    }

    private static class ImplementationHandler implements InvocationHandler {
        Map<String, Object> map = new HashMap<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().startsWith("get"))
                return get(proxy, method.getName().substring(3));
            else if (method.getName().startsWith("set"))
                return set(proxy, method.getName().substring(3), args);
            else
                throw new UnsupportedOperationException(method.getName());
        }

        private Object set(Object proxy, String propName, Object[] args) {
            ;
            return map.put(propName, args[0]);
        }

        private Object get(Object proxy, String propName) {
            return map.get(propName);
        }

    }
}