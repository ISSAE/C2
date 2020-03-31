package glg203.json.model.formes;

/**
 * Cercle
 */
public class Cercle extends Forme{
    private int x,y,r=1;

    public Cercle() {        
    }


    public Cercle(int x,int y, int r) {        
        this.x= x;
        this.y= y;
        this.r= r;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the r
     */
    public int getR() {
        return r;
    }
    @Override
    public String toString() {
        return "cercle("+x+","+y+","+r+")";
    }
}