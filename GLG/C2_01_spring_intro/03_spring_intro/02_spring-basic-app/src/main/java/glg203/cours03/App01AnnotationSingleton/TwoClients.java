package glg203.cours03.App01AnnotationSingleton;

/**
 * TwoClients
 */
public class TwoClients {

    private final BeanClient client1, client2;

    public TwoClients(BeanClient client1, BeanClient client2) {
        this.client1 = client1;
        this.client2 = client2;
    }

    public BeanClient getClient1() {
        return client1;
    }

    public BeanClient getClient2() {
        return client2;
    }

    public void affiche() {
        System.out.println("Information sur les clients");
        client1.affiche();
        client2.affiche();
    }
}