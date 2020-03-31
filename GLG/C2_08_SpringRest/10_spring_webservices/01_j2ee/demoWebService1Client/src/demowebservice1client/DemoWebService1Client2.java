/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demowebservice1client;

import javax.xml.ws.WebServiceRef;

/**
 * Code du proxy côté client engendré par :
 * <pre>
 * wsimport .... -p demowebservice1client -wsdllocation http://localhost:8080/WebService1/Calculateur?wsdl
 * </pre>
 * <p>
 * Version avec injection (ne fonctionne que dans le cadre d'une app). Exemple
 * de lancement :
 * <pre>
 *   /Applications/NetBeans/glassfish-4.1.1/glassfish/bin/appclient -cp dist/demoWebService1Client.jar demowebservice1client.DemoWebService1Client2
 * </pre>
 */
public class DemoWebService1Client2 {

    @WebServiceRef(wsdlLocation
            = "http://localhost:8080/WebService1/Calculateur?wsdl")

    private static Calculateur_Service service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"10", "33"};
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println(somme(a, b));
    }

    private static int somme(int v1, int v2) {
        demowebservice1client.Calculateur port = service.getCalculateurPort();
        return port.somme(v1, v2);
    }

}
