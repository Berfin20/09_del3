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
    void testChancecardField(){
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        Chancecard chancecard = new Chancecard();
        ArrayList<Chancecard> chancecards = chancecard.getAllChancecards();
        GUI gui = new GUI(fields, Color.pink.darker());

        GUI_Player ali = new GUI_Player("ali",10);
        gui.addPlayer(ali);

        Player ali2 = new Player("ali", ali.getBalance(), 0);
        int position;
        int chancecardIndex = 3;

        position = 0;
        gui.getFields()[position].setCar(ali,true);
        gui.getFields()[position].setCar(ali,false);
        position = 3;
        gui.getFields()[position].setCar(ali, true);

        if (ali2.getPosition() == 3 || ali2.getPosition() == 9 || ali2.getPosition() == 15 || ali2.getPosition() == 21){
            gui.setChanceCard(chancecards.get(chancecardIndex).getDescription());
            gui.displayChanceCard();
        }

        String titel = chancecards.get(chancecardIndex).getDescription();

        assertEquals("Ryk 1 felt frem, ELLER tag et chancekort mere", titel);

    }

    @Test
    void testMoveGuiPlayer() {
        GameBoard gameboard = new GameBoard();
        GUI_Field[] fields = gameboard.setFields();
        GUI gui = new GUI(fields, Color.pink.darker());

        GUI_Player ali = new GUI_Player("ali",10);
        gui.addPlayer(ali);
        int position;

        position = 0;
        gui.getFields()[position].setCar(ali,true);
        gui.getFields()[position].setCar(ali,false);
        position = 2;
        gui.getFields()[position].setCar(ali, true);

        String test =  fields[position].getTitle();

        assertEquals("PIZZERIA",test,"Not a match");
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