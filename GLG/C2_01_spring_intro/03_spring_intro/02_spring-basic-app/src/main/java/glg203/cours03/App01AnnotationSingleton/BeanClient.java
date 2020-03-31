package glg203.cours03.App01AnnotationSingleton;

/**
 * Utilise le bean "UnBean".
 * Va permettre de montrer que celui-ci est un singleton...
 */
public class BeanClient {

    private final UnBean unBean;

    public BeanClient(UnBean unBean) {
        this.unBean = unBean;
    }

    /**
     * @return the unBean
     */
    public UnBean getUnBean() {
        return unBean;
    }

    public void affiche() {
        System.out.println("On est dans le beanClient "+ this.toString());
        System.out.println("On détient le bean "+ unBean.toString());
    }
}