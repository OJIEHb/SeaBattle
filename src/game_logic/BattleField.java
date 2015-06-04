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


    public BattleField(){
        // додати в метод параметр boolean для кастомної розстановки кораблів
        generateBattleField();
    }
    // генеруємо поле бою гравця
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

    //перевірка клітинок поля на наявність корабля
    private boolean shipInPlace(int x, int y, boolean rotation, int shipSize){
        return false;
    }

    //додаємо підводний човен
    public void addSubmarine(){
        int iteration = 0;
        while (iteration < SUBMARINE_COUNT){
            int x = getRandomCoordinate();
            int y = getRandomCoordinate();
        }
    }

}
