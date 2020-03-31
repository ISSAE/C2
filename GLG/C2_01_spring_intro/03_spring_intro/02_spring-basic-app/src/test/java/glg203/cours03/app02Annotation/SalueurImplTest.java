package glg203.cours03.app02Annotation;

import glg203.cours03.app02Annotation.beans.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class} )
public class SalueurImplTest {
    
    
    @Autowired
    ISalueur salueur;
    
    @Test
    public void testInjection() {
        Assert.assertNotNull("Injection de ISalueur",salueur);
    }
    
    
    @Test
    public void testSaluer() {
        salueur.saluer();
    }
    
}
