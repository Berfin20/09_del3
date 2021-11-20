import gui_fields.GUI_Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void setFields() {
        GameBoard gameBoard = new GameBoard();
        GUI_Field[] test = gameBoard.allFields;

        assertEquals(test, gameBoard.setFields());
    }
}