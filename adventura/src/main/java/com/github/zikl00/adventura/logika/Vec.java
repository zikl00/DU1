/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;


/*******************************************************************************
 * Třída Postava - popisuje postavu umístěnou v konkrétním prostoru.
 * 
 * "Postava" reprezentuje jednu postavu. Postava má název, může mít i proslov ke sdělení,
 * boolean hodnotu jestli je přátelská nebo ne. Dále má počet životů,
 * útočné číslo a mapu s věcmi které u sebe má v inventáři
 * 
 * @author    Libor Zíka
 * @version   1.01
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private int utocneCislo;
    //== Konstruktory a tovární metody =============================================

    /**
     * Konstruktor pro vytvoření věci. Útočné číslo je kladné pro věci - zbraně, záporné pro léčivé věci.
     *
     * @param nazev Název věci, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param prenositelnost Boolean hodnota určující přenositelnost.
     * @param utocneCislo Určuje hodnotu útočného čísla.
     */
    public Vec(String nazev, boolean prenositelnost, int utocneCislo)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.utocneCislo = utocneCislo;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Getter na název věci.
     * 
     * @return String nazev.
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Getter na přenositelnost věci.
     * 
     * @return boolean prenositelnost.
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    /**
     * Getter na útočné číslo předmětu.
     * 
     * @return int utocneCislo.
     */
    public int vratUtocneCislo(){
        return utocneCislo;
    }
    
    @Override
    public String toString() {
    	return getNazev();
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
