package glg203.jpa.dto;

public class CompteDTO {
    private String numero;
    private String nom;
    private double solde;

    public CompteDTO(String numero, String nom, double solde) {
        this.numero = numero;
        this.nom = nom;
        this.solde = solde;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public double getSolde() {
        return this.solde;
    }


}
