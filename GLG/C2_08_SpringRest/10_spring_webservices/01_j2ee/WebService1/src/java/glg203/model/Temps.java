/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package glg203.model;

import java.util.Date;

public class Temps {
    private Date date;
    private double temperature;

    public Temps() {
        date= new Date();
        temperature= Math.random() * 40 - 10;
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
    
}
