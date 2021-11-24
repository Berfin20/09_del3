public class Die {
    private int faceValue;

    public Die(int faceValue) {
        this.faceValue = faceValue;
    }

    public Die() {
    }

    @Override
    public String toString() {
        return "Terningekastet er: " + faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }
}
