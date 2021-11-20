import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {
    Die die1 = new Die(2);

    @Test
    void testToString() {
        String test = die1.toString();

        assertEquals("Terningekastet er: 2", test, "Ikke ens");
    }

    @Test
    void testGetFaceValue(){
        int faceValue = die1.getFaceValue();

        assertEquals(2, faceValue, "Ikke ens");
    }

    @Test
    void testSetFacevalue(){
        die1.setFaceValue(4);
        int faceValue = die1.getFaceValue();

        assertEquals(4, faceValue);
    }
}