import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import xtra.Audio;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MonopolyJunior {
    private final GameBoard gameboard = new GameBoard();
    private final GUI_Field[] fields = gameboard.setFields();
    private final GUI gui = new GUI(fields, Color.pink.darker());
    private final GUI_Player[] guiPlayers = new GUI_Player[4];
    private final Player[] players = new Player[4];
    private final Audio audio = new Audio();
    private Player p = new Player();
    private final Chancecard chancecard = new Chancecard();
    private final Chancecard[] chancecards = chancecard.getAllChancecards();

    private int numberOfPlayers = 0;
    private boolean done = false;
    DicePair dicePair = new DicePair(0,0);

    public static void main(String[] args) throws InterruptedException {
        MonopolyJunior monopolyJunior = new MonopolyJunior();
        monopolyJunior.gameSetup();
    }

    //Spillets setup
   public void gameSetup() throws InterruptedException {
        audio.afspilAudio("src/main/resources/baggrundsMusik.wav");
        gui.setChanceCard("Her vises chancekortene, når du lander på et chance felt");
        createPlayers();
    }

    //Sætter spillernes startbalance ud fra valget af antallet af spillere
    public void setStartBalance(GUI_Player guiPlayer) {
        if (numberOfPlayers == 2)guiPlayer.setBalance(20);
        else if (numberOfPlayers == 3) guiPlayer.setBalance(18);
        else if (numberOfPlayers == 4) guiPlayer.setBalance(16);
    }

    //Laver spillere og tilføjer dem til GUI'en
    public void createPlayers( ) throws InterruptedException {
        int a = 0;
        String buttonPressed = gui.getUserButtonPressed("Hvor mange spillere er I? ", "2", "3", "4");
        if (buttonPressed.equals("2") || buttonPressed.equals("3") || buttonPressed.equals("4")) {
            numberOfPlayers = Integer.parseInt(buttonPressed);
            for (int i = 0; i < numberOfPlayers; i++) {
                a++;
                String playerName = gui.getUserString("Indtast navnet af spiller " + a + ": ");
                guiPlayers[i] =  new GUI_Player(playerName);
                players[i] = new Player(playerName, 0, guiPlayers[0].getBalance());
                setStartBalance(guiPlayers[i]);
                gui.addPlayer(guiPlayers[i]);
                gui.getFields()[0].setCar(guiPlayers[i], true);
            }
        }
        gameFlow();

    }

    //Detaljerne for spillertur
    public void playerTurn(GUI_Player guiPlayer, Player player) throws InterruptedException {
        dicePair.diceRoll();
        gui.setDice(dicePair.die1.getFaceValue(), dicePair.die2.getFaceValue());

        TimeUnit.MILLISECONDS.sleep(500);
        p.moveGUIPlayer(player, guiPlayer, dicePair.getFaceValueSum() + player.getPosition(), gui);
        chancecardField(player, guiPlayer);
        p.playerOnStreet(player, guiPlayer, gui, guiPlayers);
        if (p.isPlayerBankrupt(guiPlayer, done))
            gui.showMessage("Du har ikke flere penge, og har derfor tabt, " + player.getName());
    }

    /*
    Sørger for at spillerne slår med terningen i rækkefølge,
    og at spillet slutter, når en spiller går fallit
    */
    public void gameFlow() throws InterruptedException {
        int playerTurn = 0;
        while(!done) {
            p.isPlayerBankrupt(guiPlayers[playerTurn], done);

            if (playerTurn >= numberOfPlayers) playerTurn = 0;
            String button = gui.getUserButtonPressed("Tryk på knappen, for at slå, " + guiPlayers[playerTurn].getName() , "slå");
            if (button.equals("slå")) {
                playerTurn(guiPlayers[playerTurn], players[playerTurn]);
                playerTurn++;
            }
            if (playerTurn >= numberOfPlayers) playerTurn = 0;
        }
        guiPlayers[playerTurn - 1].setBalance(0);
        String input = gui.getUserButtonPressed("Spillet er slut", "OK");
        if (input.equals("OK")) System.exit(0);
    }

    //Tjekker om en spiller er landet på et chance felt
    public void chancecardField(Player player, GUI_Player guiPlayer) {
        if (player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15 || player.getPosition() == 21){
            displayRandomChancecard();
            chancecardMethod(player, guiPlayer);
            gui.displayChanceCard();
        }
    }

    //Metoderne for chancekortene
    public void chancecardMethod (Player player, GUI_Player guiPlayer) {
        int randomChancecard = displayRandomChancecard();
        if (randomChancecard == 0){
            p.moveGUIPlayer(player, guiPlayer, chancecards[randomChancecard].getNewPosition(), gui);
            guiPlayer.setBalance((int) (guiPlayer.getBalance()+ chancecards[randomChancecard].getAddMoney()));
        }
        if (randomChancecard == 1){
            String button = gui.getUserButtonPressed("Hvor mange felter vil du rykke?", "1", "2", "3", "4", "5");
            if (button.equals("1")) p.moveGUIPlayer(player, guiPlayer, player.getPosition()+1, gui);
            if (button.equals("2")) p.moveGUIPlayer(player, guiPlayer, player.getPosition()+2, gui);
            if (button.equals("3")) p.moveGUIPlayer(player, guiPlayer, player.getPosition()+3, gui);
            if (button.equals("4")) p.moveGUIPlayer(player, guiPlayer, player.getPosition()+4, gui);
            if (button.equals("5")) p.moveGUIPlayer(player, guiPlayer, player.getPosition()+5, gui);
        }
        if (randomChancecard == 2) p.moveGUIPlayer(player, guiPlayer, chancecards[randomChancecard].getNewPosition(), gui);
        if (randomChancecard == 3){
            String choice = gui.getUserButtonPressed("Vælg en handling", "Ryk 1 felt frem", "Tag et chancekort mere");
            if (choice.equals("Ryk 1 felt frem")) p.moveGUIPlayer(player, guiPlayer, player.getPosition() + 1, gui);
            if (choice.equals("Tag et chancekort mere")){
                displayRandomChancecard();
                chancecardMethod(player, guiPlayer);
            }
        }
        if (randomChancecard == 4){ guiPlayer.setBalance((int) (guiPlayer.getBalance()- chancecards[4].getPayMoney())); }
        if (randomChancecard == 5) {
            String choice = gui.getUserButtonPressed("Vælg en handling", "Ryk til et brunt felt", "Ryk til et grønt felt");
            if (choice.equals("Ryk til et brunt felt")) {
                String choiceBrown = gui.getUserButtonPressed("Hvilket felt vil du rykke til?", "Burgerbar", "Pizzeria");
                if(choiceBrown.equals("Burgerbar")) p.moveGUIPlayer(player, guiPlayer, 1, gui);
                if(choiceBrown.equals("Pizzeria")) p.moveGUIPlayer(player, guiPlayer, 2, gui);
            }
            if (choice.equals("Ryk til et grønt felt")) {
                String choiceGreen = gui.getUserButtonPressed("Hvilket felt vil du rykke til?", "Bowlinghal", "ZOO");
                if (choiceGreen.equals("Bowlinghal")) p.moveGUIPlayer(player, guiPlayer, 19, gui);
                if (choiceGreen.equals("ZOO")) p.moveGUIPlayer(player, guiPlayer, 20, gui);
            }
        }
    }

    //Sørger for at et random chancekort bliver vist på GUI'en
    public int displayRandomChancecard() {
        int randomNum = 0;
        for (int i = 0; i < chancecards.length ; i++) {
            randomNum = (int)(Math.random()*chancecards.length);
            gui.setChanceCard(chancecards[randomNum].getDescription());
            gui.displayChanceCard();
        }
        return randomNum;
    }







}