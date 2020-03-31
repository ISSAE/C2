/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glg203.webservice;

import glg203.model.Personne;
import glg203.model.Telephone;
import glg203.model.TelephoneMalFormeException;
import glg203.model.TypeTelephone;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rosmord
 */
@WebService(serviceName = "TelephoneWebService")
@Stateless()
public class TelephoneWebService {

    @PersistenceContext
    EntityManager em;

    /**
     * @return une liste de personne.
     */
    @WebMethod(operationName = "telephones")
    public List<Personne> telephones() {
        return em.createQuery("select p from Personne p", Personne.class).getResultList();
    }

    @WebMethod(operationName = "creerPersonne")
    @Oneway
    public void creerPersonne(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, 
            @WebParam(name = "adresse") String adresse) {
        Personne p = new Personne(nom, prenom, adresse);
        em.persist(p);
    }
    
    @WebMethod(operationName = "testTelephone")
    public String testTelephone(@WebParam(name = "numero")String numero) throws TelephoneMalFormeException {
        System.err.println("Valeur du paramètre :" + numero);
        Telephone telephone= new Telephone(TypeTelephone.PERSONNEL, "0123456");
        telephone.setNumero(numero);
        return "ok";
    }
    
    
    @WebMethod(operationName = "creerPersonneTels")
    public String creerPersonneTels(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, 
            @WebParam(name = "adresse") String adresse, ArrayList<Telephone> telephones) throws TelephoneMalFormeException {
        Personne p = new Personne(nom, prenom, adresse);
        for (Telephone tel: telephones) {
            p.addTelephone(tel.getTypeTelephone(), tel.getNumero());
        }
        em.persist(p);
        return "ok";
    }
    
}
