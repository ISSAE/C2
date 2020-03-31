package glg203.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glg203.jpa.model.embed.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

    
}