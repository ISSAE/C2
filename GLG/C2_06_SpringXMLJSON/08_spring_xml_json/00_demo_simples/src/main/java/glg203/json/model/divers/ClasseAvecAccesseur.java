package glg203.json.model.divers;


/**
 * 
 */
 
public class ClasseAvecAccesseur {

    String champ1="";

    String champ2="";


    public String getChamp1() {
        return this.champ1;
    }

    public void setChamp1(String champ1) {
        System.out.println("On appelle setChamp1 "+ champ1);
        this.champ1 = champ1;
    }

    public String getChamp2() {
        return this.champ2;
    }

    public void setChamp2(String champ2) {
        System.out.println("On appelle setChamp2 "+ champ2);
        this.champ2 = champ2;
    }

    
    @Override
    public String toString() {
        return champ1 + " " + champ2;        
    }
}