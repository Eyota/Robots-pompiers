
package elements;


public class EventRemplir extends Evenement{
    private Robot robot;

    public EventRemplir (int date, Robot robot){
      super(date);
      this.robot = robot;
    }

    public void execute(){
      //si le robot ne peut pas remplir
      if (!this.robot.peutRemplir()){
        //exception Evenement impossible
      }

      else{
        //on considere que le reservoir est toujours vide
        try {
        this.robot.remplirReservoir();
        this.date = this.date + this.robot.getTempsRemplissage()/3600;//en heure ???
        }
        catch(CaseWithoutWaterException c){
            System.out.println("Le robot ne peut pas se remplir ici");
        }
      }
    }

}
