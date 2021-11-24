import gui_fields.*;
import java.awt.*;
import java.util.Arrays;

public class GameBoard {
    GUI_Field[] allFields = new GUI_Field[24];

    public GUI_Field[] setFields() {

        allFields[0] = new GUI_Start("START", " <-- ", "Dette er startfeltet", Color.white, Color.black);
        allFields[1] = new GUI_Street("BURGERBAR", "1$", "Denne brugerbar er den bedste på gaden.", "1", Color.orange.darker(), Color.black);
        allFields[2] = new GUI_Street("PIZZERIA", "1$", "Dette pizzeria er den bedste på gaden.", "1", Color.orange.darker(), Color.black);
        allFields[3] = new GUI_Chance();
        allFields[4] = new GUI_Street("SLIKBUTIKKEN", "1$", "uuh godtebutikken, ku godt.", "1", Color.cyan, Color.black);
        allFields[5] = new GUI_Street("ISKIOSKEN", "1$", "Nam-nam is, lækkert.", "1", Color.cyan, Color.black);
        allFields[6] = new GUI_Jail("default", "FÆNGSEL", "GÅ I FÆNGSEL", "du farlig", Color.orange, Color.white);
        allFields[7] = new GUI_Street("MUSEET", "2$", "Fancy sted.", "2", Color.magenta, Color.black);
        allFields[8] = new GUI_Street("BIBLIOTEKET", "2$", "Stille sted. ", "2",Color.magenta, Color.black);
        allFields[9] = new GUI_Chance();
        allFields[10] = new GUI_Street("SKATERPARKEN", "2$", "Cool kids sted.", "2", Color.yellow.brighter(), Color.black);
        allFields[11] = new GUI_Street("SKATERPARKEN", "2$", "Cool kids sted.", "2", Color.yellow.brighter(), Color.black);
        allFields[12] = new GUI_Refuge("src/main/resources/bil.png", "PARKERING", "PARKERING", "Gratis parkering :)", Color.white, Color.red.darker());
        allFields[13] = new GUI_Street("ARKADE", "3$", "htx kids sted.", "3", Color.red, Color.black);
        allFields[14] = new GUI_Street("BIOGRAF", "3$", "marvel sted.", "3", Color.red, Color.black);
        allFields[15] = new GUI_Chance();
        allFields[16] = new GUI_Street("LEGETØJSBUTIK", "3$", "kids sted.", "3", Color.pink, Color.black);
        allFields[17] = new GUI_Street("DYREHANDEL", "3$", "Animal sted.", "3", Color.pink, Color.black);
        allFields[18] = new GUI_Jail("default", "FÆNGSEL", "På besøg", "du farlig", Color.orange, Color.white);
        allFields[19] = new GUI_Street("BOWLINGHAL", "4$", "Hygge sted.", "4", Color.green, Color.black);
        allFields[20] = new GUI_Street("ZOO", "4$", "Ekstra Animal sted.", "4", Color.green, Color.black);
        allFields[21] = new GUI_Chance();
        allFields[22] = new GUI_Street("VANDLAND", "5$", "Ekstra vådt sted.", "5", Color.blue, Color.black);
        allFields[23] = new GUI_Street("STRAND", "5$", "Sandet sted.", "5", Color.blue, Color.black);


        return allFields;
    }



    @Override
    public String toString() {
        return "GameBoard{" +
                "allFields=" + Arrays.toString(allFields) +
                '}';
    }
}

