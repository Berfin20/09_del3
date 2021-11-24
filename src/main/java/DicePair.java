public class DicePair{
    private int faceValueSum;
    Die die1 = new Die(0);
    Die die2 = new Die(0);

    public DicePair(int die1, int die2) {
    }

    public DicePair diceRoll(){
        die1.setFaceValue((int)(Math.random()*6)+1);
        die2.setFaceValue ((int)(Math.random()*6)+1);

        setFaceValueSum(die1.getFaceValue()+die2.getFaceValue());
        return new DicePair(die1.getFaceValue(), die2.getFaceValue());
    }

    public int getFaceValueSum() {
        return faceValueSum;
    }

    public void setFaceValueSum(int faceValueSum) {
        this.faceValueSum = faceValueSum;
    }
}
