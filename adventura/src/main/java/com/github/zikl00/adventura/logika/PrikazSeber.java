/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;

import java.util.Observable;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
public class PrikazSeber extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private Hra hra;
    //== Konstruktory a tovární metody =============================================

    /**
    * Konstruktor třídy
    * 
    * @param hra Předání instance třídy hra kvůli metodám ze třídy
    */
    public PrikazSeber(Hra hra)
    {
        this.hra = hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda sebere předmět který se nachází v aktuálním prostoru.
     *  Maximální velikost inventáře je 6 položek.
     *  
     *  @param parametry Obsahuje název věci, která se má sebrat
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Co mám sebrat?";
        }        
        String nazevSbiraneVeci = parametry[0];        
        Prostor aktualni = hra.getHerniPlan().getAktualniProstor();        
        Vec sbirana = aktualni.odeberVec(nazevSbiraneVeci);
        if(sbirana == null){
            return "Nic takového tu není.";
        }else if(hra.getHerniPlan().getHrdinka().vratInventar().size()>5){
            aktualni.vlozVec(sbirana);
            return "Víc věcí se do inventáře nevejde.";
        }else{
            if(hra.getHerniPlan().getAktualniProstor().getPostava() != null && !(hra.getHerniPlan().getAktualniProstor().pratelskaPostava())){
                aktualni.vlozVec(sbirana);
                //System.out.print( hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " tě majznul přes ruce a ubral 2 životy.");
                hra.getHerniPlan().getHrdinka().seberZivoty(2);
                if( hra.getHerniPlan().getHrdinka().vratZivotyCislo() < 1){
                       //System.out.println("\nUmřela jsi a tvé dobrodružství zde končí...");
                       return hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " tě majznul přes ruce a ubral 2 životy." + "\nUmřela jsi a tvé dobrodružství zde končí...";
                   }
                return hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " tě majznul přes ruce a ubral 2 životy.";
            }
            if(sbirana.jePrenositelna()){
               hra.getHerniPlan().getHrdinka().vlozVec(sbirana);
               this.setChanged();
               this.notifyObservers();
               return "Sebrala jsi: " + sbirana.getNazev();
            }
            else{
                aktualni.vlozVec(sbirana);
                return "To je moc těžké.";
            }
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
