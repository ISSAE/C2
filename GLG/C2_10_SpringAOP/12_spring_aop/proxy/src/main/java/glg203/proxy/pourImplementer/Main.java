package glg203.proxy.pourImplementer;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        IPersonne p = DTOFactory.creerObjet(IPersonne.class);
        p.setNom("toto");
        IPersonne p1 = DTOFactory.creerObjet(IPersonne.class);
        p1.setNom("toto1");
        p1.setPrenom("foo");
        System.out.println(p.getNom());
        System.out.println(p1.getNom());
        System.out.println(p1.getPrenom());
        ICompte compte = DTOFactory.creerObjet(ICompte.class);
        compte.setProprietaire(p);
        compte.setSolde(1000);
        System.out.println("Compte de "+ compte.getProprietaire().getNom() 
            + " solde : " + compte.getSolde());
    }
}