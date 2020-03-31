/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glg203.webClient;

import demowebservice1client.Calculateur_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 * Pour réaliser cette appli:
 * <ul>
 * <li> Générer le proxy client (new webservice client, appelle wsimport).
 * <li> insérer l'appel (ctrl-i, call web service operation
 * </ul>
 * @author rosmord
 */
@WebServlet(name = "ComputeClientServlet", urlPatterns = {"/computeClient"})
public class ComputeClientServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebService1/Calculateur.wsdl")
    private Calculateur_Service service;

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a= Integer.parseInt(req.getParameter("a"));
        int b= Integer.parseInt(req.getParameter("b"));
        int res= somme(a, b);        
        req.setAttribute("resultat", res);        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private synchronized int somme(int arg0, int arg1) {        
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        demowebservice1client.Calculateur port = service.getCalculateurPort();
        return port.somme(arg0, arg1);
    }
    
}
