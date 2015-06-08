package swing_logic;

import game_logic.Cell;
import game_logic.SeaBattle;
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
        setLocation(SwingField.HEADER_SPACE + SwingField.SPACE_BETWEEN * 2+(WIDTH + SwingField.SPACE_BETWEEN)* x,
                SwingField.HEADER_SPACE + SwingField.SPACE_BETWEEN * 2 + (HEIGHT + SwingField.SPACE_BETWEEN)* y);
        addMouseListener(this);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
