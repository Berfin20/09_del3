import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonopolyJuniorTest {

    @Test
    void testChancecardField() {
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        Chancecard chancecard = new Chancecard();
        Chancecard[] chancecards = chancecard.getAllChancecards();
        GUI gui = new GUI(fields, Color.pink.darker());

        GUI_Player ali = new GUI_Player("ali", 10);
        gui.addPlayer(ali);

        Player ali2 = new Player("ali", ali.getBalance(), 0);
        int position;
        int chancecardIndex = 3;

        position = 0;
        gui.getFields()[position].setCar(ali, true);
        gui.getFields()[position].setCar(ali, false);
        position = 3;
        gui.getFields()[position].setCar(ali, true);

        if (ali2.getPosition() == 3 || ali2.getPosition() == 9 || ali2.getPosition() == 15 || ali2.getPosition() == 21) {
            gui.setChanceCard(chancecards[chancecardIndex].getDescription());
            gui.displayChanceCard();
        }

        String titel = chancecards[chancecardIndex].getDescription();

        assertEquals("Ryk 1 felt frem, ELLER tag et chancekort mere", titel);

    }

    @Test
    void testMoveGuiPlayer() {
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        GUI gui = new GUI(fields, Color.pink.darker());

        GUI_Player ali = new GUI_Player("ali", 10);
        gui.addPlayer(ali);
        int position;

        position = 0;
        gui.getFields()[position].setCar(ali, true);
        gui.getFields()[position].setCar(ali, false);
        position = 2;
        gui.getFields()[position].setCar(ali, true);

        String test = fields[position].getTitle();

        assertEquals("PIZZERIA", test, "Not a match");
    }

    //Test for at summen af et terningekast bliver en spillers position.
    @Test
    void playerTurnFlow() throws InterruptedException {
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        GUI gui = new GUI(fields, Color.pink.darker());
        DicePair dicePair = new DicePair(0,0);

        ArrayList<GUI_Player> guiPlayers = new ArrayList<>();
        GUI_Player payam = new GUI_Player("payam");
        guiPlayers.add(payam);

        ArrayList<Player> players = new ArrayList<>();
        Player payam2 = new Player("payam", 10, 0);
        players.add(payam2);
        dicePair.die1.setFaceValue(2);
        dicePair.die2.setFaceValue(3);

        dicePair.setFaceValueSum(dicePair.die1.getFaceValue()+dicePair.die2.getFaceValue());
        gui.setDice(dicePair.die1.getFaceValue(), dicePair.die2.getFaceValue());

        TimeUnit.MILLISECONDS.sleep(500);

        payam2.setPosition(dicePair.getFaceValueSum());
        gui.getFields()[payam2.getPosition()].setCar(payam, true);
        String test =  fields[payam2.getPosition()].getTitle();

            assertEquals("ISKIOSKEN",test,"Not a match");

        }

    }


    //Lav mindst tre testcases med tilh??rende fremgangsm??de/testprocedure og testrapporter.
    //Lav mindst ??n Junit test til centrale metoder. Inklud??r code coverage dokumentation.
    //Lav mindst ??n brugertest. Husk at brugeren skal v??re en der ikke kan kode.