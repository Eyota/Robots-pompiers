
package elements;


public class EventEteindre extends Evenement{
    private Incendie fire;
    private Robot robot;

    public EventEteindre (int date, Robot robot, Incendie fire){
      super(date);
      this.robot = robot;
      this.fire = fire;
    }

    public void execute(){      //try déverser un volume correspondant à l'intensité ---> catch si pas assez d'eau on met à jour l'intensité
      //si le robot n'est pas sur la case de l'incendie
      if (this.robot.getPosition().equals((Object)this.fire.getPosition())){
        //exception Evenement impossible
      }

      else{
        //si le robot peut eteindre l'incendie
        if (this.fire.getIntensite() <= this.robot.getVolumeEau()){
          //on change la date
          super.date = super.date + this.robot.gettempsIntervention(this.fire.getIntensite());
          //on eteind le feu
          this.fire.setIntensite(0);
          //on vide le reservoir
          this.robot.deverserEau(this.fire.getIntensite());
        }
        //si le robot ne peux pas eteindre l'incendie
        if (this.fire.getIntensite() > this.robot.getVolumeEau()){
          //on change la date
          this.date = this.date + this.robot.gettempsIntervention(this.robot.getVolumeEau());
          //on eteind le feu
          this.fire.setIntensite(this.fire.getIntensite() - this.robot.getVolumeEau());
          //on vide le reservoir
          this.robot.deverserEau(this.robot.getVolumeEau());
        }
      }

    }

}
