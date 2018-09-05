package droid.yutani.com.conwaysgameoflife;

public class Cell {

    public int xx;
    public int yy;
    public boolean isAlive;

    public Cell(int xx, int yy, boolean isAlive) {
        this.xx = xx;
        this.yy = yy;
        this.isAlive = isAlive;
    }

    public void setDead() {
        isAlive = false;
    }

    public void setAlive() {
        isAlive = true;
    }

    public void switchState() {
        isAlive = !isAlive;
    }
}
