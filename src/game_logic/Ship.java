package game_logic;

/**
 * Created by andrey on 04.06.15.
 * Корабель
 */

public class Ship {
    public static final int SUBMARINE_SIZE = 1;
    public static final int CRUISER_SIZE = 2;
    public static final int DESTROYER_SIZE = 3;
    public static final int BATTLESHIP_SIZE = 4;
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

