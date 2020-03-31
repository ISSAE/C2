package glg203.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import glg203.aop.PersonneDTO;

/**
 * AllCalls
 * (désactivé. Pour réactiver, décommentez @Component et @Aspect)
 */
// @Component
// @Aspect
public class AllCalls {

    //@Before("execution(* glg203..*(..))")
    public void logAllCalls(JoinPoint joinPoint) {
        System.out.println("log appels "+joinPoint.getSignature().getName());
    }    

    @AfterReturning(pointcut = "execution(glg203.aop.PersonneDTO glg203..*(..))", returning = "value")
    public void readPersonneAdvice(PersonneDTO value) {
        System.out.println("** On retourne "+ value.getNom() + " ");
    }    

    @AfterThrowing(throwing = "ex", pointcut = "execution(* glg203..*(..))")
    public void ListeExceptions(Throwable ex) {
        System.out.println("** on a vu l'exception " +ex);
    }

    
}