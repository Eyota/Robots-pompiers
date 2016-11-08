
package elements;

public class EventDeplacer extends Evenement{
    private Case position;
    private Robot robot;

    public EventDeplacer (int date, Robot robot, Direction dir){
        super(date);
        try {
      this.robot = robot;
      this.position = robot.getMap().getVoisin(robot.position, dir);
        }
        catch (UnreachableCaseException e){
            System.out.println("Cette case n'existe pas");
        }
    }

    public void execute(){
      //si les cases ne sont pas voisines
      if (!Case.sontVoisines(this.robot.getPosition(),this.position)){
        //exception deplacement impossible
      }
      else{
        //on met le robot sur sa nouvelle case
        this.robot.setPosition(this.position);
        
        }
      }
    
}
