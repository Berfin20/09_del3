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
