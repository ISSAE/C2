package glg203.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import glg203.aop.UserConnexion;
import glg203.aop.UtilisateurNonConnecteException;

/**
 * SecurityAspect
 */
@Component
@Aspect
@Order(100)
public class SecurityAspect {

    @Autowired
    AOPLog aopLog;

    @Autowired
    UserConnexion connexion;

    @Around("execution(* glg203..*Service.*(..)) && ! execution(* glg203..*Service.read*(..))")
    public Object checkSecurity(ProceedingJoinPoint pjp) throws Throwable {
        if (connexion.estConnecte()) {
            return pjp.proceed();
        } else {
            aopLog.addMessage("Utilisateur non connecté");
            throw new UtilisateurNonConnecteException();
        }

    }

}