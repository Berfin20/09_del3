import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DicePairTest {

    @Test
    void diceRoll() {
        DicePair dicePair = new DicePair(0,0);
        dicePair.die1.setFaceValue(3);
        dicePair.die2.setFaceValue(4);
        int sum = dicePair.die1.getFaceValue() + dicePair.die2.getFaceValue();

        assertEquals(7, sum);
    }
}