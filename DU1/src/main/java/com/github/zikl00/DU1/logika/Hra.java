package com.github.zikl00.DU1.logika;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


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

public class Hra extends Observable{
	private Map<Integer, Slovicko> slovicka;
    private Pokusy pokusy;
    private String text;
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
    	slovicka = new HashMap<>();
    	pokusy = new Pokusy();
    	Slovicko prvniSlovicko = new Slovicko("brambora","potato");
    	slovicka.put(0, prvniSlovicko);
    	Slovicko druheSlovicko = new Slovicko("brokolice","broccoli");
    	slovicka.put(1, druheSlovicko);
    	Slovicko tretiSlovicko = new Slovicko("dýně","pumpkin");
    	slovicka.put(2, tretiSlovicko);
    	Slovicko ctvrteSlovicko = new Slovicko("kukuřice","corn");
    	slovicka.put(3, ctvrteSlovicko);
    	Slovicko pateSlovicko = new Slovicko("mrkev","carrot");
    	slovicka.put(4, pateSlovicko);
    	Slovicko sesteSlovicko = new Slovicko("rajče","tomato");
    	slovicka.put(5, sesteSlovicko);
    	text = "";
    }
	public void zvysSpravne(){
        pokusy.spravne += 1;
        this.setChanged();
        this.notifyObservers();
    }
	public void zvysSpatne(){
		pokusy.spatne += 1;
		this.setChanged();
	       this.notifyObservers();
    }
	public void resetSpravne(){
        pokusy.spravne = 0;
        this.setChanged();
        this.notifyObservers();
    }
	public void resetSpatne(){
        pokusy.spatne = 0;
        this.setChanged();
        this.notifyObservers();
    }
    public String ukazSpatnePokusy() {
    	return ("" + pokusy.spatne);
    }
    public String ukazSpravnePokusy() {
    	return ("" + pokusy.spravne);
    }
    public void setText(String textZPole) {
    	text = textZPole;
    }
    public String getText() {
    	return text;
    }
    public Map<Integer, Slovicko> vratSlovicka(){
        return slovicka;
    }
   
    
    /*
     public boolean konecHry() {
        return konecHry;
    }
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    */
}