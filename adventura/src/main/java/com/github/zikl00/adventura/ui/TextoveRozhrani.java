package com.github.zikl00.adventura.ui;

import java.util.Scanner;
//importy na streamy
import java.io.*;
import com.github.zikl00.adventura.logika.IHra;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());
        //základní cyklus programu - opakovaně se čtou příkazy a poté se provádějí do konce hry.
        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    //metoda pro hru ze souboru - nepoužívá se stejně
    public void hrajZeSouboru(String nazevSouboru) {
        System.out.println(hra.vratUvitani());
        try (BufferedReader ctecka = new BufferedReader (new FileReader(nazevSouboru))){
            //cteni ze souboru
            //FileReader cte znak
            //BufferedReader
            String radek = ctecka.readLine();
            while (!hra.konecHry() && radek != null) {
                //String radek = ctecka.readLine();
                //takhle ale bude chyba v kodovani, je potreba ulozit txt soubor jako, a tam nastavit kodovani na UTF-8
                //mozna doladit tak, ze se sezere prvni znak a necha se zahodit. UTF-8 necha na zacatku souboru nejaky balast
                System.out.println("*"+radek+"*");
                System.out.println(hra.zpracujPrikaz(radek));
                radek = ctecka.readLine();
            }
            
            System.out.println(hra.vratEpilog());
        }
        catch(FileNotFoundException e){
            System.out.println("Soubor nenalezen - " +e);
        }
        catch(IOException e){
            System.out.println("Chyba vstupu - " +e);
        }
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
