package at.duftner.service;

import java.util.ArrayList;
import java.util.List;

import at.duftner.model.Zug;
import at.duftner.model.ZugTeil;

public class ZugManager {

   private final List<Zug> zuege = new ArrayList<>();

   public void addZug(Zug neuerZug) {

      for(Zug alterZug : this.zuege) {
         for(ZugTeil neuerZugTeil : neuerZug.getZugTeile()) {
            for (ZugTeil alterZugTeil : alterZug.getZugTeile()) {
               //Die Pruefung der Gleichheit zweier Zugteile erfolgt der Einfachheit halber per Referenz.
               //In einem echten Projekt, hätten die Zugteile IDs aus der Datenbank, die sie identifizieren.
               if (neuerZugTeil == alterZugTeil) {
                  throw new IllegalArgumentException("Ein ZugTeil wurde bereits in einem anderen Zug verplant.");
               }
            }
         }
      }

      this.zuege.add(neuerZug);
   }

   public void deleteZug(Zug zugToBeDeleted) {

      this.zuege.removeIf(zug -> zug == zugToBeDeleted);
   }

   public List<Zug> getZuege() {

      return zuege;
   }
}
