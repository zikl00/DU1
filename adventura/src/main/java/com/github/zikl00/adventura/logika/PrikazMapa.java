package com.github.zikl00.adventura.logika;
import java.util.*;
/**
 *  Třída PrikazMapa implementuje pro hru TAJNÝ příkaz popis.
 *  Nebude se vypisovat v seznamu použitelných příkazů ani v nápovědě.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Libor Zíka
 *@version    1.02
 */
public class PrikazMapa implements IPrikaz
{

    private static final String NAZEV = "mapa";
    private Hra hra;
    int[] indexy = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    
    /**
     * Konstruktor třídy
     * 
     * @param plan Herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazMapa(Hra hra) {
        this.hra = hra;
    }

    /**
     *  Vrací rekurzivní výpis možných cest po herním plánu.
     *  
     *  @return popis k aktuálnímu prostoru.
     */
    @Override
    public String proved(String... parametry) {
    	String vratitRetezec;
    	vratitRetezec = hra.getHerniPlan().getAktualniProstor().getNazev() + "\n";
        //System.out.println (hra.getHerniPlan().getAktualniProstor().getNazev());
        //return (vypisMapa(null, hra.getHerniPlan().getAktualniProstor(), 0, indexy));
    	vratitRetezec +=  vypisMapa(null, hra.getHerniPlan().getAktualniProstor(), 0, indexy);
    	return vratitRetezec;
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

    /**
     * Vrací rekurzivní výpis možných cest po herním plánu.
     * Výpis má podobu adresářové struktury s úrovněmi.
     * 
     * @param Prostor praRodic Předává metodě prostor ze kterého jsme přišli.
     * @param Prostor rodic Předává metodě prostor, který je rodičem
     * pro potomky, ze kterých se dělá výpis ve for cyklu.
     * @param int level Předává metodě číslo úrovně, kvůli
     * správnému odsazení ve struktuře výpisu.
     * @param int[] indexy Předává pole s hodnotami 0 nebo 1, kvůli
     * správnému formátování výpisu. Lépe řečeno, kvůli rovným čárám
     * ve výpisu, aby byla lépe reprezentována struktura a byla
     * lepší čitelnost.
     * 
     * @return String část výpisu.
     */
    	private String vypisMapa(Prostor praRodic, Prostor rodic, int level, int[] indexy){
        String mezery_1 = "      ";
        String mezery_2 = "    |     ";
        String retezecVrat = "";
        if(praRodic != null){
            rodic.getVychodyProMapu().remove(praRodic.getNazev());
        }
        int citac = 1;
        for(Prostor potomek : rodic.getVychodyProMapu().values()){
            if (citac == rodic.getVychodyProMapu().size()){
                    indexy[level] = 0;
                }else{
                    indexy[level] = 1;
                }
            citac ++;
            if(!potomek.equals(praRodic)){
                //System.out.print(mezery_1);
            	retezecVrat += mezery_1;
                for(int i = 0; i<level; i++){
                    if( indexy[i] == 0){
                        //System.out.print(mezery_1);
                    	retezecVrat += mezery_1;
                    }else{
                        //System.out.print(mezery_2);
                    	retezecVrat += mezery_2;
                    }                    
                }
                //System.out.println("\\--- " + potomek.getNazev());
                retezecVrat += "    \\--- " + potomek.getNazev() + "\n";
                //System.out.print(vypisMapa(rodic, potomek, level + 1, indexy));
                retezecVrat += vypisMapa(rodic, potomek, level + 1, indexy);
            }
        }
        if(praRodic != null){
            rodic.getVychodyProMapu().put(praRodic.getNazev() , praRodic);
        }
        return retezecVrat;
    }
    	//vypis mapa - záloha
    	/*
    	 * private String vypisMapa(Prostor praRodic, Prostor rodic, int level, int[] indexy){
        String mezery_1 = "      ";
        String mezery_2 = "|     ";
        if(praRodic != null){
            rodic.getVychodyProMapu().remove(praRodic.getNazev());
        }
        int citac = 1;
        for(Prostor potomek : rodic.getVychodyProMapu().values()){
            if (citac == rodic.getVychodyProMapu().size()){
                    indexy[level] = 0;
                }else{
                    indexy[level] = 1;
                }
            citac ++;
            if(!potomek.equals(praRodic)){
                System.out.print(mezery_1);
                for(int i = 0; i<level; i++){
                    if( indexy[i] == 0){
                        System.out.print(mezery_1);
                    }else{
                        System.out.print(mezery_2);
                    }                    
                }
                System.out.println("\\--- " + potomek.getNazev());
                System.out.print(vypisMapa(rodic, potomek, level + 1, indexy));
            }
        }
        if(praRodic != null){
            rodic.getVychodyProMapu().put(praRodic.getNazev() , praRodic);
        }
        return "";
    }
    	 */
    	
}





