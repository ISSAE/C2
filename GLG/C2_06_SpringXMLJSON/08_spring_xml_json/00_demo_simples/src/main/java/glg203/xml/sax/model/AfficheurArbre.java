package glg203.xml.sax.model;

/**
 *
 * @author rosmord
 */
public class AfficheurArbre {

    public void afficher(Noeud n) {
        n.accept(new AfficherVisiteur());
    }

    private class AfficherVisiteur implements NoeudVisiteur<Void> {

        private int decalage = 0;

        @Override
        public Void visitElement(NoeudElement n) {
            marge();
            System.out.println(n.getLabel().toUpperCase());
            decalage++;
            for (Noeud f : n.getFils()) {
                f.accept(this);
            }
            decalage--;
            return null;
        }

        @Override
        public Void visitTexte(NoeudTexte t) {
            if (!"".equals(t.getTexte().trim())) {
                marge();
                System.out.println(t.getTexte());
            }
            return null;
        }

        private void marge() {
            for (int i = 0; i < decalage; i++) {
                System.out.print(" ");
            }
        }

    }
}
