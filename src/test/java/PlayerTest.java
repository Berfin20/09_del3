import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void setName() {
        Player player = new Player("Popsi", 1000, 0);
        System.out.println("Spillerens navn er: " + player.getName());
        player.setName("Jens Hansen");
        System.out.println("Spillerens nye navn er: " + player.getName());

        assertEquals("Jens Hansen", player.getName());
    }

}