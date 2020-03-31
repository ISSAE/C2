package glg203.isolation.model;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Id;

/**
 * TimeTable
 * Comme la temporalité des entrées/sorties et celles de la base de
 * données ne sont pas forcément coordonnées, nous utilisons cette classe 
 * pour garder la trace du moment où les événements se produisent.
 */
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int owner;

    private String label;

    private Date date = new Date();
    
    public TimeTable() {        
    }

    public TimeTable(int owner, String label) {
        this.label = label;
        this.owner = owner;
    }

    public Long getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public Date getDate() {
        return this.date;
    }

    public int getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return owner + "\t"+ date + "\t"+ label;
    }
}