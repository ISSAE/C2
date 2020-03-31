package glg203.securityDemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.securityDemo.dao.AuteurRepository;
import glg203.securityDemo.model.Auteur;
import glg203.securityDemo.roles.RolesRepository;

/**
 * Service d'accès aux utilisateurs.
 * <p> Ici, sert aussi de UserDetailsService, c'est à dire de DAO pour le login/logout.
 * On utilise directement les objets Auteur dans le système, ce qui n'est pas forcément 
 * la meilleure solution. On pourrait préférer scinder en deux la notion d'auteur d'un message
 * et la notion d'utilisateur connecté.
 */
@Service
@Transactional
public class AuteurService implements UserDetailsService {

    @Autowired
    AuteurRepository auteurRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        System.err.println("Création de l'utilisateur admin");
        if (! auteurRepository.existsById("admin")) {
            Auteur admin = buildAuteur("admin","admin", "administrateur", true);
            auteurRepository.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("Appel de loadUserByUsername");
        Auteur auteur = auteurRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("login inconnu " + username));
        List<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(RolesRepository.USER);
        if (auteur.isAdmin()) {
            authorities.add(RolesRepository.ADMIN);
        }
        return new User(auteur.getLogin(), auteur.getPassword(), authorities);        
    }

    /**
     * Liste de tous les auteurs.
     */
    public List<Auteur> auteurs() {
        return StreamSupport.stream(auteurRepository.findAll()
            .spliterator(), false)
            .collect(Collectors.toList());
    }

    /**
     * Crée un nouvel auteur (qui n'est pas un administrateur)
     */
	public void sauverAuteur(String login, String password, String signature) {
        Auteur auteur = buildAuteur(login, password, signature,false);
        auteurRepository.save(auteur);
    }
    

    /**
     * Crée un auteur (et encode son mot de passe).
     */
    private Auteur buildAuteur(String login, String password, String signature, boolean isAdmin) {
        return  new Auteur(login, passwordEncoder.encode(password), signature, isAdmin);

    }
    
}