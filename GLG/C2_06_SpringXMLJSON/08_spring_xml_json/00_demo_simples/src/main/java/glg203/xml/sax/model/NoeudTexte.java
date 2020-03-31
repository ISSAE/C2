package glg203.xml.sax.model;

public class NoeudTexte extends Noeud{
    private String texte;

    public NoeudTexte(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return texte;
    }

    @Override
    public <T> T accept(NoeudVisiteur<T> v) {
        return v.visitTexte(this);
    }

   
    
}
