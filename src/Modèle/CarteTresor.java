package Modèle;

public class CarteTresor {
    private TypeCarte type;
    public CarteTresor(TypeCarte type) {
        this.setType(type);
    }

    /**
     * @return the type
     */
    public TypeCarte getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    private void setType(TypeCarte type) {
        this.type = type;
    }
}