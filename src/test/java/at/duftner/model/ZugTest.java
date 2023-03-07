package at.duftner.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import at.duftner.model.Lokomotive;
import at.duftner.model.Personenwaggon;
import at.duftner.model.Schlafwaggon;
import at.duftner.model.Waggon;
import at.duftner.model.Zug;
import org.junit.jupiter.api.Test;

class ZugTest {

   @Test
   void getLeergewichGesamt_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(2000L, zug.getLeergewichtGesamt());
   }

   @Test
   void getPersonenKapazitaet_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(900, zug.getPersonenKapazitaet());
   }

   @Test
   void getZuladungskapazitaetGueter_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(40000L, zug.getZuladungskapazitaetGueter());
   }

   @Test
   void getZuladungskapazitaetGesamt_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(40000L + 900 * 75, zug.getZuladungskapazitaetGesamt());
   }

   @Test
   void getLaenge_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(168, zug.getGesamtLaenge());
   }

   @Test
   void getSchaffner_gueltigerZug_korrektesErgebnis() {
      Zug zug = getGueltigerZug();

      assertEquals(18, zug.getSchaffner());
   }

   @Test
   void isFahrfaehig_gueltigerZug_true() {
      Zug zug = getGueltigerZug();

      assertTrue(zug.isFahrfaehig());
   }

   @Test
   void isFahrfaehig_zuWenigZugkraft_false() {
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
      lokomotive4.setZugkraft(39499L);

      Zug zug = new Zug(Arrays.asList(lokomotive1, lokomotive4), Arrays.asList(waggon2, waggon3));

      assertFalse(zug.isFahrfaehig());
   }

   @Test
   void constructor_korrekteWagenreihung_keineException() {
      assertDoesNotThrow(this::getGueltigerZug);
   }

   @Test
   void constructor_wagennummerUebersprungen_Exception() {
      Lokomotive lokomotive1 = new Lokomotive();
      lokomotive1.setPosition(1);
      Personenwaggon waggon2 = new Personenwaggon();
      waggon2.setPosition(2);
      Personenwaggon waggon3 = new Personenwaggon();
      waggon3.setPosition(3);
      Lokomotive lokomotive4 = new Lokomotive();
      lokomotive4.setPosition(5);

      assertThrows(IllegalStateException.class, () -> new Zug(Arrays.asList(lokomotive1, lokomotive4), Arrays.asList(waggon2, waggon3)));
   }

   @Test
   void constructor_wagennummerZero_Exception() {
      Lokomotive lokomotive1 = new Lokomotive();
      lokomotive1.setPosition(0);
      Personenwaggon waggon2 = new Personenwaggon();
      waggon2.setPosition(2);
      Personenwaggon waggon3 = new Personenwaggon();
      waggon3.setPosition(3);
      Lokomotive lokomotive4 = new Lokomotive();
      lokomotive4.setPosition(4);

      assertThrows(IllegalStateException.class, () -> new Zug(Arrays.asList(lokomotive1, lokomotive4), Arrays.asList(waggon2, waggon3)));
   }

   @Test
   void constructor_NullLokomotive_throwsException(){

      assertThrows(IllegalArgumentException.class, () -> new Zug(null, List.of(new Schlafwaggon())));
   }

   @Test
   void constructor_zeroLokomotive_throwsException(){

      assertThrows(IllegalArgumentException.class, () -> new Zug(Collections.emptyList(), List.of(new Schlafwaggon())));
   }

   @Test
   void constructor_nullWaggons_noException(){

      Lokomotive lokomotive = new Lokomotive();
      lokomotive.setPosition(1);

      assertDoesNotThrow(() -> new Zug(List.of(lokomotive), null));
   }

   @Test
   void aendereTeile_korrekteWagenreihung_keineException() {
      Zug zug = getGueltigerZug();
      List<Lokomotive> lokomotiven = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Lokomotive).map(teil -> (Lokomotive) teil).collect(Collectors.toList());
      List<Waggon> waggons = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Waggon).map(teil -> (Waggon) teil).collect(Collectors.toList());

      assertDoesNotThrow(() -> zug.aendereTeile(lokomotiven,waggons));
   }

   @Test
   void aendereTeile_wagennummerUebersprungen_Exception() {
      Zug zug = getGueltigerZug();

      List<Lokomotive> lokomotiven = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Lokomotive).map(teil -> (Lokomotive) teil).collect(Collectors.toList());
      List<Waggon> waggons = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Waggon).map(teil -> (Waggon) teil).collect(Collectors.toList());

      lokomotiven.get(1).setPosition(5);

      assertThrows(IllegalStateException.class, () -> zug.aendereTeile(lokomotiven,waggons));

   }

   @Test
   void aendereTeile_wagennummerZero_Exception() {
      Zug zug = getGueltigerZug();

      List<Lokomotive> lokomotiven = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Lokomotive).map(teil -> (Lokomotive) teil).collect(Collectors.toList());
      List<Waggon> waggons = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Waggon).map(teil -> (Waggon) teil).collect(Collectors.toList());

      lokomotiven.get(0).setPosition(0);
      waggons.get(0).setPosition(1);
      waggons.get(1).setPosition(2);
      lokomotiven.get(1).setPosition(3);

      assertThrows(IllegalStateException.class, () -> zug.aendereTeile(lokomotiven,waggons));
   }

   @Test
   void aendereTeile_NullLokomotive_throwsException(){

      Zug zug = getGueltigerZug();

      assertThrows(IllegalArgumentException.class, () -> zug.aendereTeile(null, List.of(new Schlafwaggon())));
   }

   @Test
   void aendereTeile_zeroLokomotive_throwsException(){

         Zug zug = getGueltigerZug();

      assertThrows(IllegalArgumentException.class, () -> zug.aendereTeile(Collections.emptyList(), List.of(new Schlafwaggon())));
   }

   @Test
   void aendereTeile_nullWaggons_noException() {

      Zug zug = getGueltigerZug();
      List<Lokomotive> lokomotiven = getGueltigerZug().getZugTeile().stream().filter(teil -> teil instanceof Lokomotive).map(teil -> (Lokomotive) teil).collect(Collectors.toList());
      lokomotiven.get(1).setPosition(2);

      assertDoesNotThrow(() -> zug.aendereTeile(lokomotiven,null));
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
