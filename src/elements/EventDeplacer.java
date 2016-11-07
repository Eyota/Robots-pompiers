
package elements;

public class EventDeplacer extends Evenement{
    private Case position;
    private Robot robot;

    public EventDeplacer (int date, Robot robot, Case newPos){
      super(date);
      this.robot = robot;
      this.position = newPos;
    }

    public void execute(){
      //si les cases ne sont pas voisines
      if (!sontVoisines(this.robot.getPosition(),this.position)){
        //exception deplacement impossible
      }
      else{
        //on met le robot sur sa nouvelle case
        this.robot.setPosition(this.position);
        //on considere que le chemin entre 2 cases fait n km
        //on change la date
        this.date = this.date + n/this.robot.getVitesse();
      }
    }
}
