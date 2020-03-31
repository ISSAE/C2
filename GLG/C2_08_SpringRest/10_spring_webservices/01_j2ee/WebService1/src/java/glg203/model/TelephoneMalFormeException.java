/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glg203.model;

import javax.xml.ws.WebFault;

/**
 *
 * @author rosmord
 */
// Voir http://io.typepad.com/eben_hewitt_on_java/2009/07/using-soap-faults-and-exceptions-in-java-jaxws-web-services.html
// pour faire fonctionner ça...
@WebFault(name = "MonExceptionBean")
public class TelephoneMalFormeException extends Exception {
    private MonExceptionBean exceptionBean;
    
    private String telephoneNumber;

    public TelephoneMalFormeException(String telNum) {
        super("numéro mal formé "+ telNum);
        this.telephoneNumber = telNum;
        exceptionBean= new MonExceptionBean("numéro mal formé "+ telNum);
    }

    /**
     * Méthode qui retourne l'information sur l'exception.
     * Cette méthode est nécessaire.
     * @return 
     */
    public MonExceptionBean getFaultInfo() {
        return exceptionBean;
    }

}
