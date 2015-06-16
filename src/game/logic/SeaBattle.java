package game.logic;

import swing.logic.SeaBattleSwing;
import swing.logic.Sector;
import swing.logic.SwingField;

import java.util.concurrent.TimeUnit;
/**
 * Created by andrey on 07.06.15.
 */
public class SeaBattle{
    public  static final int PAUSE = 10;
    private BattleField playerFieldMap, computerFieldMap;
    private SeaBattleSwing seaBattleSwing;
    public static boolean userShooting, userKilled;

    public SeaBattle() {
        playerFieldMap = new BattleField();
        computerFieldMap = new BattleField();
    }

    public void setSeaBattleSwing(SeaBattleSwing seaBattleSwing){this.seaBattleSwing = seaBattleSwing;}

    public BattleField getPlayerField(){return this.playerFieldMap;}

    public BattleField getComputerField() {return computerFieldMap;}

    public static void waitUserAttack() {
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!userShooting);
    }

    public boolean checkWin(Cell[][] map){
        int firedCells = 0;
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Cell cell = map[i][j];
                if(cell.isShip() && cell.isFired()){
                    firedCells++;
                    if(firedCells == BattleField.SHIPS_CELLS_COUNT){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean computerAttack(){
        int x = BattleField.getRandomCoordinate();
        int y = BattleField.getRandomCoordinate();

        SwingField playerSwingField = seaBattleSwing.getPlayerField();
        Sector sector = playerSwingField.getSectors()[x][y];

        Cell[][] cells = playerFieldMap.getFieldMap();
        Cell cell = cells[x][y];

        while (cell.isFired()){
            x = BattleField.getRandomCoordinate();
            y = BattleField.getRandomCoordinate();
            cell = cells[x][y];
        }
        cell.setWasFired();

        sector.setAttacked();
        if (cell.isShip()) {
            sector.setShip();
        }

        sector.repaint();

        return cell.isShip();
    }

    public void play(){
        boolean userWin = false;
        boolean computerWin = false;

        do{
            userShooting = false;
            waitUserAttack();

            userWin = checkWin(computerFieldMap.getFieldMap());
            if (userWin){
                continue;
            }

            if(userKilled){
                userKilled = false;
                continue;
            }
            while (computerAttack()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(PAUSE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            computerWin = checkWin(playerFieldMap.getFieldMap());



        } while(!userWin && !computerWin);

        if (computerWin) {
            showWinMessageBox("Ви програли", false);
        } else {
           showWinMessageBox("Ви виграли", true);
        }

    }
    private void showWinMessageBox(String message, boolean userWin) {
        if (userWin) {
            seaBattleSwing.getComputerField().setGameEnd();
            seaBattleSwing.getComputerField().repaint();
        } else {
            SwingField playerSwingField = seaBattleSwing.getPlayerField();
            playerSwingField.setGameEnd();
            playerSwingField.repaint();
        }

       /** Object[] options = {"Так", "Ні"};
        int result = JOptionPane.showOptionDialog(seaBattleSwing.getComputerField().getParent(), "Розпочати нову гру?", message,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (result == 0) {
/// можливо перезапуск гри
        }**/

    }

}
