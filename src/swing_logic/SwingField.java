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
        //додати шапку(цифри, букви)
    }
}
