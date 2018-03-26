package com.github.zikl00.adventura.logika;
import java.util.*;
import java.io.*;
import java.util.Observable;
/**
 *  Třída PrikazSouboj implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.01
 */
class PrikazSouboj extends Observable implements IPrikaz {
    private static final String NAZEV = "souboj";
    private Hra hra;
    
    /**
    * Konstruktor třídy
    *  
    * @param hra je potřeba kvůli metodám ze třídy
    */    
    public PrikazSouboj(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provádí příkaz "souboj". Generuje vždy různá čísla od 0 do 6 a přičítá je k útočnému číslu hlavní hrdinky a nepřítele,
     * pak je porovná a podle toho změní životy u jednoho z bojujících a na konci souboje buďto ukončí hru nebo
     * přesune věci z inventáře nepřítele do prostoru, ty je pak možné sebrat.
     *
     * @param parametry - bez parametru, ošetřeno
     * 
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if(parametry.length != 0){
            return "Příkaz souboj je bez parametru.";
        }
        if(hra.getHerniPlan().getAktualniProstor().getPostava() == null){
            return "Nikdo tu není.";
        }
        if(hra.getHerniPlan().getAktualniProstor().getPostava().zjistiFriend()){
            return "Nemůžeš bojovat s přátelskou postavou.";
        }
        System.out.println("Máš " + hra.getHerniPlan().getHrdinka().ukazZivoty() + " životů a tvoje útočné číslo je: "
            + hra.getHerniPlan().getHrdinka().ukazUtocneCislo());
        System.out.println( hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " má " + 
            hra.getHerniPlan().getAktualniProstor().getPostava().ukazZivoty() + " životů a útočné číslo: " +
            hra.getHerniPlan().getAktualniProstor().getPostava().getUtocneCislo() );
        System.out.println("Souboj začal:");
        int utokHrdinka, utokPostava;
        while(hra.getHerniPlan().getAktualniProstor().getPostava().ukazZivoty()>0){
               /*try {
                   Thread.sleep(1250);//1000 milliseconds is one second.
               } catch(InterruptedException ex) {
                   Thread.currentThread().interrupt();
               }*/
               utokHrdinka = hra.getHerniPlan().randInt(6) + hra.getHerniPlan().randInt(6) + hra.getHerniPlan().getHrdinka().ukazUtocneCislo();
               utokPostava = hra.getHerniPlan().randInt(6) + hra.getHerniPlan().randInt(6) + hra.getHerniPlan().getAktualniProstor().getPostava().getUtocneCislo();
               if(utokHrdinka > utokPostava){
                   hra.getHerniPlan().getAktualniProstor().getPostava().seberZivoty(2);
                   System.out.println("Ubrala jsi protivníkovi 2 životy: "
                   + hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " má "
                   + hra.getHerniPlan().getAktualniProstor().getPostava().ukazZivoty() + " životů");
               }else if (utokPostava > utokHrdinka){
                   hra.getHerniPlan().getHrdinka().seberZivoty(2);
                   System.out.println("Protivník ti ubral 2 životy, máš " + hra.getHerniPlan().getHrdinka().ukazZivoty() + " životů");
                   if( hra.getHerniPlan().getHrdinka().vratZivotyCislo() < 1){
                       System.out.println("\nUmřela jsi a tvé dobrodružství zde končí...");
                       return "";
                   }
               }else{
                   System.out.println("Podařilo se ti vykrýt protivníkův úder.");
               }
        }
        System.out.println ("Souboj skončil úspěšně");
        return uklidPoSouboji();
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
    //== Soukromé metody (instancí i třídy) ========================================
    /**
     * Privátní metoda na provedení "úklidu" prostoru po souboji.
     * Zjistí, zda-li měl nepřítel u sebe předměty a pokud ano,
     * tak je umístí do aktuálního prostoru. Vymaže také postavu
     * z aktuálního prostoru. Pokud je mazaná postava kouzelník,
     * vypíše se zpráva a nastaví se proměnná potřebná k ukončení hry.
     * 
     * @return String řetězec.
     */
    private String uklidPoSouboji(){
        if(hra.getHerniPlan().getAktualniProstor().getPostava().maVec()){
            String vracenyText = hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " byl zabit a upustil loot: ";
            for(Vec predmet: hra.getHerniPlan().getAktualniProstor().getPostava().getLoot().values()){
                vracenyText += ( predmet.getNazev() + ", ");
                hra.getHerniPlan().getAktualniProstor().vlozVec(predmet);
            }
            vracenyText = vracenyText.substring(0, vracenyText.length()-2);
            System.out.print(vracenyText);
            this.setChanged();
            this.notifyObservers();
        }
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        this.setChanged();
        this.notifyObservers();
        if(hra.getHerniPlan().getAktualniProstor().getNazev().equalsIgnoreCase("kouzelníkova_komnata")){
            hra.getHerniPlan().setOsvobodilaBratra();
            return "Blahopřeji, úspěšně se ti podařilo porazit zlého kouzelníka a osvobodit svého bratra.\n" + 
            "Děkujeme, že jste si zahráli. Hru vytvořil Libor Zíka.";
        }
        return "";
    }
}
