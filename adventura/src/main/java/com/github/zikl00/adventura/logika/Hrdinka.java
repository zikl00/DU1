/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.logika;

import java.util.*;

/*******************************************************************************
 * Třída Hrdinka - popisuje hrdinku, tedy hlavní postavu, za kterou hráč hraje.
 * 
 * "Hrdinka" reprezentuje hlavní postavu. Postava počet životů,
 * maximální počet životů, vlastní útočné číslo a mapu s věcmi
 * které u sebe má v inventáři a mapu s věcí,
 * která je zrovna používaná (equipped, v ruce).
 * 
 * @author    Libor Zíka
 * @version   1.01
 */
public class Hrdinka
{
    //== Datové atributy (statické i instancí)======================================
    private int zivoty;
    private int maxZivoty;
    private int utocneCislo;
    private Hra hra;
    private Map<String, Vec> inventar;
    private Map<String, Vec> equipped;
    //== Konstruktory a tovární metody =============================================

    /**
     * Konstruktor pro vytvoření hlavní postavy.
     *
     * @param hra Instance hry kvůli používání metod ze třídy.
     */
    public Hrdinka(Hra hra)
    {
        zivoty = randInt(6) + randInt(6) + 12;
        maxZivoty = zivoty;
        utocneCislo = randInt(6) + 6;
        this.hra = hra;
        inventar = new HashMap<>();
        equipped = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Getter na počet životů. Používá se hlavně v příkazu souboj.
     * 
     * @return String.
     */
    public String ukazZivoty(){
        return Integer.toString(zivoty) + "/" + Integer.toString(maxZivoty);
    }
    
    /**
     * Getter na útočné číslo.
     * 
     * @return int utocneCislo.
     */
    public int ukazUtocneCislo(){
        return utocneCislo;
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
        if(zivoty < 1){
            hra.setKonecHry(true);//ukoncit
        }
        return zivoty;
    }
    
    /**
     * Setter na počet životů. Používá se při použití léčivého předmětu.
     * 
     * @param pridej Určuje číslo, které se k hodnotě životů přičte.
     * 
     * @return int zivoty.
     */
    public int pridejZivoty(int pridej){
        zivoty += pridej;
        return zivoty;
    }
    
    /**
     * Getter na počet životů.
     * 
     * @return int zivoty.
     */
    public int vratZivotyCislo(){
        return zivoty;
    }
    
    /**
     * Vloží věc do inventáře hlavní postavy.
     * 
     * @param Vec neco Představuje odkaz na věc přidávanou do inventáře.
     */
    public void vlozVec(Vec neco){
        inventar.put(neco.getNazev(),neco);
    }
    
    /**
     * Odebere věc z inventáře hlavní postavy.
     * 
     * @param Vec neco Představuje odkaz na věc odebíranou z inventáře.
     */
    public Vec odeberVec(String nazev){
        return inventar.remove(nazev);
    }
    
    /**
     * Metoda na používání věci. Podle útočného čisla se věc použije. Kladné číslo je číslo zbraně, záporné číslo se odečte od životů hrdinky.
     * 
     * @param Vec neco Představuje odkaz na věc používanou Z INVENTARE (pouze).
     */
    public String pouzijVec(Vec neco){//je to zbytecne slozite, mozna to pak predelam, ale asi ne, kdyz to funguje... :D
        //if(inventar.containsValue(neco)){//takova mala paranoia :D, nemusim protoze je to v prikazu pouzij
    	String retezecVrat = "";
    	if(neco.vratUtocneCislo() < 0){//potion - zaporne cislo utocne
            if(zivoty == maxZivoty){
                //System.out.print("Životy netřeba doplňovat.");
                retezecVrat += "Životy netřeba doplňovat.";
                vlozVec(neco);
            }else{
                zivoty -= neco.vratUtocneCislo();
                if(zivoty > maxZivoty){ zivoty = maxZivoty;}
                //System.out.print("Doplinila jsi si životy: " + ukazZivoty());
                retezecVrat += "Doplinila jsi si životy: " + ukazZivoty();
                //jeste odeber potion
                odeberVec(neco.getNazev());
            }
        }else if(neco.vratUtocneCislo() == 0){
            //System.out.print("Klíč se použije automaticky při vchodu do prostoru.");
            retezecVrat += "Klíč se použije automaticky při vchodu do prostoru.";
        }else if(equipped.isEmpty()){//kdyz neni nic equipnuteho
            equipped.put(neco.getNazev(),neco);
            utocneCislo += neco.vratUtocneCislo();
            inventar.remove(neco);
        }else{
            inventar.putAll(equipped);
            //snad je tam jen jeden prvek, mel by byt
            for (String predmet : equipped.keySet()){
                utocneCislo -= equipped.get(predmet).vratUtocneCislo();
            }
            equipped.clear();
            equipped.put(neco.getNazev(),neco);
            utocneCislo += neco.vratUtocneCislo();
            inventar.remove(neco);
        }
    	return retezecVrat;
    }
    
    /**
     * Setter na věc odebíranou ze "slotu" pro používanou věc.
     * 
     * @param nazev Určuje název věci
     * 
     * @return Vec.
     */
    public Vec odeberEquip(String nazev){
        return equipped.remove(nazev);
    }
    
    /**
     * Metoda na popis věcí z inventáře.
     * 
     * @return String.
     */
    public String popisInventar(){
        String vracenyText = "Inventář (" + (vratInventar().size()) + "/6):";
        for (String nazev : inventar.keySet()) {
            vracenyText += " " + nazev  + ",";  //přidávání čárek pro hezčí výpis
        }
        if (vracenyText.length()>9){
            vracenyText = vracenyText.substring(0, vracenyText.length()-1); //umazání čárky za posledním vypisovaným slovem
            return vracenyText;}
        else{return "Žádné věci v inventáři nemáš.";}
    }
    
    /**
     * Metoda pro výpis věci používané/v ruce.
     * 
     * @return String.
     */
    public String ukazEQ(){ //malá paranoia s výpisem, ale pro jistotu
         String vracenyText = "V ruce máš:";
        for (String nazev : equipped.keySet()) {
            vracenyText += " " + nazev  + ",";
        }
        if (vracenyText.length()>11){
            vracenyText = vracenyText.substring(0, vracenyText.length()-1);
            return vracenyText;}
        else{return "Máš holé ruce.";}
    }
    
    /**
     * Getter na inventář hlavní postavy.
     * 
     * @return Map<String, Vec> inventar.
     */
    public Map<String, Vec> vratInventar(){
        return inventar;
    }
    
    /**
     * Getter na používanou věc z ruky.
     * 
     * @return Map<String, Vec> equipped.
     */
    public Map<String, Vec> vratEquipped(){
        return equipped;
    }
    
    //== Soukromé metody (instancí i třídy) ========================================
    /**
     * Metoda na generování náhodného čísla od 0 do max.
     * 
     * @param max Určuje horní hranici pro generované číslo.
     * 
     * @return int.
     */
    private static int randInt(int max){
        Random rand = new Random();
        return (int)rand.nextInt(max) + 1;
    }

}
