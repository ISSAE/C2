package glg203.json.apps;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.bidiIdentity.*;
/**
 * DemoBidi.
 * 
 * Démonstration de mapping bidirectionnel en utilisant l'identité en Jackson.
 */
public class DemoBidiIdentity {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        Universite u = new Universite(1l,"cnam");
        Etudiant e1 =  new Etudiant(2l,"e1");
        Etudiant e2 =  new Etudiant(3l,"e2");
        u.addEtudiant(e1);
        u.addEtudiant(e2);
        String s= mapper.writeValueAsString(u);
        System.out.println(s);
        Universite u1 = mapper.readValue(s, Universite.class);
        System.out.println("L'université contient deux étudiants " + (u1.getEtudiants().size() == 2));
        System.out.println("Leur université est bien le cnam :");
        for (Etudiant e : u1.getEtudiants()) {
            System.out.println("pour "+e.getNom()+ " c'est "+ (u1 == e.getUniversite()));
        }
        System.out.println(mapper.writeValueAsString(u1));
    }
}