package glg203.jpa.model.heritage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "t")
public class TextDocument extends Document {
    private String content;

    TextDocument() {
    }

    public TextDocument(Long id, String titre, Auteur auteur, String content) {
        super(id, titre, auteur);
        this.content = content;
    }

    public TextDocument(String titre, Auteur auteur, String content) {
        super(titre, auteur);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextDocument content(String content) {
        this.content = content;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " content='" + getContent() + "'" +
            "}";
    }


    public <T> T accept(DocumentVisitor<T> visitor) {
        return visitor.visitTextDocument(this);
    }

    
}