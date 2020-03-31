package glg203.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glg203.aop.model.Personne;

/**
 * PersonneService
 */
@Service
public class PersonneService {

    @Autowired
    PersonneRepository repository;

    public Long creerPersonne(String nom) {
        Personne p = new Personne(nom);
        repository.save(p);
        return p.getId();
    }

    public List<PersonneDTO> readPersonnes() {
        return repository.readAll();
    }

    public void renamePersonne(Long id, String newName) {
        repository.findById(id).ifPresent(p -> p.setNom(newName));
    }

	public PersonneDTO readById(Long id) {
        return repository.readById(id).orElse(null);
	}

	public void plante() {
        throw new RuntimeException("cette méthode plante toujours");
	}
}