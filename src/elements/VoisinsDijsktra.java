/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.List;

/**
 *
 * @author Camille Gardelle
 */
public class VoisinsDijsktra {
   private Case destination;
   private Double cout;
   
    public VoisinsDijsktra(Case pe){
       this.destination = pe;
       this.cout = 20000000.0;
   }
   
   
    public VoisinsDijsktra(Case pe, Double co){
       this.destination = pe;
       this.cout = co;
   }
    
   
    
   public Case getDestinationV(){
       return this.destination;
   }
   
   public Double getCoutV(){
       return this.cout;
   }
    
   public void setDestination(Case per){
       this.destination = per;
   }
   
   public void setCoutV(Double c){
       this.cout = c;
   }
   
}
