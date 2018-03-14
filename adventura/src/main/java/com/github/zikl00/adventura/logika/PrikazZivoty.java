package com.github.zikl00.adventura.logika;

/**
 *  Třída PrikazZivoty implementuje pro hru příkaz zivoty.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
public class PrikazZivoty implements IPrikaz {
    private static final String NAZEV = "životy";
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra kvůli metodám ze třídy
    */    
    public PrikazZivoty(Hra hra) {
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "zivoty". Vypíše aktuální počet životů
     *  z maximální hodnoty.
     *
     *@param parametry - příkaz je bez parametru, ošetřeno
     *
     *@return výpis životů
     */ 
    @Override
    public String proved(String... parametry) {
        if(parametry.length == 0){
            return "Máš " + hra.getHerniPlan().getHrdinka().ukazZivoty() + " životů a tvoje útočné číslo je: "
            + hra.getHerniPlan().getHrdinka().ukazUtocneCislo();
        }else{
            return "Příkaz životy je bez parametru.";
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
