package glg203.json.model.formes;


/**
 * Rectangle
 */
public class Rectangle extends Forme{
    int x1,x2, y1, y2;

    public Rectangle() {        
    }

    public Rectangle(int x1, int x2, int y1,int y2) { 
               this.x1= x1;
               this.x2= x2;
               this.y1= y1;
               this.y2= y2;               
    }

    /**
     * @return the x1
     */
    public int getX1() {
        return x1;
    }
    /**
     * @return the x2
     */
    public int getX2() {
        return x2;
    }
    /**
     * @return the y1
     */
    public int getY1() {
        return y1;
    }
    /**
     * @return the y2
     */
    public int getY2() {
        return y2;
    }

    @Override
    public String toString() {
        return "rectangle("+x1+","+x2+","+y1+","+y2+")";
    }
}