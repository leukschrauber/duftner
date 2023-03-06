package at.duftner.model;

public abstract class ZugTeil {

   //Die Anforderung zu "fixes" Leergewicht und "fixe" Laenge sind m.E. nicht klar genug definiert.
   //Im realen Setting hätte ich eine Nachfrage an den Kunden/Requirements Engineer formuliert.
   private static final long LEERGEWICHT = 500L;
   private static final double LAENGE = 42.00;
   private int position;
   private int passagiere;
   private long zuladungsGewichtGueter;
   private String typenBezeichnung;
   private String hersteller;
   private String baujahr;
   private String serienNummer;


   public long getLeergewicht() {
      return LEERGEWICHT;
   }

   public double getLaenge() {
      return LAENGE;
   }

   public int getPosition() {

      return position;
   }

   public void setPosition(int position) {

      this.position = position;
   }

   public int getPassagiere() {
      return passagiere;
   }

   public void setPassagiere(int passagiere) {
      if(passagiere < 0) {
         throw new IllegalArgumentException("Passagiere können nicht kleiner 0 sein.");
      }

      this.passagiere = passagiere;
   }

   public long getZuladungsGewichtGueter() {

      return zuladungsGewichtGueter;
   }

   public void setZuladungsGewichtGueter(long zuladungsGewichtGueter) {
      if(zuladungsGewichtGueter < 0) {
         throw new IllegalArgumentException("Zuladungsgewicht kann nicht kleiner 0 sein.");
      }
      this.zuladungsGewichtGueter = zuladungsGewichtGueter;
   }

   public String getTypenBezeichnung() {

      return typenBezeichnung;
   }

   public void setTypenBezeichnung(String typenBezeichnung) {

      this.typenBezeichnung = typenBezeichnung;
   }

   public String getHersteller() {

      return hersteller;
   }

   public void setHersteller(String hersteller) {

      this.hersteller = hersteller;
   }

   public String getBaujahr() {

      return baujahr;
   }

   public void setBaujahr(String baujahr) {

      this.baujahr = baujahr;
   }

   public String getSerienNummer() {

      return serienNummer;
   }

   public void setSerienNummer(String serienNummer) {

      this.serienNummer = serienNummer;
   }
}
