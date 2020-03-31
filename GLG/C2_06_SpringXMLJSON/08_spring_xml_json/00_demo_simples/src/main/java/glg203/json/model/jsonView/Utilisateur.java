package glg203.json.model.jsonView;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Utilisateur
 */
public class Utilisateur {

    @JsonView({FullView.class, NomEtIdView.class})
    private Long id;

    @JsonView({FullView.class, NomEtIdView.class})
    private String nom;
    
    @JsonView({FullView.class, MotDePasseView.class})
    private String motDePasse;

    public Utilisateur() {
        super();
    }


    public Utilisateur(Long id, String nom, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.motDePasse = motDePasse;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}