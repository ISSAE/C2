package glg203.jpa.model.heritage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "i")
public class ImageDocument extends Document{

    private String filePath;

    private int width, height;


    ImageDocument() {
    }

    public ImageDocument(Long id, String titre, Auteur auteur, String filePath, int width, int height) {
        super(id, titre, auteur);
        this.filePath = filePath;
        this.width = width;
        this.height = height;
    }

    public ImageDocument(String titre, Auteur auteur, String filePath, int width, int height) {
        super(titre, auteur);
        this.filePath = filePath;
        this.width = width;
        this.height = height;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageDocument filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public ImageDocument width(int width) {
        this.width = width;
        return this;
    }

    public ImageDocument height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " filePath='" + getFilePath() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            "}";
    }

    public <T> T accept(DocumentVisitor<T> visitor) {
        return visitor.visitImageDocument(this);
    }
    
}