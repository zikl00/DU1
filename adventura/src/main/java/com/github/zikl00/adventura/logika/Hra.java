package com.github.zikl00.adventura.logika;
import java.util.*;
/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Libor Zíka
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(this));
        platnePrikazy.vlozPrikaz(new PrikazZahod(this));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(this));
        platnePrikazy.vlozPrikaz(new PrikazZivoty(this));
        platnePrikazy.vlozPrikaz(new PrikazInventar(this));
        platnePrikazy.vlozPrikaz(new PrikazPopis(this));
        platnePrikazy.vlozPrikaz(new PrikazSouboj(this));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(this));
        platnePrikazy.vlozPrikaz(new PrikazMapa(this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče spolu s informačními příkazy.
     */
    public String vratUvitani() {
        return "\nVítej!\n" +
               "Jsi hlavní hrdinkou fantasy příběhu, který se odehrává v minulosti.\n" + 
               "Jsi mladou dívkou s temně rudými vlasy a mimořádnými bojovými schopnostmi.\n" + 
               "Když jsi přišla zpět domů, zjistila si, že celá vesnice, kde žiješ, byla vypálena a tvůj malý bratr byl pravděpodobně unesen.\n" + 
               "Ani chvíli neváháš a vydáš se po stopách neznámých útočníku zachránit svého posledního příbuzného.\n" +
               "Po celou dobu hry můžeš zadávat tyto příkazy: " +
               platnePrikazy.vratNazvyPrikazu() +
               "\n\n" + "Tvé životy: " +
               getHerniPlan().getHrdinka().ukazZivoty() +
               " a útočné číslo: " +  getHerniPlan().getHrdinka().ukazUtocneCislo() + "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Příště naviděnou.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }
    
    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if(herniPlan.jeVyhra()){
                konecHry = true;
            }
        }
        else {
            textKVypsani="Neznámý příkaz, pro seznam příkazů napiště: \"napoveda\"";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry hodnota false = konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

