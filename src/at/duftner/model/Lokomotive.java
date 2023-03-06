package at.duftner.model;

public class Lokomotive extends ZugTeil {

   private long zugkraft;
   private Antriebsart antriebsart;

   public long getZugkraft() {
      return zugkraft;
   }

   public void setZugkraft(long zugkraft) {
      this.zugkraft = zugkraft;
   }

   public Antriebsart getAntriebsart() {

      return antriebsart;
   }

   public void setAntriebsart(Antriebsart antriebsart) {

      this.antriebsart = antriebsart;
   }
}
