package Modèle;

public class Coordonees {

	private int x;
	private int y;
        public Coordonees(Integer x, Integer y) {
            this.setX(x);
            this.setY(y);
        }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    private void setY(int y) {
        this.y = y;
    }

}