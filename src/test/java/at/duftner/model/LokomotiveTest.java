package at.duftner.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LokomotiveTest {

   @Test
   void setPassagiere_wenigerAls0_Exception(){
      Lokomotive lokomotive = new Lokomotive();

      assertThrows(IllegalArgumentException.class, () -> lokomotive.setPassagiere(-1));
   }

   @Test
   void setPassagiere_0_noException(){
      Lokomotive lokomotive = new Lokomotive();

      assertDoesNotThrow(() -> lokomotive.setPassagiere(0));
   }
   @Test
   void setZuladungsgewicht_wennigerAls0_Exception(){
      Lokomotive lokomotive = new Lokomotive();

      assertThrows(IllegalArgumentException.class, () -> lokomotive.setZuladungsGewichtGueter(-1));
   }

   @Test
   void setZuladungsgewicht_0_noException(){
      Lokomotive lokomotive = new Lokomotive();

      assertDoesNotThrow(() -> lokomotive.setZuladungsGewichtGueter(0));
   }

}
