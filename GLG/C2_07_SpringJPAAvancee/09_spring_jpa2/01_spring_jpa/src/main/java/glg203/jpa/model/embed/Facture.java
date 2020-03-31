package glg203.jpa.model.embed;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

/**
 * Facture.
 */
@NamedEntityGraphs(
    {@NamedEntityGraph(name = "facture.details", 
        attributeNodes = {@NamedAttributeNode(value = "lignesCommandes") })})
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nom;

    /**
     * Associe les produits de produits à leur quantité. Dans la base, fonctionne
     * sur les <em>identifiants</em>
     */
    @ElementCollection
    @CollectionTable(name = "COMMAND_LINE", joinColumns = @JoinColumn(name = "COMMAND_ID"))
    @MapKeyJoinColumn(name = "PRODUCT_ID")
    @Column(name = "QUANTITY")
    private Map<Produit, LigneFacture> lignesCommandes = new HashMap<>();

    Facture() {
    }

    public Facture(String nom) {
        this.nom = nom;
    }

    public Facture(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void ajouter(Produit p, int qte) {
        LigneFacture ligne = lignesCommandes.get(p);
        if (ligne == null) {
            ligne = new LigneFacture(p, qte);
            lignesCommandes.put(p, ligne);
        } else {
            ligne.setQuantite(ligne.getQuantite() + qte);
        }
        lignesCommandes.merge(p, new LigneFacture(p, qte),
                (ligne1, ligne2) -> new LigneFacture(p, ligne1.getQuantite() + ligne2.getQuantite()));
    }

    public void retirer(Produit p, int qte) {
        LigneFacture ligne = lignesCommandes.get(p);
        if (ligne != null) {
            int res = ligne.getQuantite() - qte;
            if (res > 0) {
                ligne.setQuantite(res);
            } else {
                lignesCommandes.remove(p);
            }
        }
    }

    public Map<Produit, LigneFacture> getLignesCommandes() {
        return new HashMap<>(lignesCommandes);
    }
}