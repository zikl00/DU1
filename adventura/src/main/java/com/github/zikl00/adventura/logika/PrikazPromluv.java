package com.github.zikl00.adventura.logika;
import java.util.*;
import java.io.*; 
/**
 *  Třída PrikazPromluv implementuje pro hru příkaz promluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
class PrikazPromluv implements IPrikaz {
    private static final String NAZEV = "promluv";
    private Hra hra;
    
    /**
    * Konstruktor třídy
    *  
    * @param plan herní plán, ve kterém se bude ve hře "chodit"
    * @param hrdinka Hrdinka je potřeba kvůli metodám ze třídy
    */
    public PrikazPromluv(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provádí příkaz "promluv". Vypíše sdělení nadefinované
     * u postavy v aktuálním prostoru.
     *
     * @param parametry - bez parametrů, ošetřeno
     *
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if(parametry.length != 0){
            return "Příkaz promluv je bez parametru.";
        }
        if(hra.getHerniPlan().getAktualniProstor().getPostava() == null){
            return "Nikdo tu není.";
        }
        if(hra.getHerniPlan().getAktualniProstor().getPostava().zjistiFriend()){
            if(hra.getHerniPlan().getAktualniProstor().getPostava().getKecy() == null){
                return hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " ti nechce nic říct.";
            }else{
                return hra.getHerniPlan().getAktualniProstor().getPostava().getKecy();
            }
        }else{
            return "Nepřítel si s tebou moc povídat nebude...";
        }
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
