package glg203.jpa.dto;

import glg203.jpa.model.heritage.Document;
import glg203.jpa.model.heritage.DocumentVisitor;
import glg203.jpa.model.heritage.ImageDocument;
import glg203.jpa.model.heritage.TextDocument;

public class DocumentDTOBuilder {

    private final DocumentDTOBuilderAux aux = new DocumentDTOBuilderAux();

    public DocumentDTO build(Document d) {
        return d.accept(aux);
    }

    private static class DocumentDTOBuilderAux implements DocumentVisitor<DocumentDTO> {

        @Override
        public DocumentDTO visitTextDocument(TextDocument d) {
          return new DocumentDTO(d);
        }

        @Override
        public DocumentDTO visitImageDocument(ImageDocument d) {
            return new DocumentDTO(d);
        }

    }
}