package glg203.jpa.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import glg203.jpa.model.heritage.Document;
import glg203.jpa.model.heritage.ImageDocument;
import glg203.jpa.model.heritage.TextDocument;

/**
 * Comme Thymeleaf ne sait pas vraiment gérer l'héritage, Nous créons une unique
 * classe pour l'affichage des documents.
 * <p>
 * DocumentDTO
 */
public class DocumentDTO {
    /**
     * Map ordonnée : on fixe l'ordre des champs !
     */
    private LinkedHashMap<String, Object> fields = new LinkedHashMap<>();

    public DocumentDTO(TextDocument textDocument) {
        initFromDocument(textDocument);
        fields.put("contenu", textDocument.getContent());
    }

    public DocumentDTO(ImageDocument imageDocument) {
        initFromDocument(imageDocument);
        fields.put("path", imageDocument.getFilePath());
        fields.put("width", imageDocument.getWidth());
        fields.put("height", imageDocument.getHeight());
    }

    private void initFromDocument(Document document) {
        fields.put("id", document.getId());
        fields.put("titre", document.getTitre());
        fields.put("nom", document.getAuteur().getNom());
        fields.put("prenom", document.getAuteur().getPrenom());
    }

    public Object get(String fieldName) {
        return fields.get(fieldName);
    }

    public List<Entry<String, Object>> getFields() {
        return new ArrayList<>(fields.entrySet());
    }

}