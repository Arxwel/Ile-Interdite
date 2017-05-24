package Modèle;

public class CarteInondation {

	private Tuile tuile;
        
        public CarteInondation(Tuile tuile) {
            setTuile(tuile);
        }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * @param tuile the tuile to set
     */
    private void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

}