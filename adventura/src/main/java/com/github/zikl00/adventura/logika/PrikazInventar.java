package com.github.zikl00.adventura.logika;

/**
 *  Třída PrikazInventar implementuje pro hru příkaz inventar.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
class PrikazInventar implements IPrikaz {
    private static final String NAZEV = "inventář";
    private Hra hra;
    
    /**
    * Konstruktor třídy
    *  
    * @param hra je potřeba kvůli metodám ze třídy.
    */    
    public PrikazInventar(Hra hra) {
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "inventar". Vypíše obsah inventáře hlavní hrdinky a používanou zbraň.
     *
     * @param parametry - bez parametrů, ošetřeno
     * 
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if(parametry.length == 0){
            return hra.getHerniPlan().getHrdinka().popisInventar() + "\n" + hra.getHerniPlan().getHrdinka().ukazEQ();
        }else{
            return "Příkaz souboj je bez parametru.";
        }
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
