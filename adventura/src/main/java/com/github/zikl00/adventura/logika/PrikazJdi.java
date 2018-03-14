package com.github.zikl00.adventura.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Libor Zíka
 *@version    pro školní rok 2015/2016
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra, který potřebuje třída kvůli metodám,
    *  např. ke zjištění jestli má postava správný klíč pro vstup.
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
    }
    
    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Zadej název oblasti kam chceš jít";
        }
        String smer = parametry[0];
        //zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = hra.getHerniPlan().getAktualniProstor().vratSousedniProstor(smer);
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if(hra.getHerniPlan().getAktualniProstor().getPostava() != null && !(hra.getHerniPlan().getAktualniProstor().pratelskaPostava())){
                hra.getHerniPlan().getHrdinka().seberZivoty(2);
                System.out.println(hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + " tě praštil za 2 životy, protože jsi se otočila zády.");
                if( hra.getHerniPlan().getHrdinka().vratZivotyCislo() < 1){
                       System.out.println("\nUmřela jsi a tvé dobrodružství zde končí...");
                       return "";
                   }
            }
            if(sousedniProstor.jePotrebaKlic()){
                if (hra.getHerniPlan().getHrdinka().vratInventar().containsKey(sousedniProstor.getKlic())){
                    System.out.println("Máš správný klíč a můžeš vejít.");
                }else{
                    return "Nemáš klíč.";
                }
            }
            hra.getHerniPlan().setAktualniProstor(sousedniProstor);
            return "\n" + sousedniProstor.dlouhyPopis();
        }
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

}
