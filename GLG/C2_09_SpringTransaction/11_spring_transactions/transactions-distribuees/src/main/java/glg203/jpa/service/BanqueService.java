package glg203.jpa.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

// Important : bien noter les imports... Il y a plusieurs annotations 
// @Transactional.

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa.dto.CompteDTO;
import glg203.jpa.model.Compte;
import glg203.jpa.model.CompteException;
import glg203.jpa.repositories.banque1.Banque1Repository;
import glg203.jpa.repositories.banque2.Banque2Repository;

@Service
// On dit que le service est transactionnel.
// Comme CompteException est une exception déclarée (et non une RuntimeException), le rollback
// n'est pas automatique quand elle est levée.
// On doit donc préciser qu'elle fait partie des exceptions qui déclenchent un rollback.
// testez éventuellement ce programme en enlevant le code "rollbackFor = {CompteException.class}"
@Transactional( 
    transactionManager = "transactionManager", // c'est la valeur par défaut, on peut ne pas le préciser.
    rollbackFor = {CompteException.class}) // exceptions qui déclenchent un rollback (les RuntimeExceptions en déclenchent par défaut)
@DependsOn("transactionManager")
public class BanqueService {
    Logger logger = LoggerFactory.getLogger(BanqueService.class);

    @Autowired
    Banque1Repository repository1;

    @Autowired
    Banque2Repository repository2;

    /**
     * Crée les deux comptes en banque (un dans chaque banque), s'ils n'existent pas.
     */
    @PostConstruct
    public void init() {
        if (repository1.count() == 0) {
            logger.info("on crée les objets clients");
            repository1.save(new Compte("1", "client 1", 1000));
            repository2.save(new Compte("2", "client 2", 2000));
        } else {
            logger.info("objets existant");
        }
    }

    /**
     * Récupère et renvoie les deux comptes.
     * @return
     */
    @Transactional(readOnly = true)
    public List<CompteDTO> getComptes() {
        Compte c1 = repository1.findById("1").get();
        Compte c2 = repository2.findById("2").get();
        return Arrays.asList(
            new CompteDTO(c1.getNumero(), c1.getClient(), c1.getSolde()),
            new CompteDTO(c2.getNumero(), c2.getClient(), c2.getSolde())
            );
    }

    /**
     * Effectue un virement du compte 1 vers le compte 2.
     * <p> Noter que pour simuler un problème et voir les transactions à l'œuvre,
     * la méthode qui effectue un dépôt lève une CompteException si la somme déposée est de 100.
     * <p> Dans ce cas, le virement étant un retrait sur compte1 suivi d'un dépôt sur compte2, 
     * <b>si on n'est pas transactionnels,</b> compte 1 perdra 100 euros sans que compte2 ne soit crédité.
     * <b>Comme on est transactionnels,</b> et que CompteException est déclarée dans <code>rollbackFor,</code>
     * elle déclenche un rollback, et ni compte 1 ni compte 2 ne sont touchés.
     * <p> Pour voir ce qui se passe sinon, enlevez CompteException du rollbackFor.
     * @param somme la somme à virer.
     * @throws CompteException
     */
    public void virer(double somme) throws CompteException {
        Compte c1 = repository1.findById("1").get();
        Compte c2 = repository2.findById("2").get();
        c1.virerVers(c2, somme);
    }
}