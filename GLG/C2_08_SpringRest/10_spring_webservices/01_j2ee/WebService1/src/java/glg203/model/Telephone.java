/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glg203.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Somehow a value...
 *
 * @author rosmord
 */
@Embeddable
@XmlRootElement
public class Telephone implements Serializable {

    private TypeTelephone typeTelephone;

    private String numero;

//  @ManyToOne
//    private Personne possesseur;
    public Telephone(TypeTelephone typeTelephone, String numero) throws TelephoneMalFormeException {
        this.typeTelephone = typeTelephone;
        this.setNumero(numero);
    }

    public Telephone() {
    }

    public TypeTelephone getTypeTelephone() {
        return typeTelephone;
    }

    public void setTypeTelephone(TypeTelephone typeTelephone) {
        this.typeTelephone = typeTelephone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws TelephoneMalFormeException {
        if (!numero.matches("^([0-9] ?){6,10}")) {
            throw new TelephoneMalFormeException(numero);
        }
        this.numero = numero;
    }

//    void setPossesseur(Personne possesseur) {
//        this.possesseur = possesseur;
//    }
    // annotation importante (évite une boucle...)
    // @XmlTransient
    //public Personne getPossesseur() {
    //    return possesseur;
    //}
}
