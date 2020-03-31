/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glg203.model;

/**
 * Bean pour transmettre les exceptions avec @WebFault
 * @author rosmord
 */
public class MonExceptionBean {
    private String message;

    public MonExceptionBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    
}
