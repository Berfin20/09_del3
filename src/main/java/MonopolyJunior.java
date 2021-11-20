import gui_codebehind.GUI_Center;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;
import xtra.Audio;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MonopolyJunior {
    private final GameBoard gameboard = new GameBoard();
    private final GUI_Field[] fields = gameboard.setFields();
    private final GUI gui = new GUI(fields, Color.pink.darker());
    private final Chancecard chancecard = new Chancecard();
    private final ArrayList<Chancecard> chancecards = chancecard.getAllChancecards();
    private final ArrayList<GUI_Player> guiPlayers = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final Audio audio = new Audio();
    Die die1 = new Die();
    Die die2 = new Die();
    DicePair dicePair = new DicePair(die1, die2);

    private int numberOfPlayers = 0;
    private boolean done = false;

   public void gameSetup() throws InterruptedException {
        audio.afspilAudio("src/main/resources/baggrundsMusik.wav");
        gui.setChanceCard("Her vises chancekortene, når du lander på et chance felt");
        createPlayers();
    }

    public void createPlayers() throws InterruptedException {
        int a = 0;
        String buttonPressed = gui.getUserButtonPressed("Hvor mange spillere er I? ", "2", "3", "4");
        if (buttonPressed.equals("2") || buttonPressed.equals("3") || buttonPressed.equals("4")) {
            numberOfPlayers = Integer.parseInt(buttonPressed);
            for (int i = 0; i < numberOfPlayers; i++) {
                a++;
                String playerName = gui.getUserString("Indtast navnet af spiller " + a + ": ");
                guiPlayers.add(i, new GUI_Player(playerName));
                players.add(i, new Player(playerName, 0, guiPlayers.get(0).getBalance()));
                setStartBalance(guiPlayers.get(i));
                gui.addPlayer(guiPlayers.get(i));
                gui.getFields()[0].setCar(guiPlayers.get(i), true);
            }
        }
            playerTurnFlow();
    }

    public void setStartBalance(GUI_Player guiPlayer) {
        if (numberOfPlayers == 2)guiPlayer.setBalance(20);
        else if (numberOfPlayers == 3) guiPlayer.setBalance(18);
        else if (numberOfPlayers == 4) guiPlayer.setBalance(16);
    }

    public void playerTurnFlow() throws InterruptedException {
        int playerTurn = 0;
        while(!done) {
            playerBankrupt(guiPlayers.get(playerTurn));

            if (playerTurn >= numberOfPlayers) playerTurn = 0;
            String button = gui.getUserButtonPressed("Tryk på knappen, for at slå, " + guiPlayers.get(playerTurn).getName() , "slå");
            if (button.equals("slå")) {
                playerTurn(guiPlayers.get(playerTurn), players.get(playerTurn));
                playerTurn++;
            }
            if (playerTurn >= numberOfPlayers) playerTurn = 0;
        }
        guiPlayers.get(playerTurn - 1).setBalance(0);
       String input = gui.getUserButtonPressed("Spillet er slut", "OK");
        if (input.equals("OK")) System.exit(0);
    }

    public void playerTurn(GUI_Player guiPlayer, Player player) throws InterruptedException {
            dicePair.diceRoll(die1, die2);
            gui.setDice(die1.getFaceValue(), die2.getFaceValue());

            TimeUnit.MILLISECONDS.sleep(500);
            moveGUIPlayer(player, guiPlayer, dicePair.getFaceValueSum() + player.getPosition());
            chancecardField(player, guiPlayer);
            buyFields(player, guiPlayer);
            if(playerBankrupt(guiPlayer)) gui.showMessage("Du har ikke flere penge, og har derfor tabt, " + player.getName());
    }

    public void chancecardField(Player player, GUI_Player guiPlayer) {
        if (player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15 || player.getPosition() == 21){
            displayRandomChancecard();
           chancecardMethod(player, guiPlayer);
            gui.displayChanceCard();
        }
    }

    public void chancecardMethod (Player player, GUI_Player guiPlayer) {
        int randomChancecard = displayRandomChancecard();
        if (randomChancecard == 0){
            moveGUIPlayer(player, guiPlayer, chancecards.get(randomChancecard).getNewPosition());
            guiPlayer.setBalance((int) (guiPlayer.getBalance()+ chancecards.get(randomChancecard).getAddMoney()));
        }
        if (randomChancecard == 1){
            String button = gui.getUserButtonPressed("Hvor mange felter vil du rykke?", "1", "2", "3", "4", "5");
            if (button.equals("1")) moveGUIPlayer(player, guiPlayer, player.getPosition()+1);
            if (button.equals("2")) moveGUIPlayer(player, guiPlayer, player.getPosition()+2);
            if (button.equals("3")) moveGUIPlayer(player, guiPlayer, player.getPosition()+3);
            if (button.equals("4")) moveGUIPlayer(player, guiPlayer, player.getPosition()+4);
            if (button.equals("5")) moveGUIPlayer(player, guiPlayer, player.getPosition()+5);
        }
        if (randomChancecard == 2) moveGUIPlayer(player, guiPlayer, chancecards.get(randomChancecard).getNewPosition());
        if (randomChancecard == 3){
            String choice = gui.getUserButtonPressed("Vælg en handling", "Ryk 1 felt frem", "Tag et chancekort mere");
            if (choice.equals("Ryk 1 felt frem")) moveGUIPlayer(player, guiPlayer, player.getPosition() + 1);
            if (choice.equals("Tag et chancekort mere")){
                displayRandomChancecard();
                chancecardMethod(player, guiPlayer);
            }
        }
        if (randomChancecard == 4){ guiPlayer.setBalance((int) (guiPlayer.getBalance()- chancecards.get(4).getPayMoney())); }
        if (randomChancecard == 5) {
            String choice = gui.getUserButtonPressed("Vælg en handling", "Ryk til et brunt felt", "Ryk til et grønt felt");
            if (choice.equals("Ryk til et brunt felt")) {
                String choiceBrown = gui.getUserButtonPressed("Hvilket felt vil du rykke til?", "Burgerbar", "Pizzeria");
                if(choiceBrown.equals("Burgerbar")) moveGUIPlayer(player, guiPlayer, 1);
                if(choiceBrown.equals("Pizzeria")) moveGUIPlayer(player, guiPlayer, 2);
            }
            if (choice.equals("Ryk til et grønt felt")) {
                String choiceGreen = gui.getUserButtonPressed("Hvilket felt vil du rykke til?", "Bowlinghal", "ZOO");
                if (choiceGreen.equals("Bowlinghal")) moveGUIPlayer(player, guiPlayer, 19);
                if (choiceGreen.equals("ZOO")) moveGUIPlayer(player, guiPlayer, 20);
            }
        }
    }

    public int displayRandomChancecard() {
       int randomNum = 0;
        for (int i = 0; i < chancecards.size() ; i++) {
            randomNum = (int)(Math.random()*chancecards.size());
            gui.setChanceCard(chancecards.get(randomNum).getDescription());
            gui.displayChanceCard();
        }
        return randomNum;
    }

    public void moveGUIPlayer(Player player, GUI_Player guiPlayer, int position) {
        gui.getFields()[player.getPosition()].setCar(guiPlayer,false);
        player.setPosition(position);
        circleTurn(player,guiPlayer);
        gui.getFields()[player.getPosition()].setCar(guiPlayer, true);
    }

    public boolean playerBankrupt(GUI_Player guiPlayer) {
        if (guiPlayer.getBalance() <= 0) {
            done = true;
            return true;
        } else {
            done = false;
            return false;
        }
    }

    public void buyFields(Player player, GUI_Player guiPlayer) {
        if (player.getPosition() == 1) buyField(player, 1, guiPlayer);
        if (player.getPosition() == 2) buyField(player, 2,  guiPlayer);
        if (player.getPosition() == 4) buyField(player, 4,  guiPlayer);
        if (player.getPosition() == 5) buyField(player, 5, guiPlayer);
        if (player.getPosition() == 7) buyField(player, 7, guiPlayer);
        if (player.getPosition() == 8) buyField(player, 8, guiPlayer);
        if (player.getPosition() == 10) buyField(player, 10, guiPlayer);
        if (player.getPosition() == 11) buyField(player, 11, guiPlayer);
        if (player.getPosition() == 13) buyField(player, 13, guiPlayer);
        if (player.getPosition() == 14) buyField(player, 14, guiPlayer);
        if (player.getPosition() == 16) buyField(player, 16, guiPlayer);
        if (player.getPosition() == 17) buyField(player, 17, guiPlayer);
        if (player.getPosition() == 19) buyField(player, 19, guiPlayer);
        if (player.getPosition() == 20) buyField(player, 20, guiPlayer);
        if (player.getPosition() == 22) buyField(player, 22, guiPlayer);
        if (player.getPosition() == 23) buyField(player, 23, guiPlayer);
    }

    public int findPlayer(String name) {
        for (GUI_Player guiPlayer : guiPlayers) {
            if (guiPlayer.getName().equals(name)) {
                return guiPlayer.getNumber();
            }
        } return 0;
    }

    public void buyField(Player player, int fieldNum, GUI_Player guiPlayer) {
        if ( ((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName() == null) {
            if (player.getPosition() == fieldNum) {
                String button = gui.getUserButtonPressed("Vil du købe dette felt?", "Ja", "Nej");
                if (button.equals("Ja")) {
                    ((GUI_Ownable) gui.getFields()[fieldNum]).setOwnerName(player.getName());
                    ((GUI_Ownable) gui.getFields()[fieldNum]).setBorder(guiPlayer.getPrimaryColor());
                    fieldBalanceInfluence(guiPlayer, fieldNum);
                }
                if (button.equals("Nej")) {
                    gui.showMessage("okay");
                }
            }
        } else if (((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName() != null && !((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName().equals(player.getName())){
            if (player.getPosition() == fieldNum) {
                gui.showMessage("Du skal betale lejen af dette felt.");
                    String rent = ((GUI_Ownable) gui.getFields()[fieldNum]).getRent();
                    guiPlayer.setBalance(guiPlayer.getBalance()-Integer.parseInt(rent));
                    int index = findPlayer(((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName());

                    guiPlayers.get(index).setBalance(guiPlayers.get(index).getBalance()+Integer.parseInt(rent));
                }
            } else {
            gui.showMessage("Du er landet på et felt, som du ejer, så der er intet at foretage her:)");
        }
    }

    public void fieldBalanceInfluence(GUI_Player guiPlayer, int fieldNum) {
        if(fieldNum == 1 ||  fieldNum == 2 || fieldNum == 4 || fieldNum == 5)
            guiPlayer.setBalance(guiPlayer.getBalance() - 1);
        if(fieldNum == 7 ||  fieldNum == 8 || fieldNum == 10 ||fieldNum == 11)
            guiPlayer.setBalance(guiPlayer.getBalance() - 2);
        if(fieldNum == 13 ||  fieldNum == 14 || fieldNum == 16 ||fieldNum == 17)
            guiPlayer.setBalance(guiPlayer.getBalance() - 3);
        if(fieldNum == 19 ||  fieldNum == 20 || fieldNum == 22 || fieldNum == 23)
            guiPlayer.setBalance(guiPlayer.getBalance() - 4);
    }

    public void circleTurn(Player player, GUI_Player guiPlayer) {
        if(player.getPosition()>=24){
            guiPlayer.setBalance(guiPlayer.getBalance()+2);
            player.setPosition(player.getPosition()-24);
        }
    }

}