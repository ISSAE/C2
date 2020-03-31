package glg203.proxy.pourImplementer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * DTOFactoryTest
 */
public class DTOFactoryTest {

    @Test
    public void testChampLireEcrire() {
        IPersonne p = DTOFactory.creerObjet(IPersonne.class);
        p.setNom("aa");
        assertEquals("aa", p.getNom());
    }

    public void testDeuxChamps() {
        IPersonne p = DTOFactory.creerObjet(IPersonne.class);
        p.setNom("aa");
        p.setPrenom("bb");
        assertEquals("aa", p.getNom());
        assertEquals("bb", p.getPrenom());
    }
    public void valeursModifiees() {
        IPersonne p = DTOFactory.creerObjet(IPersonne.class);
        p.setNom("aa");
        p.setNom("bb");
        assertEquals("bb", p.getNom());        
    }

}