/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;
import java.util.*;

/**
 *  Třída PrikazZahod implementuje pro hru příkaz zahod.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author    Libor Zíka
 * @version   1.01
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahoď";
    private Hra hra;
    //== Konstruktory a tovární metody =============================================

    /**
    * Konstruktor třídy
    * 
    * @param hra, kvůli metodám ze třídy
    */
    public PrikazZahod(Hra hra)
    {
        this.hra = hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro provedení příkazu zahod, která přesune věc/předmět
     *  z inventáře do aktuálního prostoru.
     *  
     *  @param parametry Název věci, která se má zahodit
     *  
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "co mám zahodit?";
        }        
        String nazevZahozeneVeci = parametry[0];
        Prostor aktualni = hra.getHerniPlan().getAktualniProstor();
        Vec zahozena = hra.getHerniPlan().getHrdinka().odeberVec(nazevZahozeneVeci);
        Vec equipnuta = hra.getHerniPlan().getHrdinka().odeberEquip(nazevZahozeneVeci);
        if(equipnuta != null){
            aktualni.vlozVec(equipnuta);
            return "z ruky jsi zahodila: " + equipnuta.getNazev();
        }
        if(zahozena == null){
            return "To v inventáři nemáš.";
        }
        else{
             aktualni.vlozVec(zahozena);
             return "zahodila jsi: " + zahozena.getNazev();
        }
    } 
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
