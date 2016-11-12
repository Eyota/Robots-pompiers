
package elements.events;

import elements.Incendie;
import elements.robots.Robot;


public class EventEteindre extends Evenement{
    private Incendie fire;
    private Robot robot;

    public EventEteindre (int date, Robot robot, Incendie fire){        // EmptyTankException ??
      super(date);
      this.robot = robot;
      this.fire = fire;
    }

    public void execute(){      
      //si le robot n'est pas sur la case de l'incendie
      if (!this.robot.getPosition().equals((Object)this.fire.getPosition())){
        //exception Evenement impossible
          System.out.println("Il n'y a pas de feu a eteindre ici");
      }

      else{
        //si le robot peut eteindre l'incendie

        if (this.fire.getIntensite() <= this.robot.getVolumeEau()){
            System.out.println("Le robot eteint le feu");   
            
          //on eteint le feu
          this.fire.setIntensite(0);
          //on vide le reservoir
          this.robot.deverserEau((int)this.fire.getIntensite());
        }
        //si le robot ne peux pas eteindre l'incendie
        if (this.fire.getIntensite() > this.robot.getVolumeEau()){
            System.out.println("Le robot ne peut pas competement eteindre le feu");
          //on eteint le feu
          this.fire.setIntensite(this.fire.getIntensite() - this.robot.getVolumeEau());
          //on vide le reservoir
          this.robot.deverserEau((int)this.robot.getVolumeEau());
        }
        
      }

    }

}
