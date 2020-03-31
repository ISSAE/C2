package glg203.jpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa.model.embed.Adresse;
import glg203.jpa.model.embed.Client;
import glg203.jpa.model.embed.Facture;
import glg203.jpa.model.embed.Produit;
import glg203.jpa.model.embed.TableDesMatieres;
import glg203.jpa.repositories.ClientRepository;
import glg203.jpa.repositories.FactureRepository;
import glg203.jpa.repositories.ProduitRepository;
import glg203.jpa.repositories.TableDesMatieresRepository;

/**
 * Service montrant les fonctionnalités liées à l'embedding et aux collections
 * d'éléments
 */
@Service
@Transactional
public class DemoAnnotationsAvanceesService {

    private Logger logger = LoggerFactory.getLogger(DemoAnnotationsAvanceesService.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    TableDesMatieresRepository tableDesMatieresRepository;

    @PostConstruct
    public void init() {
        initClients();
        initFacture();
        initTableDesMatieres();
    }

    private void initTableDesMatieres() {
        TableDesMatieres tab = new TableDesMatieres("livre", Arrays.asList("partie 1", "partie 2", "partie 3"));
        tableDesMatieresRepository.save(tab);
    }

    /**
     * Crée une facture, qui montre une map intégrée dans un objet.
     */
    private void initFacture() {
        Random random = new Random();
        if (factureRepository.count() == 0) {
            // Commence par créer quelques produits.
            ArrayList<Produit> produits = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Produit p = new Produit("prod" + i, 5 + random.nextInt(1000));
                produitRepository.save(p);
                produits.add(p);
            }
            // Crée quelques factures...
            for (int i = 0; i < 5; i++) {
                Facture facture = new Facture("n" + (i % 3));
                int nbLignes = random.nextInt(9) + 1;
                for (int j = 0; j < nbLignes; j++) {
                    int indexProd = random.nextInt(produits.size());
                    int qte = random.nextInt(10) + 1;
                    facture.ajouter(produits.get(indexProd), qte);
                }
                factureRepository.save(facture);
            }
        }
    }

    /**
     * Initialise des objets "clients" avec une adresse "@Embedded".
     * <p>
     * Intérêt : voir que dans la BD, les champs de l'adresse sont directement
     * copiés dans la table client.
     */
    private void initClients() {
        // Initialise quelques clients...
        if (clientRepository.count() == 0) {
            for (int i = 0; i < 100; i++) {
                Adresse adresse = new Adresse("rue " + i, (i % 2 == 0) ? "Paris" : "Palo Alto", "00" + i);
                Client client = new Client("n" + i, adresse);
                clientRepository.save(client);
            }
        }
    }

    public List<Client> clients() {
        return clientRepository.findAll();
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    // Conserve le résultat de findAll en cache de second niveau.
    @Cacheable(value = "facture")
    public List<Facture> factures() {
        return factureRepository.findAll();
    }

    /**
     * Une méthode idiote, pour voir l'évolution des factures. Retire un de chaque
     * quantité dans chaque facture. Du coup, on va supprimer des lignes.
     * 
     */
    @CacheEvict(allEntries = true, value = "facture")
    public void retirerUnProduitDeChaqueFacture() {
        for (Facture f : factureRepository.findAll()) {
            Set<Produit> produitsCommandes = f.getLignesCommandes().keySet();
            for (Produit p : produitsCommandes) {
                f.retirer(p, 1);
            }
        }
    }

    /**
     * Liste les (la) table des matières.
     * @return
     */

    public List<TableDesMatieres> tablesDesMatieres() {
        return tableDesMatieresRepository.findAll();
    }

    /**
     * Ajoute une entrée en tête des tables des matières
     */
    public void ajouteEntreeATableDesMatieres() {
        tableDesMatieresRepository.findAll().forEach(
            tab -> {
                tab.getEntrees().add(0, "partie 0");
            });
    }

}