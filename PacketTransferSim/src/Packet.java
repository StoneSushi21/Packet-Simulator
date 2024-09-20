

/**
 * 
 * 
 * @author Ishi Golub
 * @version 1.0.0 2024-09-13 Initial implementation
 *
 */
public class Packet{

    /**
     * size of the packet in bits
     */
    private int size;
    
    //just for fun
    private String data = "";
    
    private String name = ((Integer)this.hashCode()).toString();
    
    
    /**
     * @param s size of packet
     */
    Packet(int s){
        this.size = s;
    }
    
    /**
     * @return the size of this packet
     */
    public double size() {
        return this.size;
    }

    /**
     * @return the data
     */
    public String getData() {
        return this.data ;

    }

    /**
     * @param d the data to set
     */
    public void setData(String d) {
        this.data = d ;

    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name ;

    }

    /**
     * @param n the name to set
     */
    public void setName( String n) {
        this.name = n ;

    }
    

    
    
}
   // end class Packet