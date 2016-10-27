
public class DonneesSimulation{
	private Carte carte;
	private	List<Incendie> incendies;
	private List<Robot> robots;

	public DonneesSimulation(Carte map, List<Incendie> listeFeux, List<Robot> listeRobots){
		this.carte = map;
		this.incendies = listeFeux;
		this.robots = listeRobots;
	}
	
	/*public void setCarte(Carte map){
		this.carte = map;
	}*/
	
	
}