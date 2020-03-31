package glg203.cours03.app04Scope;

import org.springframework.beans.factory.annotation.Value;

public class DemoCompteur {
    
    /**
     * test injection de propriétés. A voir séparément. 
     */
 
    @Value("#{systemProperties['user.home']}")
    private String home;
    
    private Compteur compteur1;
    private Compteur compteur2;
    private Compteur compteur3;
    private Compteur compteur4;

    public void setCompteur1(Compteur compteur1) {
        this.compteur1 = compteur1;
    }

    public void setCompteur2(Compteur compteur2) {
        this.compteur2 = compteur2;
    }

    public void setCompteur3(Compteur compteur3) {
        this.compteur3 = compteur3;
    }

    public void setCompteur4(Compteur compteur4) {
        this.compteur4 = compteur4;
    }

    public Compteur getCompteur1() {
        return compteur1;
    }

    public Compteur getCompteur2() {
        return compteur2;
    }

    public Compteur getCompteur3() {
        return compteur3;
    }

    public Compteur getCompteur4() {
        return compteur4;
    }
    
    public void infos() {
        System.out.println("Le home est " + home);
        Compteur[] t= {compteur1, compteur2, compteur3, compteur4};
        for (int i = 0; i < t.length; i++) {
            System.out.println("Compteur "+i + " timeStamp "+ t[i].getTimeStamp());
        }
    }
    
}
