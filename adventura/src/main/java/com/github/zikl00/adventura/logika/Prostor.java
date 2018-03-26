package com.github.zikl00.adventura.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Libor Zíka
 * @version pro školní rok 2015/2016 LS
 */
public class Prostor {
    
    private int level;  //úroveň kvůli generování
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;  //obsahuje veci v prostoru
    private Postava postava; //popisuje postavu v prostoru
    private String nazevKlice;  //jestli bude pro vstup do prostoru vyžadován klíč, je nutno nastavit hodnotu této proměnné
    private Map<String, Prostor> vychodyProMapu; //vychody prostoru pro tajny prikaz "mapa"
    
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník před domem"
     *
     * @param level Určuje úroveň prostoru pro generování východů.
     * @param nazev Název prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(int level, String nazev, String popis) {
        this.level = level;
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postava = null;
        vychodyProMapu = new HashMap<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
        vychodyProMapu.put(vedlejsi.getNazev(), vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru - " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci()
                + popisPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev() + ",";
        }
        vracenyText = vracenyText.substring(0, vracenyText.length()-1);
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci v aktuálním prostoru:
     * "Předměty: léčivý_lektvar, palcát".
     *
     * @return Popis věcí v aktuálním prostoru.
     */
    private String popisVeci(){
         String vracenyText = "Věci:";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev  + ",";  //úhlednější výpis s čárkami mezi slovy
        }
        if (vracenyText.length()>5){
            vracenyText = vracenyText.substring(0, vracenyText.length()-1); //umaže poslední znak z řetězce, konkrétně tu poslední čárku
            return vracenyText;}
        else{return "Žádné věci tu nejsou.";}
    }
    
    /**
     * Vrací textový řetězec, který popisuje postavu v aktuálním prostoru:
     * "Postava: zloděj".
     *
     * @return Popis postavy v aktuálním prostoru.
     */
    private String popisPostav(){
        String vracenyText = "\nPostava:";
        if (postava != null){
            vracenyText += " " + postava.getNazev();
            return vracenyText;}
        
        else{return "\nNikdo jiný tu není.";}
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor lze upravovat kvůli správnému
     * výpisu tajného příkazu "mapa".
     *
     * @return Kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Map<String, Prostor> getVychodyProMapu() {
        return vychodyProMapu;
    }
    
    /**
     * Vloží věc z parametru do mapy s věcmi v prostoru.
     * 
     * @param Vec neco Předává metodě věc, která se přidá do mapy.
     */
    public void vlozVec(Vec neco){
        veci.put(neco.getNazev(),neco);
    }
    
    /**
     * Odebere věc z parametru z mapy s věcmi v prostoru.
     * 
     * @param String nazev Předává metodě věc, která se odebere z mapy.
     * 
     * @return String název věci.
     */
    public Vec odeberVec(String nazev){
        return veci.remove(nazev);
    }
    
    /**
     * Vloží postavu z parametru do prostoru.
     * 
     * @param Postava nekdo Předává metodě postavu, která se přidá do prostoru.
     */
    public void vlozPostavu(Postava nekdo){
        postava = nekdo;
    }
    
    /**
     * Odebere postavu z prostoru.
     * 
     */
    public void odeberPostavu(){
        postava = null;
    }
    
    /**
     * Getter na postavu z prostoru.
     * 
     * @return Postava postava.
     */
    public Postava getPostava(){
        return postava;
    }
    
    /**
     * Vrátí mapu s věcmi v prostoru (getter).
     * 
     * @return Map<String, Vec> veci.
     */
    public Map<String, Vec> getVeci(){
        return veci;
    }
    
    /**
     * Getter na zjištění, jestli je postava v prostoru přátelská nebo ne. Jestli postava v prostoru není, je nutno ošetřit předem.
     * 
     * @return boolean.
     */
    public boolean pratelskaPostava(){
        return postava.zjistiFriend();
    }
    
    /**
     * Setter na klíč kvůli přístupu do prostoru. Pro přístup do prostoru bude potřeba, aby měl hráč v inventáři klíč s tímto názvem.
     *
     * @param String klicek Předává metodě název klíče, který se nastaví pro přístup do prostoru.
     */
    public void setKlic(String klicek){
        nazevKlice = klicek;//nazev klice
    }
    
    /**
     * Getter na zjištění názvu klíče pro vstup do prostru.
     * 
     * @return String nazevKlice.
     */
    public String getKlic(){
        return nazevKlice;
    }
    
    /**
     * Getter na zjištění potřeby klíče pro vstup do prostoru.
     * 
     * @return boolean.
     */
    public boolean jePotrebaKlic(){
        if(nazevKlice == null){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public String toString() {
    	return getNazev();
    }
}
