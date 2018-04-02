/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;

import java.util.*;

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
public class Postava extends Observable
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String kecy;
    private boolean friend;
    private int zivoty;
    private int utocneCislo;
    private Map<String, Vec> loot;
    //== Konstruktory a tovární metody =============================================
    /**
     * Konstruktor pro vytvoření postavy.
     *
     * @param nazev Název postavy, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param friend Boolean hodnota určující přátelskou postavu nebo ne.
     * @param zivoty Určuje hodnotu životů.
     * @param utocneCislo Určuje hodnotu útočného čísla.
     */
    public Postava(String nazev, boolean friend, int zivoty, int utocneCislo)
    {
        this.nazev = nazev;
        this.friend = friend;
        this.zivoty = zivoty;
        this.utocneCislo = utocneCislo;
        loot = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Getter na zjištění názvu postavy v prostoru.
     * 
     * @return String nazev.
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Getter na zjištění názvu útočného čísla postavy.
     * 
     * @return int utocneCislo.
     */
    public int getUtocneCislo(){
        return utocneCislo;
    }
    
    /**
     * Getter na zjištění "kamarádství" postavy. Podle této hodnoty se s postavou buďto bojuje a nebo jen mluví.
     * 
     * @return boolean friend.
     */
    public boolean zjistiFriend(){
        return friend;
    }
    
    /**
     * Getter na zjištění počtu životů postavy.
     * 
     * @return int zivoty.
     */
    public int ukazZivoty(){
        return zivoty;
    }

    /**
     * Setter na počet životů. Používá se hlavně v příkazu souboj.
     * 
     * @param uber Určuje číslo, které se od hodnoty životů odečte.
     * 
     * @return int zivoty.
     */
    public int seberZivoty(int uber){
        zivoty -= uber;
        return zivoty;
    }
    
    /**
     * Vloží věc postavě do inventáře (do mapy) s věcmi.
     * 
     * @param Vec neco představuje odkaz na konkrétní věc.
     */
    public void vlozVec(Vec neco){
        loot.put(neco.getNazev(),neco);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Getter na zjištění přítomnosti věcí v inventáři postavy.
     * 
     * @return boolean.
     */
    public boolean maVec(){
        if(!loot.isEmpty()){
            return true;
        }
        return false;
    }
    
    /**
     * Getter na inventář, tedy mapu s věcmi od postavy.
     * 
     * @return Map<String, Vec> loot.
     */
    public Map<String, Vec> getLoot(){
        return loot;
    }
    
    /**
     * Setter na sdělení postavy.
     * 
     * @param String kecy představuje řetězec se sdělením.
     */
    public void setKecy(String kecy){
        this.kecy = kecy;
    }
    
    /**
     * Getter na sdělení postavy.
     * 
     * @return String kecy.
     */
    public String getKecy(){
        return kecy;
    }
    
    @Override
    public String toString() {
    	return getNazev();
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
