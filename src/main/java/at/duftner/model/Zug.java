package at.duftner.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zug {

   private List<Lokomotive> lokomotiven = new ArrayList<>();

   private List<Waggon> waggons = new ArrayList<>();

   private int schaffner;

   public Zug(List<Lokomotive> lokomotiven, List<Waggon> waggons) {

      initialisiere(lokomotiven, waggons);
   }

   public void aendereTeile(List<Lokomotive> lokomotiven, List<Waggon> waggons) {
      initialisiere(lokomotiven, waggons);
   }

   public long getLeergewichtGesamt() {
      List<ZugTeil> zugTeile = getZugTeile();

      return zugTeile.stream().map(ZugTeil::getLeergewicht).reduce(0L, Long::sum);
   }

   public int getPersonenKapazitaet() {
      List<ZugTeil> zugTeile = getZugTeile();

      return zugTeile.stream().map(ZugTeil::getPassagiere).reduce(0, Integer::sum);
   }

   public long getZuladungskapazitaetGueter() {
      List<ZugTeil> zugTeile = getZugTeile();

      return zugTeile.stream().map(ZugTeil::getZuladungsGewichtGueter).reduce(0L, Long::sum);
   }

   public long getZuladungskapazitaetGesamt() {
      return getZuladungskapazitaetGueter() + 75L*getPersonenKapazitaet();
   }

   public long getMaximalesGesamtgewicht() {
      return getZuladungskapazitaetGesamt() + getLeergewichtGesamt();
   }

   public double getGesamtLaenge() {
      List<ZugTeil> zugTeile = getZugTeile();

      return zugTeile.stream().map(ZugTeil::getLaenge).reduce(0.0, Double::sum);
   }

   public long getZugkraftGesamt() {
      return this.lokomotiven.stream().map(Lokomotive::getZugkraft).reduce(0L, Long::sum);
   }

   public boolean isFahrfaehig() {
      long zugkraft = getZugkraftGesamt();
      long gesamtgewichtMaximal = getMaximalesGesamtgewicht();

      return zugkraft >= gesamtgewichtMaximal;
   }


   public int getSchaffner() {

      return schaffner;
   }

   public List<ZugTeil> getZugTeile() {
      return Stream.concat(this.waggons.stream(), this.lokomotiven.stream()).collect(Collectors.toList());
   }

   private void initialisiereSchaffner() {
      int personen = getPersonenKapazitaet();

      this.schaffner = personen / 50;
   }

   private void checkPositionen() {
      List<Integer> positionen = getZugTeile().stream().map(ZugTeil::getPosition).sorted().collect(Collectors.toList());

      for(int i = 0; i<positionen.size(); i++) {
         if(i + 1 != positionen.get(i)) {
            throw new IllegalStateException("Die Positionierung der Zugbestandteile ist inkonsistent.");
         }
      }

   }

   private void initialisiere(List<Lokomotive> lokomotiven, List<Waggon> waggons) {
      if(lokomotiven == null || lokomotiven.isEmpty()) {
         throw new IllegalArgumentException("Ein Zug besteht aus mindestens einem Waggon");
      }

      if(waggons == null) {
         waggons = new ArrayList<>();
      }

      this.lokomotiven = lokomotiven;
      this.waggons = waggons;

      initialisiereSchaffner();
      checkPositionen();
   }

}
