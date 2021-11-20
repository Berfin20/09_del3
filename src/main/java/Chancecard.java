import javax.swing.event.ChangeEvent;
import java.util.ArrayList;

public class Chancecard {
    String description;
    int newPosition;
    double addMoney;
    double payMoney;
    ArrayList<Chancecard> allChancecards = new ArrayList<>();


    public Chancecard() {

    }

    public Chancecard(String description, int newPosition, double addMoney, double payMoney ){
        this.description = description;
        this.newPosition = newPosition;
        this.addMoney = addMoney;
        this.payMoney = payMoney;
    }

    public ArrayList<Chancecard> setUp() {
        Chancecard first = new Chancecard("Ryk frem til START. Modtag 2$", 0, 2, 0);
        allChancecards.add(first);

        Chancecard second = new Chancecard("Ryk op til 5 felter frem", +0, 0, 0);
        allChancecards.add(second);

        Chancecard third = new Chancecard("Ryk frem til det nærmeste brune felt.", 1, 0, 0);
        allChancecards.add(third);

        Chancecard fourth = new Chancecard("Ryk 1 felt frem, ELLER tag et chancekort mere", +0, 0, 0);
        allChancecards.add(fourth);

        Chancecard fifth = new Chancecard("Du har spist for meget slik. Betal 2$ til banken", +0, 0, 2);
        allChancecards.add(fifth);

        Chancecard sixth = new Chancecard("Ryk frem til et BRUNT eller GRØNT felt.", +0, 0,0 );
        allChancecards.add(sixth);

        return allChancecards;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(double addMoney) {
        this.addMoney = addMoney;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public ArrayList<Chancecard> getAllChancecards() {
        setUp();
        return allChancecards;
    }

    @Override
    public String toString() {
        return "Chancecard{" +
                "allChancecards=" + allChancecards +
                '}';
    }
}
