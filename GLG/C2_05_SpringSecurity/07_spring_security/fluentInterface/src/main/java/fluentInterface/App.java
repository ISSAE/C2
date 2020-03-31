package fluentInterface;

public class App {
    public static void main(String[] args) {
        // Démonstration de la création d'une URL...
        // cas simple :
        // @formatter:off (désactive l'autoformat de l'IDE... permet de conserver sa propre mise en page)
        MyUrl url = new URLBuilder()
            .protocol("http")
            .server("www.cnam.fr")
            .build();
        // @formatter:on 
        System.out.println(url);
        // @formatter:off (désactive l'autoformat de l'IDE... permet de conserver sa propre mise en page)
        MyUrl url1 = new URLBuilder().protocol("https").server("www.mysearch.com").port(443)
            .addPath("do").addPath("search")
            .addParameter("query", "langage java")
            .addParameter("lang", "fr")
            .build();
        // @formatter:on 
        System.out.println(url1);
    }
}
