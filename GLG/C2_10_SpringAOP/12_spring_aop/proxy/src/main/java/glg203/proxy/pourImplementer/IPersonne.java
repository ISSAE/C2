package glg203.proxy.pourImplementer;

/**
 * IPersonne : définition des propriétés d'une personne par des accesseurs.
 */
public interface IPersonne {
    void setNom(String nom);
    void setPrenom(String prenom);
    String getNom();
    String getPrenom();    
}