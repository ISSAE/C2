/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glg203.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * Exemple simple de Web service.
 * <ul>
 * <li>  adresse:  http://localhost:8080/WebService1/CalculateurBis</li>
 * <li>  adresse du wsdl:  http://localhost:8080/WebService1/CalculateurBis?wsdl</li>
 * <li>  adresse du test:  http://localhost:8080/WebService1/Calculateur?Tester</li>
 * 
 * </ul>
 */
@WebService
public class CalculateurBis {
     
    @WebMethod(exclude = true)
    public int somme(int a, int b) {
        return a+b;
    }
    
    @WebMethod(operationName = "sommeHx")
    public String sommeHexa(@WebParam(name = "v1")int a, @WebParam(name="v2")int b) {
        return Integer.toHexString(a+b).toUpperCase();
    }
    
}
