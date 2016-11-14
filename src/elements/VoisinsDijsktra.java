
package elements;

import java.util.List;
/**
 * Structure de données qui contient une case et un cout
 */
public class VoisinsDijsktra {
   private Case destination;
   private int cout;
   
   /**
    * initalise la structure avec une case et un cout infini
    */
    public VoisinsDijsktra(Case pe){
       this.destination = pe;
       this.cout = 20000000;
   }
   
   /**
    * initialise la structure avec une case et un coût déterminés
    * @param pe la case
    * @param co le coût
    */
    public VoisinsDijsktra(Case pe, int co){
       this.destination = pe;
       this.cout = co;
   }
    
   
    /**
     * accède à la case contenue dans la structure
     * @return la case 
     */
   public Case getDestinationV(){
       return this.destination;
   }
   
     /**
     * accède au coût contenue dans la structure
     * @return le coût
     */
   public int getCoutV(){
       return this.cout;
   }
    
   /**
    * remplace la valeur de case
    * @param per la case 
    */
   public void setDestination(Case per){
       this.destination = per;
   }
   
   /**
    * remplace le cout
    * @param c le cout
    */
   public void setCoutV(int c){
       this.cout = c;
   }
   
}
