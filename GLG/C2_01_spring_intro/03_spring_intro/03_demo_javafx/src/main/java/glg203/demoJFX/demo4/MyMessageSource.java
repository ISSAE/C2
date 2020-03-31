package glg203.demoJFX.demo4;

public class MyMessageSource implements IMessageSource {

    @Override
    public String getMessage() {
        return "Dans cette version, plus propre, on utilise l'injection de dépendance pour injecter le bon MessageSource.\n"+
        "Le système est encore assez bancal... la création du contrôleur supporterait mal une architecture plus complexe.\n"+
        "Remarque: on a en fait deux systèmes d'injection de dépendances. Le nôtre (très sommaire), et celui de FXMLLoader."
        ;
    }
    
}