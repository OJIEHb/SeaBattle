package game.logic;

/**
 * Created by andrey on 04.06.15.
 * Корабель
 */

public class Ship {
    public static final int SUBMARINE_SIZE = 1,
            DESTROYER_SIZE = 2,
            CRUISER_SIZE = 3,
            BATTLESHIP_SIZE = 4;
    public static boolean isLife = true;

    protected Cell[] cells;//Клітинки, що займає корабель

    public boolean shipIsDead() {
        for(Cell cell : cells) {
            if(!cell.isFired()) {
                return false;
            }
        }

        return true;
    }//Метод знищення корабля

}

