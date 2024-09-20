import java.util.ArrayList;

/**
 * @author Ishi Golub
 * 
 * @version 1.0.0 2024-09-13 Initial implementation
 */

public class Main{
  /*
   * Routers take 1 ms to pass on packets
   */

   /**
    * @param args unused, not needed for this program
    */
    public static void main(String[] args) {
        

        
       
        ArrayList<Router> routers = new ArrayList<>();
        
        Router source = new Router();
        source.giveName("Source");
       

        Router destination = new Router();
        destination.giveName("Destination");
        
        routers.add(source);
        
        
        
        //create inbetween routers
        // i is how many (min 1)
        for(int i = 1; i < 1; i++ ){
            Router r = new Router();
            routers.add(r);
        }
        

        routers.add(destination);
        
        
        //create connections (all of same speed)
        for(int i = 0; i < routers.size()-1;i++ ){
            routers.get(i).addConnection(new Connection(routers.get(i), routers.get(i+1), 50.0));
            
        }
        
 
        
        ArrayList<Packet> packets = new ArrayList<>();
        
        Packet a = new Packet(5000);
        a.setName("Packet A");
        packets.add(a);
        
        
        Packet b = new Packet(50);
        b.setName("Packet B");
        packets.add(b);
        

        
        source.setPackets(packets);

        double timeToSim = 0.5;//in secs
        for(int ms = 0; ms <= (timeToSim*100); ms++ ){
            System.out.println(ms+"ms:");
            for(Router r : routers){
                r.tickTime();
                
                for(Connection path : r.getConnections()){
                    path.tickTime();
                }
                
            }    
        }//time loop
        
        
        System.out.print("Destination contains: ");
           
        for(Packet packet : destination.containedPackets()){
            System.out.print(packet.getName()+ " ");
        }
        
        
    }

}
// end class main
