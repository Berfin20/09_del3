public class DicePair {
    private int faceValueSum;

    public DicePair(int faceValue, int faceValue1) { }

    //Constructor til at initialiserer attributter
    public DicePair(Die die1, Die die2){ }

    public DicePair diceRoll(Die die1, Die die2){
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
