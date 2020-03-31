/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glg203.webservice;

import javax.jws.WebService;

/**
 * Interface pour démo de CalculateurBis
 * @author rosmord
 */
@WebService
public interface CalculateurTerInterface {
    public int somme(int a, int b);
}
