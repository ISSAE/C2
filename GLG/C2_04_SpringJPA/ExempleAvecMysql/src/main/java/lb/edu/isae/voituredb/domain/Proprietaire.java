package lb.edu.isae.voituredb.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proprietaire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ownerid;
	private String firstname, lastname;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
	private List<Voiture> cars;
	
	public Proprietaire() {}
	
	public Proprietaire(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public List<Voiture> getVoitures() {
		return cars;
	}
	
	public void setVoitures(List<Voiture> cars) {
		this.cars = cars;
	}
	
	public long getOwnerid() {
		return ownerid;
	}
	
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	
}
