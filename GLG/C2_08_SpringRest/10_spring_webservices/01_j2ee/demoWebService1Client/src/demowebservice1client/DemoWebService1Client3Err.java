/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demowebservice1client;

import demowebservice1client.model.Telephone;
import demowebservice1client.model.TelephoneMalFormeException;
import demowebservice1client.model.TelephoneWebService;
import demowebservice1client.model.TelephoneWebService_Service;
import demowebservice1client.model.TypeTelephone;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * Code du proxy côté client engendré par :
 * <pre>
 * wsimport .... -p demowebservice1client -wsdllocation http://localhost:8080/WebService1/Calculateur?wsdl
 * </pre>
 * <p>
 * <p>
 * Version avec injection (ne fonctionne que dans le cadre d'une app). Exemple
 * de lancement :
 * <pre>
 * /Applications/NetBeans/glassfish-4.1.1/glassfish/bin/appclient -cp dist/demoWebService1Client.jar demowebservice1client.DemoWebService1Client3Err
 * </pre>
 */
public class DemoWebService1Client3Err {

    @WebServiceRef(wsdlLocation = 
                    "http://localhost:8080/TelephoneWebService/TelephoneWebService?wsdl")
    static TelephoneWebService_Service telephoneWebServiceFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Telephone> l = new ArrayList<>();
        Telephone tel = new Telephone();
        tel.setNumero("0682");
        tel.setTypeTelephone(TypeTelephone.PROFESSIONNEL);
        l.add(tel);
        try {
            telephoneWebServiceFactory.getTelephoneWebServicePort().creerPersonneTels("a", "b", "", l);
        } catch (TelephoneMalFormeException e) {
            System.err.println("Error : " + e.getFaultInfo());
        } catch (SOAPFaultException e) {
            e.printStackTrace();            
            System.err.println("The fault object is " + e.getFault());
            System.err.println("Not the exception we want..." + e);
        }
    }

}
