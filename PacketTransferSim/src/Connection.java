import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 
 * @author Ishi Golub
 * @version 1.0.0 2024-09-16 Initial implementation
 *
 */
public class Connection{
    private Router from;
    private Router to;
    
    //bandWidth in bits per ms
    private Double bandWidth;
    private ArrayList<Packet> heldPackets = new ArrayList<>();
    private HashMap<Packet, Double> PacketsProgress = new HashMap<>();
    

    /**
     * @param source from router
     * @param target to router
     * @param bandW max speed in bits per ms
     */
    Connection(Router source, Router target, Double bandW){
        this.from = source;
        this.to = target;  
        this.bandWidth = bandW;
    }
    
    /**
     * @return source router
     */
    public Router from() {
        return this.from;
    }
    
    /**
     * @return destination router
     */
    public Router to() {
        return this.to;
    }
    
    /**
     * @return bandWidth
     */
    public Double bandWidth() {
        return this.bandWidth;
    }
    
    
    /**
     * @param p packet to add to this connection 
     */
    public void putPacket(Packet p) {
        this.heldPackets.add(p);
        this.PacketsProgress.put(p, 0.0);
        //System.out.println(p.getName() + " has been added to " + this);
    }
    
    private void popPacket(Packet p) {
        
        this.PacketsProgress.remove(p);
        
        //System.out.println(p.getName() + " has exited " + this);
        
        this.heldPackets.remove(p);
    }

    /**
     * simulates 1 ms of activity for this router
     * 
     */
    public void tickTime() {
        if(this.heldPackets.isEmpty()){return;}
        
        for(Packet packet : this.heldPackets){
            System.out.print(this+":"+packet.getName()+":"+ Math.floor(this.PacketsProgress.get(packet)*100)+"%->");

            this.PacketsProgress.replace(packet, this.PacketsProgress.get(packet) + (this.bandWidth/packet.size()));

            //traffic:
            if(this.heldPackets.indexOf(packet) > 0){
                if(this.PacketsProgress.get(packet) > this.PacketsProgress.get(this.heldPackets.get((this.heldPackets.indexOf(packet)-1)))){
                    this.PacketsProgress.replace(packet, this.PacketsProgress.get(this.heldPackets.get((this.heldPackets.indexOf(packet)-1))));
                }
            }
            System.out.println(Math.floor(this.PacketsProgress.get(packet)*100)+"%");
        }
        
        //have to do it in a roundabout way to avoid the iterating ending early :p
        ArrayList<Packet> packetsToPush = new ArrayList<>();
        for(Packet p : this.heldPackets){
            if(this.PacketsProgress.get(p) >= 1){
                packetsToPush.add(p);      
            }
        
        }
        for(Packet packet : packetsToPush){
            this.to.recivePacket(packet); 
            popPacket(packet);
        }
       
        
    }

}
   // end class Connection