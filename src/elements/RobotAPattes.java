
package elements;

public class RobotAPattes extends Robot{
	private final static int capacite = Integer.MAX_VALUE;
	private final static double vitesse = 30;
	private final static double vitesseIntervention=10;
	
	public RobotAPattes(Carte carte){
		super(carte, capacite, 0, vitesseIntervention, vitesse);
	}
	
	@Override
    public void setPosition(Case C) throws UnreachableCaseException, WrongCaseNatureException{
        if (C.getNature() == NatureTerrain.EAU) {
            throw new WrongCaseNatureException();
        } else {
            while (C != super.position) {
                for (Case Voisin : map.ListeVoisins(super.position)) {
                    if (C == Voisin) {
                        super.position = C;
                    }
                }
                throw new UnreachableCaseException();
        }

		}
    }
    
    @Override
    public double getVitesse(NatureTerrain T) {
        if (T == NatureTerrain.EAU) {
            return 0;
        }
        if (T == NatureTerrain.ROCHE) {
            return 10;
        } else {
            return vitesse;
        }
    }

    @Override
    public void deverserEau(int Volume) { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
		int x=1;
    }

    @Override
    public void remplirReservoir() { //le robot a de la poudre dans le r�servoir, et donc un r�servoir infini
    }
        
        @Override
        public boolean peutRemplir(){
            return false;
        }
	
}