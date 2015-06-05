package game_logic;
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
    public static final int CRUISER_COUNT = 2;
    public static final int DESTROYER_COUNT = 3;
    public static final int SUBMARINE_COUNT = 4;
    public static final int SHIPS_CELLS_COUNT = BATLESHIP_COUNT * Ship.BATTLESHIP_SIZE +
            CRUISER_COUNT * Ship.CRUISER_SIZE +
            DESTROYER_COUNT * Ship.DESTROYER_SIZE +
            SUBMARINE_COUNT * Ship.SUBMARINE_SIZE;

    //генеруємо поле бою гравця
    public BattleField(){
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
        //умова на кастомну чи автоматичну розстановку кораблів
        //тут додаємо лінкори
        //тут додаємо крейсери
        //тут додаємо есмінці
        //тут додаємо підводні човни
    }


    //генеруємо випадкові координати кораблів
    public static int getRandomCoordinate(){
        Random random = new Random();
        return random.nextInt(10);
    }

    //генеруємо випадкове положення кораблів
    public static boolean getRandomRotation(){
        Random random = new Random();
        return random.nextBoolean();
    }

    //тут потрібні методи для розрахунку координат клітинок корабля
    //в залежності від положення і координат першої клітинки

    //перевірка клітинок поля на наявність корабля
    private boolean shipInPlace(int x, int y, boolean rotation, int shipSize){
        int cx, cy;
        int i, j;

        if (rotation) {
            i = shipSize;
            j = 3;
        } else {
            i = 3;
            j = shipSize;
        }

        for(cx = -1; cx <= i; cx++ ){
            for(cy = -1; cy <= j; cy++){
                if((x + cx <= 0) && (x + cx >= 9) && (y + cy <= 0) && (y + cy >= 9)){
                    if(field[x + cx][y + cy].isShip())
                        return true;
                }
            }
        }
        //цикл перевірки
        return false;
    }

    //додаємо підводний човен
    public void addSubmarine(){
        int iteration = 0;
        while (iteration < SUBMARINE_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
            boolean rotation = getRandomRotation();
            if(!shipInPlace(x, y, rotation, Ship.SUBMARINE_SIZE)){
                addSubmarine(x,y);
                iteration++;
            }
        }
    }

    public void addDestroyer(){
        int iteration = 0;
        while (iteration < DESTROYER_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
        }
    }




    public void addSubmarine(int x, int y){
        Submarine submarine = new Submarine(x,y);
        field[x][y] = submarine.cells[0];
    }

    public void addDestroyer(int x1, int y1, int x2, int y2){
        Destroyer destroyer = new Destroyer(x1, y1, x2, y2);
        field[x1][y1] = destroyer.cells[0];
        field[x2][y2] = destroyer.cells[1];
    }

    public void addCruiser(int x1, int y1, int x2, int y2, int x3, int y3){
        Cruiser cruiser = new Cruiser(x1, y1, x2, y2, x3, y3);
        field[x1][y1] = cruiser.cells[0];
        field[x2][y2] = cruiser.cells[1];
        field[x3][y3] = cruiser.cells[2];
    }

    public void Battleship(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        Battleship battleship = new Battleship(x1, y1, x2, y2, x3, y3, x4, y4);
        field[x1][y1] = battleship.cells[0];
        field[x2][y2] = battleship.cells[1];
        field[x3][y3] = battleship.cells[2];
        field[x4][y4] = battleship.cells[3];
    }

}
