package Modèle;

public class CarteSpeciale extends CarteTresor {

    private TypeSpé type;
    public CarteSpeciale(TypeSpé type) {
        super();
        this.setType(type);
    }

    private void setType(TypeSpé type) {
        this.type = type;
    }

}