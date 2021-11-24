public class Chancecard {
    String description;
    int newPosition;
    double addMoney;
    double payMoney;
    Chancecard[] allChancecards = new Chancecard[6];

    public Chancecard() {

    }

    public Chancecard[] setUp() {
        allChancecards[0] = new Chancecard();
        allChancecards[0].setDescription("Ryk frem til START. Modtag 2$");
        allChancecards[0].setNewPosition(0);
        allChancecards[0].setAddMoney(2);
        allChancecards[1] = new Chancecard();
        allChancecards[1].setDescription("Ryk op til 5 felter frem");
        allChancecards[2] = new Chancecard();
        allChancecards[2].setDescription("Ryk frem til det nærmeste brune felt.");
        allChancecards[2].setNewPosition(1);
        allChancecards[3] = new Chancecard();
        allChancecards[3].setDescription("Ryk 1 felt frem, ELLER tag et chancekort mere");
        allChancecards[4] = new Chancecard();
        allChancecards[4].setDescription("Du har spist for meget slik. Betal 2$ til banken");
        allChancecards[4].setPayMoney(2);
        allChancecards[5] = new Chancecard();
        allChancecards[5].setDescription("GRATIS FELT! Ryk frem til et BRUNT eller GRØNT felt.");
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

    public Chancecard[] getAllChancecards() {
        setUp();
        return allChancecards;
    }

}
