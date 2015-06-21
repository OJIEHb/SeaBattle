package game.logic;

/**
 * Created by andrey on 04.06.15.
 * Корабель, що займає 1 клітинку
 */
public class Submarine extends Ship {
    Submarine(int x, int y) {
        cells = new Cell[SUBMARINE_SIZE];
        cells[0] = new Cell(x, y, this);
    }
}
