package at.duftner.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import at.duftner.model.Lokomotive;
import at.duftner.model.Personenwaggon;
import at.duftner.model.Zug;
import org.junit.jupiter.api.Test;

class ZugManagerTest {

   ZugManager underTest = new ZugManager();

   @Test
   void addZug_addGleichenZugZweiMal_Exception() {

      Zug zug = getGueltigerZug();
      underTest.addZug(zug);

      assertThrows(IllegalArgumentException.class, () -> underTest.addZug(zug));
   }

   @Test
   void addZug_addUnterschiedlicheZuege_keineException() {

      underTest.addZug(getGueltigerZug());

      assertDoesNotThrow(() -> underTest.addZug(getGueltigerZug()));
   }


   private Zug getGueltigerZug() {
      Lokomotive lokomotive1 = new Lokomotive();
      lokomotive1.setPosition(1);
      lokomotive1.setZugkraft(70000L);
      lokomotive1.setZuladungsGewichtGueter(5000L);
      Personenwaggon waggon2 = new Personenwaggon();
      waggon2.setPosition(2);
      waggon2.setPassagiere(400);
      waggon2.setZuladungsGewichtGueter(10000L);
      Personenwaggon waggon3 = new Personenwaggon();
      waggon3.setPosition(3);
      waggon3.setPassagiere(500);
      waggon3.setZuladungsGewichtGueter(25000L);
      Lokomotive lokomotive4 = new Lokomotive();
      lokomotive4.setPosition(4);
      lokomotive4.setZugkraft(39500L);

      return new Zug(Arrays.asList(lokomotive1, lokomotive4), Arrays.asList(waggon2, waggon3));
   }

}
