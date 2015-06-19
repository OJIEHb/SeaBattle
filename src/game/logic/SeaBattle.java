package game.logic;

import swing.logic.SeaBattleSwing;
import swing.logic.Sector;
import swing.logic.SwingField;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * Created by andrey on 07.06.15.
 */
public class SeaBattle{
    public  static final int PAUSE = 10;
    private BattleField playerFieldMap, computerFieldMap;
    private SeaBattleSwing seaBattleSwing;
    public static boolean userShooting, userKilled;
    private int firedX = -1, firedY = -1;
    private Random random = new Random();

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
        int x , y;
        Cell[][] cells = playerFieldMap.getFieldMap();

        if(firedX !=-1 && firedY != -1&& !cells[firedX][firedY].getShip().shipIsDead()){
            ArrayList<Cell> list = calculateCoordinateStrickenCell(firedX, firedY);
            int number = random.nextInt(list.size());
            Cell cell = list.get(number);
            x = cell.x;
            y = cell.y;
            list.remove(number);

        }else {
            do {
                x = BattleField.getRandomCoordinate();
                y = BattleField.getRandomCoordinate();
            }while(cells[x][y].isFired());
        }
        SwingField playerSwingField = seaBattleSwing.getPlayerField();
        Sector sector = playerSwingField.getSectors()[x][y];
        Cell cell = cells[x][y];
        if(cell.isShip()){
            firedX = cell.x;
            firedY = cell.y;
            if(cell.getShip().shipIsDead()) {
                firedX = -1;
                firedY = -1;
            }
        }
        cell.setWasFired();
        sector.setAttacked();
        if (cell.isShip()) {
            sector.setShip();
        }
        sector.repaint();
        return cell.isShip();
    }

    public ArrayList<Cell> calculateCoordinateStrickenCell(int x, int y) {
        Cell[][] cells = playerFieldMap.getFieldMap();
        ArrayList<Cell> list = new ArrayList<Cell>();
        if (x < 9 && !checkCellUp(x,y-1,y) && !checkCellDown(x,y+1,y)){
            if (!cells[x + 1][y].isFired()) {
                list.add(cells[x + 1][y]);
            }
            if (checkCellDown(x+1, y, x)){
                if(cells[x+2][y].isFired() && x+3 < 9){
                    list.add(cells[x+3][y]);
                }
                else {
                    list.add(cells[x + 2][y]);
                }
            }
        }

        if (x > 0 && !checkCellUp(x,y-1,y) && !checkCellDown(x,y+1,y)){
            if(!cells[x-1][y].isFired() ){
                list.add(cells[x-1][y]);
            }
            if(checkCellUp(x-1,y,x)){
                if(cells[x-2][y].isFired()&& x-3 >0){
                    list.add(cells[x-3][y]);
                }
                else {
                    list.add(cells[x - 2][y]);
                }
            }
        }
        if (y > 0 && !checkCellUp(x-1,y,x) && !checkCellDown(x + 1, y, x)){
            if(!cells[x][y-1].isFired()){
                list.add(cells[x][y-1]);
            }
            if(checkCellUp(x,y-1,y)){
                if(cells[x][y-2].isFired()&& y-3 >0){
                    list.add(cells[x][y-3]);
                }
                else {
                    list.add(cells[x][y - 2]);
                }
            }
        }
        if (y < 9 && !checkCellUp(x-1,y,x) && !checkCellDown(x+1,y,x)){
            if(!cells[x][y+1].isFired()){
                list.add(cells[x][y+1]);
            }
            if (checkCellDown(x, y+1, y)){
                if(cells[x][y+2].isFired() && y+3 < 9){
                    list.add(cells[x][y+3]);
                }
                else {
                    list.add(cells[x ][y+ 2]);
                }
            }
        }
        return list;
    }
    public boolean checkCellDown(int x,int y, int checkOrdinate){
        Cell[][] cells = playerFieldMap.getFieldMap();
        try {
            if (cells[x][y].isFired() && cells[x][y].isShip() && checkOrdinate + 2 < 9) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){return false;}
    }
    public boolean checkCellUp(int x,int y, int checkOrdinate){
        Cell[][] cells = playerFieldMap.getFieldMap();
        try{
        if (cells[x][y].isFired() && cells[x][y].isShip() && checkOrdinate-2 > 0){
            return true;
        }else {
            return false;
        }
        }catch (Exception e){return false;}
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
