package Modèle;

import javax.swing.ImageIcon;

public class CarteTresor {
    private TypeCarte type;
    private ImageIcon image;
    
    public CarteTresor(TypeCarte type) {
        this.setType(type);
        image = new ImageIcon(this.getClass().getResource("/ImagesCartesTresor/"+type.name()+".png"));
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

    /**
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }
}