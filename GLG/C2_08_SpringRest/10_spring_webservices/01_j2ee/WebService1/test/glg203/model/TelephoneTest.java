/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glg203.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rosmord
 */
public class TelephoneTest {

    public TelephoneTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void constructorTest() throws TelephoneMalFormeException {
        Telephone tel = new Telephone(TypeTelephone.PERSONNEL, "0140746699");
        Telephone tel1 = new Telephone(TypeTelephone.PERSONNEL, "0140123");
    }
    
    @Test(expected = TelephoneMalFormeException.class)
    public void constructorTestException1() throws TelephoneMalFormeException {
        Telephone tel = new Telephone(TypeTelephone.PERSONNEL, "0140");
    }
     
    @Test(expected = TelephoneMalFormeException.class)
    public void constructorTestException2() throws TelephoneMalFormeException {
        Telephone tel = new Telephone(TypeTelephone.PERSONNEL, "454565456FGGFvvx");
    }
    

}
