package game.logic;

/**
 * Created by andrey on 04.06.15.
 * Клітинка поля бою
 */
public class Cell {
    protected int x, y;
    private Ship ship;
    private boolean wasFired = false;

    Cell(int x, int y, Ship ship) {
        this.x = x;
        this.y = y;
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void setWasFired() {
        this.wasFired = true;
    }

    public boolean isFired() {
        return wasFired;
    }

    public boolean isShip() {
        return ship != null;
    }

}
