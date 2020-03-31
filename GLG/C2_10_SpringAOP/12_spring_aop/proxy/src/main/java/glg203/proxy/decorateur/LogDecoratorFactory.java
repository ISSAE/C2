/**
 * 
 */

package glg203.proxy.decorateur;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Une classe qui crée des décorateurs ajoutant des possibilités de Log à un
 * objet. Les opérations sur l'objet seront listées sur un PrintWriter.
 */
public class LogDecoratorFactory {

    /**
     * crée un proxy autour de cible.
     * <p> pré-condition : la classe de cible doit implémenter au moins une interface.
     * @param <T> une des interfaces implémentée par cible
     * @param cible l'objet dont on logge les commandes.
     * @param out la sortie des affichages de log.
     * @return un décorateur qui appelle les méthodes de cible, mais en les "loggant".
     */
    @SuppressWarnings("unchecked")
    public static <T> T creerLogDecorator(T cible, PrintWriter out) {
        Class<?> classes[] = cible.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), classes,
                new LogDecoratorImpl<T>(cible, out));
    }

    private static class LogDecoratorImpl<T> implements InvocationHandler {
        private T object;
        private PrintWriter out;

        public LogDecoratorImpl(T object, PrintWriter out) {
            this.object = object;
            this.out = out;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            out.println("appel de " + method.getName());
            out.flush();
            Object res = method.invoke(object, args);
            // éventuellement :
            // out.println("fin d'appel de " + method.getName());
            // out.flush();
            // (serait utile pour tracer des appels imbriqués)
            return res;
        }
    }

}