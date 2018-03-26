/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;
import java.util.*;
import java.util.Observable;

/**
 *  Třída PrikazPouzij implementuje pro hru příkaz pouzij.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
public class PrikazPouzij extends Observable implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "použij";
    private Hra hra;
    //== Konstruktory a tovární metody =============================================

    /**
    * Konstruktor třídy
    * 
    * @param hra, kvůli metodám ze třídy
    */
    public PrikazPouzij(Hra hra)
    {
        this.hra = hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda equipne predmet z inventare, pokud uz nejaky je equipnuty, vymeni je.
     *  Pokud je to lektvar, tak ho vypije - lektvat má záporné číslo útoku (pro jednoduchost).
     *  
     *  @param parametry Obsahuje název věci, která se má použít
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Co mám použít?";
        }        
        String nazevPouzivaneVeci = parametry[0];
        Vec pouzivana = hra.getHerniPlan().getHrdinka().odeberVec(nazevPouzivaneVeci);
        if(pouzivana == null){
            return "To v inventáři nemáš.";
        }//kdyz jeste nic nema
        else{
        	String vratRetezec = hra.getHerniPlan().getHrdinka().pouzijVec(pouzivana);
            if(pouzivana.vratUtocneCislo() > 0){
            	this.setChanged();
            	this.notifyObservers();
            	return "teď bojuješ s: " + pouzivana.getNazev();}
            else{
            	this.setChanged();
            	this.notifyObservers();
            	return vratRetezec;}
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
