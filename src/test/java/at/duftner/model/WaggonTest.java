package at.duftner.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class WaggonTest {

   private static final List<Class<? extends Waggon>> WAGGON_ARTEN = Arrays.asList(Schlafwaggon.class, Gueterwaggon.class, Personenwaggon.class, Speisewaggon.class);

   @Test
   void setPassagiere_wenigerAls0_Exception() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

      for(Class<? extends Waggon> waggonArt : WAGGON_ARTEN) {
         Waggon waggon = waggonArt.getDeclaredConstructor().newInstance();
         assertThrows(IllegalArgumentException.class, () -> waggon.setPassagiere(-1));
      }

   }

   @Test
   void setPassagiere_0_noException() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      for(Class<? extends Waggon> waggonArt : WAGGON_ARTEN) {
         Waggon waggon = waggonArt.getDeclaredConstructor().newInstance();
         assertDoesNotThrow(() -> waggon.setPassagiere(0));
      }
   }
   @Test
   void setZuladungsgewicht_wennigerAls0_Exception() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      for(Class<? extends Waggon> waggonArt : WAGGON_ARTEN) {
         Waggon waggon = waggonArt.getDeclaredConstructor().newInstance();
         assertThrows(IllegalArgumentException.class, () -> waggon.setZuladungsGewichtGueter(-1));
      }
   }

   @Test
   void setZuladungsgewicht_0_noException() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      for(Class<? extends Waggon> waggonArt : WAGGON_ARTEN) {
         Waggon waggon = waggonArt.getDeclaredConstructor().newInstance();
         assertDoesNotThrow(() -> waggon.setZuladungsGewichtGueter(0));
      }
   }

}
