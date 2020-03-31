package glg203.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import glg203.aop.model.Personne;

/**
 * ServiceAdvice.
 * Aspect qui logge tout appel à un service.
 */
@Component
@Aspect
@Order(10)
public class ServiceAdviceLevel10 {

    @Autowired
    AOPLog aopLog;
   
    // Dans cette version, nous séparons la déclaration des points de jonction et les 
    // advices.

    // Définition d'un point de coupe... la méthode save
    // saveCutPoint sert juste à porter l'annotation...
    @Pointcut("execution(* glg203.aop.PersonneRepository.save(..))")
    private void saveCutPoint() {}

    @Pointcut("execution(* glg203..*.readById(Long))")
    private void beforeReadCutPoint() {}


    @Before("saveCutPoint() && args(personne)")
    public void beforeSave(Personne personne) {
        aopLog.addMessage("On va sauver " + personne.getNom());
    }

    @Before("beforeReadCutPoint() && args(id)")
    public void beforeFind( JoinPoint joinPoint, Long id) {
        aopLog.addMessage("on appelle readById "+id+ " dans " + joinPoint.getSignature().getDeclaringTypeName());
    }
    
}