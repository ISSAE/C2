package glg203.jpa.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa.dto.DocumentDTO;
import glg203.jpa.dto.DocumentDTOBuilder;
import glg203.jpa.model.heritage.Auteur;
import glg203.jpa.model.heritage.Document;
import glg203.jpa.model.heritage.ImageDocument;
import glg203.jpa.model.heritage.TextDocument;
import glg203.jpa.repositories.AuteurRepository;
import glg203.jpa.repositories.DocumentRepository;

/**
 * DocumentService
 */
@Service
@Transactional
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    AuteurRepository auteurRepository;

    @PostConstruct
    public void initDocuments() {
        Auteur[] auteurs = { new Auteur("n1", "p1"), new Auteur("n2", "p2"), new Auteur("n3", "p3"), };
        auteurRepository.saveAll(Arrays.asList(auteurs));

        for (int i = 0; i < 100; i++) {
            Auteur auteur = auteurs[i % auteurs.length];
            if (i % 2 == 0) {
                TextDocument textDocument = new TextDocument("t" + i, auteur, "texte" + i);
                documentRepository.save(textDocument);
            } else {
                ImageDocument imageDocument = new ImageDocument("t" + i, auteur, "path " + i, i, i);
                documentRepository.save(imageDocument);
            }
        }
    }

    public List<Document> documents() {
        return documentRepository.findAll();
    }

    public Document dernier() {
        return documentRepository.dernierDocument();
    }

    public List<DocumentDTO> documentsDTO() {
        DocumentDTOBuilder dtoBuilder = new DocumentDTOBuilder();
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false).map(d -> dtoBuilder.build(d))
                .collect(Collectors.toList());
    }

}