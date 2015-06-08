package swing_logic;

import game_logic.BattleField;
import javax.swing.*;

/**
 * Created by andrey on 07.06.15.
 */
public class SeaBattleSwing extends JFrame {
    private SwingField playerField, computerField;

    public SeaBattleSwing() {
        setTitle("Морський бій ’’Республіка’’");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(SwingField.WIDTH * 2 + 37, SwingField.HEIGHT + 68);
        playerField =  new SwingField(true, this);
        computerField = new SwingField(false, this);
        add(playerField);
        add(computerField);
        playerField.setLocation(10, 10);
        computerField.setLocation(SwingField.WIDTH + 20, 10);
        setLayout(null);
        setVisible(true);
    }
}
