package glg203.isolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import glg203.isolation.dao.CompteRepository;
import glg203.isolation.model.Compte;
import glg203.isolation.model.CompteReadOnly;

import static glg203.isolation.Utils.*;

import java.util.List;

/**
 * Services
 */
@Service
@Transactional
public class MesServices {

    @Autowired
    Utils utils;

    private long startTime = System.currentTimeMillis();
    int unit = 1000;

    @Autowired
    private CompteRepository repository;
    
    public void creerComptes() {
        for (long id = 1l; id <= 2l; id++) {
            Compte c = new Compte(id, 1000);
            repository.save(c);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void lecture(int num, int timeBefore, int timeAfter) {
        utils.sleep(timeBefore * unit);        
        utils.display(num, "tâche lecture "+num+", avant récupération ");
        List<CompteReadOnly> list = repository.findAsReadable();
        utils.display(num, "tâche lecture "+num+", après récupération ");
        int total = list.stream().mapToInt(c -> c.getSolde()).sum();
        utils.display(num, "tâche lecture "+num+" total des comptes "+total);
        utils.sleep(timeAfter*unit);
        utils.display(num, "tâche lecture "+num+", fin");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void ecriture(int num, int timeBefore, int timeAfter, int valeurAFixer) {
        utils.sleep(timeBefore * unit);
        utils.display(num, "tâche écriture "+num+", avant récupération");
        List<Compte> list = repository.findAsWriteable();
        utils.display(num, "tâche écriture "+num+", après récupération");
        for (Compte c : list) {
            c.setSolde(valeurAFixer);            
        }
        utils.sleep(100);
        utils.display(num, "tâche écriture "+num+", modifications faites ");
        utils.sleep(timeAfter*unit);
        utils.display(num, "tâche écriture "+num+", fin ");
    }

    private double seconds() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }
}