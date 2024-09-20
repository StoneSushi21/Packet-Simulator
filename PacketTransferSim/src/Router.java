
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



/**
 * @author Ishi Golub
 * 
 * @version 1.0.0 2024-09-13 Initial implementation
 */
public class Router{

    private Queue<Packet> containedPackets = new LinkedList<>();
    private ArrayList<Packet> recivedPackets = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();
    private String name = ((Integer)this.hashCode()).toString();
    
    /**
     * @param p the packets to set this router to contain
     * for use only with source router
     */
    void setPackets(ArrayList<Packet> p ){
        for(Packet packet : p){
            this.containedPackets.add(packet);
        } 
    }
    

    /**
     * @param p the packet to set this router to contain
     * same as above function, but for a single packet
     */
    void setPackets(Packet p){
        this.containedPackets.add(p);
    }
    
    /**
     * @return the connections
     */
    public ArrayList<Connection> getConnections() {
        
        return this.connections;
    }
    
    /**
     * @param c the connections hashmap to set the connections hashmap of this router to
     */
    public void setConnections(ArrayList<Connection> c) {
        this.connections = c;
    }

    /**
     * @param c the connection to dynamically add
     */
    public void addConnection(Connection c) {
        this.connections.add(c);
    }
    
    /**
     * @return the packets this router contains
     */
    public Queue<Packet> containedPackets() {
        return this.containedPackets;    
    }
    
    /**
     * 
     * 
     * @param n the string to name this router
     */
    public void giveName(String n) {
        this.name = n;
    }
    
    /**
     * @param p packet to recive
     */
    public void recivePacket(Packet p) {
        System.out.println(this.name+" has recived "+p.getName());
        this.recivedPackets.add(p);
    }
    
    /**
     * @param along the connection to send the packet along
     */
    public void sendPacket(Connection along) {
        if(this.containedPackets.isEmpty()){
            throw new Error();
        }        
        
        System.out.println(this.name+" has sent "+this.containedPackets.peek().getName()+" to " + along.to() + " using " +along);
        
        along.putPacket(this.containedPackets.remove());
       //to.recivePacket(this.containedPackets.remove());
        
          
    }
    
    
    /**
     * increments simulated time for this router by 0.01 sec
     */
    public void tickTime() {
        if( ! ( this.containedPackets.isEmpty() || this.connections.isEmpty() )){
            for(int i = 0;i < this.containedPackets.size();i++ ){
                // eventually change to pick best path
                sendPacket(this.connections.get(0));

            }

        }

        //rountabout way to avoid concurrent modification, yay
        ArrayList<Packet> toRemove = new ArrayList<>();
        for(Packet p : this.recivedPackets){
            this.containedPackets.add(p);
            toRemove.add(p);
        }
        for(Packet p : toRemove){
            this.recivedPackets.remove(p);
        }
        

    }

}





