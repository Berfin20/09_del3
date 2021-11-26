import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Player {
    private String name;
    private int position;
    private int balance;


    public Player (String name, int position, int balance) {
        this.name = name;
        this.position = position;
        this.balance = balance;
    }

    public Player() {

    }

    //Tjekker om en spilleren er gået fallit
    public boolean isPlayerBankrupt(GUI_Player guiPlayer, boolean done) {
        if (guiPlayer.getBalance() <= 0) {
            done = true;
            return true;
        } else {
            done = false;
            return false;
        }
    }

    //Finder en spillers index ud fra et navn
    public int findPlayer(String name, GUI_Player[] guiPlayers) {
        for (GUI_Player guiPlayer : guiPlayers) {
            if (guiPlayer.getName().equals(name)) {
                return guiPlayer.getNumber();
            }
        } return 0;
    }

    //Sørger for at spillerens bil rykker på GUI'en
    public void moveGUIPlayer(Player player, GUI_Player guiPlayer, int position, GUI gui) {
        gui.getFields()[player.getPosition()].setCar(guiPlayer,false);
        player.setPosition(position);
        circleTurn(player,guiPlayer);
        gui.getFields()[player.getPosition()].setCar(guiPlayer, true);
    }

    //Sørger for at en spiller også kan lande på startfeltet igen
    public void circleTurn(Player player, GUI_Player guiPlayer) {
        if(player.getPosition()>=24){
            guiPlayer.setBalance(guiPlayer.getBalance()+2);
            player.setPosition(player.getPosition()-24);
        }
    }

    //Tjekker om en spiller er på et street felt
    public void playerOnStreet(Player player, GUI_Player guiPlayer, GUI gui, GUI_Player[] guiPlayers) {
        if (player.getPosition() == 1) buyField(player, 1, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 2) buyField(player, 2,  guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 4) buyField(player, 4,  guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 5) buyField(player, 5, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 7) buyField(player, 7, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 8) buyField(player, 8, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 10) buyField(player, 10, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 11) buyField(player, 11, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 13) buyField(player, 13, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 14) buyField(player, 14, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 16) buyField(player, 16, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 17) buyField(player, 17, guiPlayer, gui,guiPlayers);
        if (player.getPosition() == 19) buyField(player, 19, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 20) buyField(player, 20, guiPlayer, gui, guiPlayers);
        if (player.getPosition() == 22) buyField(player, 22, guiPlayer, gui,guiPlayers);
        if (player.getPosition() == 23) buyField(player, 23, guiPlayer, gui,guiPlayers);
    }

    //Sørger for at en spiller kan vælge at købe det felt, som den er landet på
    public void buyField(Player player, int fieldNum, GUI_Player guiPlayer, GUI gui, GUI_Player[] guiPlayers) {
        if ( ((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName() == null) {
            if (player.getPosition() == fieldNum) {
                String button = gui.getUserButtonPressed("Vil du købe dette felt?", "Ja", "Nej");
                if (button.equals("Ja")) {
                    ((GUI_Ownable) gui.getFields()[fieldNum]).setOwnerName(player.getName());
                    ((GUI_Ownable) gui.getFields()[fieldNum]).setBorder(guiPlayer.getPrimaryColor());
                    if(fieldNum == 1 ||  fieldNum == 2 || fieldNum == 4 || fieldNum == 5)
                        guiPlayer.setBalance(guiPlayer.getBalance() - 1);
                    if(fieldNum == 7 ||  fieldNum == 8 || fieldNum == 10 ||fieldNum == 11)
                        guiPlayer.setBalance(guiPlayer.getBalance() - 2);
                    if(fieldNum == 13 ||  fieldNum == 14 || fieldNum == 16 ||fieldNum == 17)
                        guiPlayer.setBalance(guiPlayer.getBalance() - 3);
                    if(fieldNum == 19 ||  fieldNum == 20 || fieldNum == 22 || fieldNum == 23)
                        guiPlayer.setBalance(guiPlayer.getBalance() - 4);
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
                int index = findPlayer(((GUI_Ownable) gui.getFields()[fieldNum]).getOwnerName(), guiPlayers);

                guiPlayers[index].setBalance(guiPlayers[index].getBalance()+Integer.parseInt(rent));
            }
        } else {
            gui.showMessage("Du er landet på et felt, som du ejer, så der er intet at foretage her:)");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) { this.position = position; }

    public int getBalance() { return balance; }

    public void setBalance(int balance) { this.balance = balance; }


}
