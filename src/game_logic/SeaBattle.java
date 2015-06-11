package game_logic;

import swing_logic.SeaBattleSwing;
import swing_logic.Sector;
import swing_logic.SwingField;
import javax.swing.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
/**
 * Created by andrey on 07.06.15.
 */
public class SeaBattle{
    public  static final int PAUSE = 1;
    private BattleField playerFieldMap, computerFieldMap;
    private SeaBattleSwing seaBattleSwing;
    public static boolean userShooting, userKilled;

    public SeaBattle() {
        playerFieldMap = new BattleField();
        computerFieldMap = new BattleField();
    }

    public void setSeaBattleSwing(SeaBattleSwing seaBattleSwing){this.seaBattleSwing = seaBattleSwing;}

    public SeaBattleSwing getSeaBattleSwing(){ return seaBattleSwing;}

    public BattleField getPlayerField(){return this.playerFieldMap;}

    public BattleField getComputerFieldMap() {return computerFieldMap;}

    public static void waitUserAttack() {
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!userShooting);
    }

    public void play(){
        boolean userWin = false;
        boolean computerWin = false;

        do{
            userShooting = false;
            waitUserAttack();
        }while(!userWin && !computerWin);
    }

}
