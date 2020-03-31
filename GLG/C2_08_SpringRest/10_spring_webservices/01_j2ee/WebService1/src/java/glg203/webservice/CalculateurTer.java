/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glg203.webservice;

import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * Exemple simple de Web service.
 * <ul>
 * <li>  adresse:  http://localhost:8080/WebService1/Calculateur</li>
 * <li>  adresse du wsdl:  http://localhost:8080/WebService1/Calculateur?wsdl</li>
 * <li>  adresse du test:  http://localhost:8080/WebService1/Calculateur?Tester</li>
 * 
 * </ul>
 */
@WebService(endpointInterface = "glg203.webservice.CalculateurTerInterface", targetNamespace = "fr.cnam.glg203")
public class CalculateurTer implements CalculateurTerInterface{
  
    @Override
    public int somme(int a, int b) {
        return a+b;
    }
    
    
    public String sommeHexa(@WebParam(name = "v1")int a, @WebParam(name="v2")int b) {
        return Integer.toHexString(a+b).toUpperCase();
    }
    
}
