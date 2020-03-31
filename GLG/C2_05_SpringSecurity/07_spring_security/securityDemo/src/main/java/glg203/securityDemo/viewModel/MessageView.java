package glg203.securityDemo.viewModel;

import glg203.securityDemo.model.Message;

/**
 * MessageView
 * <p>Une DTO pour l'affichage de message.
 * <p>Intérêt de la chose : il porte des informations 
 * <em>différentes de celles de l'objet métier. En particulier,
 * on lui a "dit" s'il était destructible par l'utilisateur courant</em>
 */
public class MessageView {
    private Long id;
    private String titre;
    private String texte;
    private String signature;
    private String login;
    private boolean destructible= false;


    public MessageView(Message message) {
        this.id = message.getId();
        this.titre = message.getTitre();
        this.texte = message.getTexte();
        this.login = message.getAuteur().getLogin();
        this.signature= message.getAuteur().getSignature();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getTexte() {
        return this.texte;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getLogin() {
        return login;
    }

    /**
     * Dis si oui ou non cet objet peut être détruit par l'utilisateur actuel.
     * @param destructible
     */
    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

    /**
     * L'utilisateur courant peut-il détruire ce message ?
     */
    public boolean getDestructible() {
        return this.destructible;
    }

}