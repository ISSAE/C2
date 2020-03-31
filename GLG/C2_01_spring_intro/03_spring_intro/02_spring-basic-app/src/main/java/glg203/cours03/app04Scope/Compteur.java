package glg203.cours03.app04Scope;


public class Compteur {

    private final long timeStamp;
    private int value = 0;

    public Compteur() {
        timeStamp = System.nanoTime();
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    
    public int getValue() {
        return value++;
    }
}
