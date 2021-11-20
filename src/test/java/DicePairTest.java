import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DicePairTest {


    @Test
    void diceRoll(Die die1, Die die2) {
        int sum = die1.getFaceValue() + die2.getFaceValue();

        assertEquals(5, sum);
    }
}