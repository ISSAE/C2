package glg203.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * ServiceAdvice.
 * Aspect qui logge tout appel à un service.
 */
@Component
@Aspect
@Order(1)
public class ServiceAdvice {

    @Autowired
    AOPLog aopLog;
   
 
    @Before("execution(* glg203..*Service.*(..))")
    public void beforeService(JoinPoint joinPoint) {
        aopLog.addMessage("On va appeler la méthode de service "+joinPoint.getSignature().getName());
    }

    @Before("execution(* glg203.aop.*Repository.*(..))")
    public void beforeRepository(JoinPoint joinPoint) {
        aopLog.addMessage("On va appeler la méthode de repository "+ joinPoint.getSignature().getName());
    }    
}