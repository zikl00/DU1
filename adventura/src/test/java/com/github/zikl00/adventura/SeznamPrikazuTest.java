package com.github.zikl00.adventura;

import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.*;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček, Libor Zíka
 * @version   pro školní rok 2015/2016
 */
public class SeznamPrikazuTest
{
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazZivoty prZivoty;
    
    /**
     * Nastaví počáteční hodnoty pro každý test.
     */
    @Before
    public void setUp() {
        Hra hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra);
        prZivoty = new PrikazZivoty(hra);
    }

    /**
     * Otestuje přikaz, zda-li se dá použit, pokud se vložil.
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prZivoty);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
        assertEquals(prZivoty, seznPrikazu.vratPrikaz("životy"));
    }
    
    /**
     * Otestuje platnost příkazu, tedy jestli se dá použít.
     */
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("životy"));
    }
    
    /**
     * Otestuje názvy příkazů, jestli se volají tak, jak byly definovány
     */
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prZivoty);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
        assertEquals(false, nazvy.contains("zivoty"));
    }
    
}
