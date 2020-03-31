package glg203.jpa.model.heritage;

/**
 * DocumentVisitor
 */
public interface DocumentVisitor<T> {
    T visitTextDocument(TextDocument d);
    T visitImageDocument(ImageDocument d);
    
}