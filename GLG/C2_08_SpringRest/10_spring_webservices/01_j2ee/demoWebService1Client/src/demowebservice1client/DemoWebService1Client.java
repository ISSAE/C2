/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demowebservice1client;

/**
 * Code du proxy côté client engendré par :
 * <pre>
 * wsimport .... -p demowebservice1client -wsdllocation http://localhost:8080/WebService1/Calculateur?wsdl
 * </pre>
 * <p> Code client engendré par netbeans (controle-i, puis "call web service operation")</p>
 */
public class DemoWebService1Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        int a= Integer.parseInt(args[0]);
        int b= Integer.parseInt(args[1]);      
        demowebservice1client.Calculateur_Service service = new demowebservice1client.Calculateur_Service();
        demowebservice1client.Calculateur port = service.getCalculateurPort();
        int res = port.somme(a, b);
        System.out.println("Résultat "+ res);
    }
   
}
