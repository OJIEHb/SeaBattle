package swing_logic;

import game_logic.Cell;
import game_logic.BattleField;
import javax.swing.*;
import java.awt.*;


/**
 * Created by andrey on 07.06.15.
 */
public class SwingField extends JComponent {
    public static int SECTOR_COUNT = 10,
            SPACE_BETWEEN = 2,
            HEADER_SPACE = 16,
            HEIGHT = HEADER_SPACE + SPACE_BETWEEN * 2 + (Sector.HEIGHT + SPACE_BETWEEN) * SECTOR_COUNT,
            WIDTH = HEADER_SPACE + SPACE_BETWEEN * 2 + (Sector.WIDTH + SPACE_BETWEEN) * SECTOR_COUNT;;
    private Sector[][] sectors;
    private Sector selectedSector;
    private BattleField field;
    private SeaBattleSwing game;
    private boolean gameEnd = false, isOpen;

    public SwingField(boolean isOpen, SeaBattleSwing game) {
        this.isOpen = isOpen;
        this.game = game;

        sectors = new Sector[SECTOR_COUNT][SECTOR_COUNT];
        for(int i = 0; i < SECTOR_COUNT; i++)
            for(int j = 0; j < SECTOR_COUNT; j++)
                sectors[i][j] = Sector.sectorCreate(this, i,j);

        selectedSector = null;

        setSize(WIDTH, HEIGHT);

        printHeader();
        //додати шапку(цифри, букви)
    }
    public SeaBattleSwing getGame() {return this.game;}

    public Sector[][] getSectors() {return this.sectors;}

    public BattleField getBattleField() {return this.field; }

    public void setBattleField(BattleField field) {this.field = field;}

    public void setSelected(Sector sector) {
        selectedSector = sector;
        repaint();
    }

    public BattleField getField() {return this.field;}
    public boolean isOpen() {return isOpen;}

    public void setGameEnd() {
        this.gameEnd = true;
    }

    private void paintWinOrLoseMessage(Graphics2D g2d) {
        if(isOpen) {
            g2d.setColor(Color.red);
            g2d.scale(10, 10);
            g2d.drawString("LOSE", 1, 15);
        }
        else {
            g2d.setColor(Color.green);
            g2d.scale(10, 10);
            g2d.drawString("WIN", 1, 15);
        }
    }

    private void paintGradientBoxForFieldBottom(Graphics2D g) {
        int lw = getWidth() - 1;
        int lh = getHeight() - 1;
        int rw = SPACE_BETWEEN * 2;
        int rh = SPACE_BETWEEN * 2;
        g.setPaint(new GradientPaint(lw, lh, Color.white,
                lw / 4, lh / 4, Color.lightGray, true));
        g.fillRoundRect(0, 0, lw, lh, rw, rh);
        g.setColor(Color.lightGray);
        g.drawRoundRect(0, 0, lw, lh, rw, rh);
    }

    private void paintCurrentSelectedElement(Graphics2D g2d) {
        if(selectedSector != null) {
            int spx = selectedSector.getLocation().x;
            int spy = selectedSector.getLocation().y;
            int slw = selectedSector.getWidth() - 1;
            int slh = selectedSector.getHeight() - 1;
            int srw = SPACE_BETWEEN * 2;
            int srh = SPACE_BETWEEN * 2;
            g2d.setPaint(new GradientPaint(spx + slw, SPACE_BETWEEN + HEADER_SPACE, Color.white,
                    spx + slw / 4, SPACE_BETWEEN + HEADER_SPACE / 4, Color.green, true));
            g2d.fillRoundRect(spx, SPACE_BETWEEN, slw, HEADER_SPACE, srw, srh);
            g2d.setPaint(new GradientPaint(SPACE_BETWEEN + HEADER_SPACE, spy + slh, Color.white,
                    SPACE_BETWEEN + HEADER_SPACE / 4, spy + slh / 4, Color.green, true));
            g2d.fillRoundRect(SPACE_BETWEEN, spy,HEADER_SPACE, slh, srw, srh);
            g2d.setColor(Color.black);
            g2d.drawRoundRect(spx, SPACE_BETWEEN, slw, HEADER_SPACE, srw, srh);
            g2d.drawRoundRect(SPACE_BETWEEN, spy, HEADER_SPACE, slh, srw, srh);
        }
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        if(gameEnd) {
            paintGradientBoxForFieldBottom(g2d);
            paintWinOrLoseMessage(g2d);
        } else {
            paintGradientBoxForFieldBottom(g2d);
            paintCurrentSelectedElement(g2d);
            super.paint(g);
        }
    }



    public void printField() {
        Cell[][] cells = this.field.getFieldMap();
        for(int i = 0; i < SECTOR_COUNT; i++) {
            for(int j = 0; j < SECTOR_COUNT; j++) {
                if(cells[i][j].isShip()) {
                    sectors[i][j].setShip();
                    sectors[i][j].repaint();
                } else if(cells[i][j].isFired()) {
                    sectors[i][j].setAttacked();
                }
            }
        }
    }

    private void printHeader() {
        char[] letters = {'Р', 'Е', 'С', 'П', 'У', 'Б', 'Л', 'І', 'К', 'А'};
        for(int i = 0; i < SECTOR_COUNT; i++) {
            JLabel horizontal = new JLabel(String.valueOf(i + 1));
            if(i < letters.length)
                horizontal.setText(String.valueOf(letters[i]));
            JLabel vertical = new JLabel(String.valueOf(i + 1));
            horizontal.setSize(Sector.WIDTH, HEADER_SPACE);
            vertical.setSize(HEADER_SPACE, Sector.HEIGHT);
            horizontal.setLocation(5+HEADER_SPACE + SPACE_BETWEEN * 2 + (Sector.WIDTH + SPACE_BETWEEN) * i, SPACE_BETWEEN);
            vertical.setLocation(SPACE_BETWEEN, HEADER_SPACE + SPACE_BETWEEN * 2 + (Sector.HEIGHT + SPACE_BETWEEN) * i);
            add(horizontal);
            add(vertical);
        }
    }


}
