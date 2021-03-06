package game.logic;

import java.util.Random;
/**
 * Created by andrey on 04.06.15.
 * Ігрове поле для одного гравця
 */
public class BattleField {
    public static final int ROW_COUNT = 10;
    public static final int COL_COUNT = 10;

    private Cell[][] field = new Cell[ROW_COUNT][COL_COUNT];
    public static final int BATLESHIP_COUNT = 1;
    public static final int CRUISER_COUNT =2;
    public static final int DESTROYER_COUNT = 3;
    public static final int SUBMARINE_COUNT = 4;
    public static final int SHIPS_CELLS_COUNT = BATLESHIP_COUNT * Ship.BATTLESHIP_SIZE +
            CRUISER_COUNT * Ship.CRUISER_SIZE +
            DESTROYER_COUNT * Ship.DESTROYER_SIZE +
            SUBMARINE_COUNT * Ship.SUBMARINE_SIZE;
    public BattleField() {
        generateBattleField();
    }

    //метод генерації поля
    public void generateBattleField(){
        // додати в метод параметр boolean для кастомної розстановки кораблів
        for(int i = 0; i < COL_COUNT; i++){
            for(int j = 0; j < ROW_COUNT; j++){
                Cell cell = new Cell(i, j, null);
                field[i][j] = cell;
            }
        }
        randomAddingBattleship();
        //тут додаємо лінкори
        randomAddingCruiser();
        //тут додаємо крейсери
        randomAddingDestroyer();
        //тут додаємо есмінці
        randomAddingSubmarine();
        //тут додаємо підводні човни
    }

    //перевірка клітинок поля на наявність корабля
    private boolean shipInPlace(int x, int y, boolean rotation, int boatSize) {
        int cx, cy;
        int i, j;

        if (rotation) {
            i = boatSize;
            j = 3;
        } else {
            i = 3;
            j = boatSize;
        }

        for (cx = -1; cx <= i; cx++)
            for (cy = -1; cy <= j; cy++) {
                if ((x + cx) >= 0 && (x + cx) <= 9 && (y + cy) >= 0 && (y + cy) <= 9) {
                    if (field[x + cx][y + cy].isShip()) {
                        return true;
                    }
                }
            }

        return false;
    }


    //генеруємо випадкові координати кораблів
    public static int getRandomCoordinate() {
        Random random = new Random();
        return random.nextInt(10);
    }

    //генеруємо випадкове положення кораблів
    private static boolean getRandomRotation() {
        return new Random().nextBoolean();
    }

    //додаємо підводний човен
    public void randomAddingSubmarine(){
        int iteration = 0;
        while (iteration < SUBMARINE_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            boolean rotation = getRandomRotation();
            if(!shipInPlace(x, y, rotation, Ship.SUBMARINE_SIZE)){
                createSubmarine(x, y);
                iteration++;
            }
        }
    }


    public void randomAddingDestroyer(){
        int iteration = 0;
        while (iteration < DESTROYER_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            boolean rotation = getRandomRotation();
            // тут використати методи для розрахунку координат
            int x1 = calculateXCoordinate(x, rotation);
            int y1 = calculateYCoordinate(y, rotation);
            if ((x1 != 10) && (y1 != 10))
                if (!shipInPlace(x, y, getRandomRotation(), Ship.DESTROYER_SIZE)) {
                    createDestroyer(x, y, x1, y1);
                    iteration++;
                }
        }
    }


    public void randomAddingCruiser(){
        int iteration = 0;
        while (iteration < CRUISER_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            boolean rotation = getRandomRotation();
            int x1 = calculateXCoordinate(x, rotation);
            int y1 = calculateYCoordinate(y, rotation);
            int x2 = calculateXCoordinate(x1, rotation);
            int y2 = calculateYCoordinate(y1, rotation);
            if ((x1 != 10) && (y1 != 10) & (x2 != 10) && (y2 != 10)) {
                if (!shipInPlace(x, y, rotation, Ship.CRUISER_SIZE)) {
                    createCruiser(x, y, x1, y1, x2, y2);
                    iteration++;
                }
            }
        }
    }


    public void randomAddingBattleship(){
        int iteration = 0;
        while (iteration < BATLESHIP_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            boolean rotation = getRandomRotation();
            int x1 = calculateXCoordinate(x, rotation);
            int y1 = calculateYCoordinate(y, rotation);
            int x2 = calculateXCoordinate(x1, rotation);
            int y2 = calculateYCoordinate(y1, rotation);
            int x3 = calculateXCoordinate(x2, rotation);
            int y3 = calculateYCoordinate(y2, rotation);
            if ((x1 != 10) && (y1 != 10) && (x2 != 10) && (y2 != 10) && (x3 != 10) && (y3 != 10)) {
                if (!shipInPlace(x, y, rotation, Ship.BATTLESHIP_SIZE)) {
                    createBattleship(x, y, x1, y1, x2, y2, x3, y3);
                    iteration++;
                }
            }
        }
    }

    public void createSubmarine(int x, int y) {
        Submarine p1 = new Submarine(x, y);
        field[x][y] = p1.cells[0];
    }

    public void createDestroyer(int x1, int y1, int x2, int y2) {
        Destroyer cr1 = new Destroyer(x1, y1, x2, y2);
        field[x1][y1] = cr1.cells[0];
        field[x2][y2] = cr1.cells[1];
    }

    public void createCruiser(int x1, int y1, int x2, int y2, int x3, int y3) {
        Cruiser dr1 = new Cruiser(x1, y1, x2, y2, x3, y3);
        field[x1][y1] = dr1.cells[0];
        field[x2][y2] = dr1.cells[1];
        field[x3][y3] = dr1.cells[2];
    }

    public void createBattleship(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Battleship bs1 = new Battleship(x1, y1, x2, y2, x3, y3, x4, y4);
        field[x1][y1] = bs1.cells[0];
        field[x2][y2] = bs1.cells[1];
        field[x3][y3] = bs1.cells[2];
        field[x4][y4] = bs1.cells[3];
    }

    //тут потрібні методи для розрахунку координат клітинок корабля
    //в залежності від положення і координат першої клітинки
    public int calculateXCoordinate(int x1, boolean rotation) {
        int x2;

        if (!rotation) {
            x2 = x1;
        } else {
            x2 = x1 + 1;
        }

        if ((x2 < 0) || (x2 > 9)) {
            return 10;
        } else {
            return x2;
        }
    }

    public int calculateYCoordinate(int y1, boolean rotation) {
        int y2;

        if (!rotation) {
            y2 = y1 + 1;
        } else {
            y2 = y1;
        }

        if ((y2 < 0) || (y2 > 9)) {

            return 10;
        } else {
            return y2;
        }
    }

    public Cell[][] getFieldMap() {
        return field;
    }

}
