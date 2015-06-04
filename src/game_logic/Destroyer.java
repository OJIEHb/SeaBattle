package game_logic;

/**
 * Created by andrey on 04.06.15.
 * Корабель, що займає 2 клітинки
 */
public class Destroyer extends Ship {
    Destroyer(int x1, int y1, int x2, int y2) {
        cells = new Cell[DESTROYER_SIZE];
        cells[0] = new Cell(x1, y1, this);
        cells[1] = new Cell(x2, y2, this);
    }
}
