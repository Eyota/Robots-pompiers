
package elements;

import java.util.List;

public class VoisinsDijsktra {
   private Case destination;
   private int cout;
   
    public VoisinsDijsktra(Case pe){
       this.destination = pe;
       this.cout = 20000000;
   }
   
   
    public VoisinsDijsktra(Case pe, int co){
       this.destination = pe;
       this.cout = co;
   }
    
   
    
   public Case getDestinationV(){
       return this.destination;
   }
   
   public int getCoutV(){
       return this.cout;
   }
    
   public void setDestination(Case per){
       this.destination = per;
   }
   
   public void setCoutV(int c){
       this.cout = c;
   }
   
}
