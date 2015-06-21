import game.logic.SeaBattle;
import swing.logic.SeaBattleSwing;

import javax.swing.*;

/**
 * Created by andrey on 04.06.15.
 */
public class SeaBattleMain {

    public static void main(String[] args) {
        final SeaBattle game = new SeaBattle();

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                SeaBattleSwing seaBattle = new SeaBattleSwing();
                seaBattle.drawGameField(game.getPlayerField(), game.getComputerField());
                game.setSeaBattleSwing(seaBattle);
            }
        });

        game.play();

    }



}