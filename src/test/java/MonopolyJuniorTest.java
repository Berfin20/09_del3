import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonopolyJuniorTest {

    @Test
    void createPlayers() {
    }

    @Test
    void setStartBalance() {
    }

    @Test
    void displayRandomChancecard() {
    }

    @Test
    void playerBankrupt() {
    }

    @Test
    void findPlayer() {

    }

    // Test for at summen af et terningekast bliver en spillers position.
    @Test
    void playerTurnFlow() throws InterruptedException {
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        GUI gui = new GUI(fields, Color.pink.darker());
        Die die1 = new Die();
        Die die2 = new Die();
        DicePair dicePair = new DicePair(die1, die2);

        ArrayList<GUI_Player> guiPlayers = new ArrayList<>();
        GUI_Player payam = new GUI_Player("payam");
        guiPlayers.add(payam);

        ArrayList<Player> players = new ArrayList<>();
        Player payam2 = new Player("payam", 10, 0);
        players.add(payam2);
        die1.setFaceValue(2);
        die2.setFaceValue(3);

        dicePair.setFaceValueSum(die1.getFaceValue()+die2.getFaceValue());

        gui.setDice(die1.getFaceValue(), die2.getFaceValue());

        TimeUnit.MILLISECONDS.sleep(500);

        payam2.setPosition(dicePair.getFaceValueSum());
        gui.getFields()[payam2.getPosition()].setCar(payam, true);
        String test =  fields[payam2.getPosition()].getTitle();

            assertEquals("ISKIOSKEN",test,"Not a match");

        }

    }

    //Lav mindst tre testcases med tilhørende fremgangsmåde/testprocedure og testrapporter.
    //Lav mindst én Junit test til centrale metoder. Inkludér code coverage dokumentation.
    //Lav mindst én brugertest. Husk at brugeren skal være en der ikke kan kode.