package com.github.zikl00.adventura.logika;

/**
 *  Třída PrikazPopis implementuje pro hru příkaz popis.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
class PrikazPopis implements IPrikaz {
    
    private static final String NAZEV = "popis";
    private Hra hra;
    
    /**
    * Konstruktor třídy
    * 
    * @param plan Herní plán, ve kterém se bude ve hře "chodit"
    */
    public PrikazPopis(Hra hra) {
        this.hra = hra;
    }
    
    /**
     *  Vrací dlouhý popis aktuálního prostoru, východy, postavu v prostoru
     *  a předměty v prostoru.
     *  
     *  @return popis k aktuálnímu prostoru.
     */
    @Override
    public String proved(String... parametry) {
        return hra.getHerniPlan().getAktualniProstor().dlouhyPopis();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
