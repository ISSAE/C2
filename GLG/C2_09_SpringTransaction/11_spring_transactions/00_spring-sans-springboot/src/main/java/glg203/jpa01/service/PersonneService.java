package glg203.jpa01.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa01.dao.PersonneRepository;
import glg203.jpa01.model.Personne;

/**
 * PersonneService
 */
@Service
// Attention, pour @Transactional, à bien importer org.springframework.transaction.annotation.Transactional
// et pas javax.transaction.Transactional
@Transactional(isolation = Isolation.READ_COMMITTED)
public class PersonneService {

    @Autowired
    PersonneRepository repository;

    @Transactional(readOnly = true)
    public List<Personne> findAll() {
        return repository.findAll();
    }
    
    public Long creerPersonne(String nom, String prenom) {
        Personne p = new Personne(nom, prenom);
        repository.save(p);
        return p.getId();
    }

    /**
     * Crée n personnes.
     * Si n est > 9, plante au bout de 10 personnes et lève une RuntimeException.
     */
    public void creerNPersonnes(int n) {
        for (int i= 0; i< n; i++) {
            Personne p= new Personne("n"+i, "p"+i);
            repository.save(p);
            if (i == 9) throw new RuntimeException("ne devrait pas arriver");
        }
    }
}