package swing.logic;

import game.logic.Cell;
import game.logic.SeaBattle;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
/**
 * Created by andrey on 07.06.15.
 */
public class Sector extends Component implements MouseListener{
    public static int WIDTH = 20,HEIGHT = 20;
    private boolean isSelected,isAttacked,isShip;
    private int x,y;
    private SwingField field;

    private Sector(SwingField field, int x, int y){
        isSelected = false;
        isAttacked = false;
        this.x = x;
        this.y = y;
        this.field = field;
        this.field.add(this);
        setSize(WIDTH, HEIGHT);
        setLocation(SwingField.HEADER_SPACE + SwingField.SPACE_BETWEEN * 2 + (WIDTH + SwingField.SPACE_BETWEEN) * x,
                SwingField.HEADER_SPACE + SwingField.SPACE_BETWEEN * 2 + (HEIGHT + SwingField.SPACE_BETWEEN) * y);
        addMouseListener(this);
    }

    //створюємо сектор
    public static  Sector sectorCreate(SwingField field, int x, int y){
        if(field != null && 0<= x && x < SwingField.SECTOR_COUNT && 0 <= y && y < SwingField.SECTOR_COUNT)
            return new Sector(field, x, y);
        else
            return null;
    }

    public void setShip() {isShip = true;}

    public boolean isShip() {return isShip;}

    //Промальовуємо сектор(клітинку)
    private void draw(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        //малюємо поля
        if(field.isOpen()) {
            Cell cell = field.getField().getFieldMap()[x][y];
            if(isShip() && cell.getShip().shipIsDead()) {
                draw(g2d, Color.BLACK);
            } else if(isShip() && isAttacked) {
                draw(g2d, Color.red);
            } else if(isShip()){
                draw(g2d, Color.green);
            } else if(isAttacked) {
                draw(g2d, Color.cyan);
                g2d.setColor(Color.black);
                g2d.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
            } else {
                draw(g2d, Color.cyan);
            }
        } else {
            if(isSelected && !isAttacked) {
                draw(g2d, Color.cyan);
                g2d.drawLine(0, getHeight() / 2, getWidth() - 1, getHeight() / 2);
                g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight() - 1);
                g2d.drawOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
                //draw(g2d, Color.GREEN);

            } else if(isAttacked ){
                Cell cell = field.getField().getFieldMap()[x][y];
                if(isShip() && cell.getShip().shipIsDead()) {
                    draw(g2d, Color.black);
                } else if(isShip()) {
                    draw(g2d, Color.red);
                } else {
                    draw(g2d, Color.cyan);
                    g2d.setColor(Color.black);
                    g2d.fillOval(getWidth()/4,getHeight()/4,getWidth()/2,getHeight()/2);
                }

            } else {
                draw(g2d, Color.cyan);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!field.isOpen()) {

            if(!isAttacked) {
                setAttacked();
            }

            Cell[][] cells = field.getField().getFieldMap();
            Cell cell = cells[x][y];
            cell.setWasFired();

            field.setSelected(null);
            if (cell.isShip()) {
                setShip();
                SeaBattle.userKilled = true;
            }

            repaint();
            SeaBattle.userShooting = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!field.isOpen()) {
            isSelected = true;
            field.setSelected(this);
            repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!field.isOpen()) {
            isSelected = false;
            field.setSelected(null);
            repaint();
        }
    }
    public void setAttacked() {this.isAttacked = true;}

}
