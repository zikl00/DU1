package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Postava;
import com.github.zikl00.adventura.logika.Prostor;
import com.github.zikl00.adventura.logika.Vec;

/**
 * The test class PrikazJdiTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazJdiTest
{
    private Hra hra;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        hra = new Hra();
    }
    
    /**
     * Otestuje přítomnost parametru.
     */
    @Test
    public void testParametru() {
        assertEquals("Zadej název oblasti kam chceš jít", hra.zpracujPrikaz("jdi"));
    }
    
    /**
     * Otestuje nepřítomnost sousedního prostoru z parametru
     * nebo nesmyslného parametru.
     */
    @Test
    public void testNeexistujeSoused() {
        assertEquals("Tam se odsud jít nedá!", hra.zpracujPrikaz("jdi domů"));
    }
    
    /**
     * Otestuje obyčejný průchod příkazem jdi.
     */
    @Test
    public void testNormalOdchod() {
        Prostor trida = new Prostor(1, "třída","třída pro 30 žáků");
        Prostor chodba = new Prostor(1, "chodba","chodba, klasická školní chodba");
        hra.getHerniPlan().setAktualniProstor(trida);
        trida.setVychod(chodba);
        chodba.setVychod(trida);
        assertEquals(trida, hra.getHerniPlan().getAktualniProstor());
        hra.zpracujPrikaz("jdi chodba");
        assertEquals(chodba, hra.getHerniPlan().getAktualniProstor());
    }
    
    /**
     * Otestuje systém klíčů.
     */
    @Test
    public void testKlice() {
        Prostor trida = new Prostor(1, "třída","třída pro 30 žáků");
        Prostor chodba = new Prostor(1, "chodba","chodba, klasická školní chodba");
        hra.getHerniPlan().setAktualniProstor(chodba);
        trida.setVychod(chodba);
        chodba.setVychod(trida);
        Vec klicKeTride = new Vec ("klíč_od_třídy", true, 0);
        chodba.vlozVec(klicKeTride);
        trida.setKlic("klíč_od_třídy");
        
        assertEquals("Nemáš klíč.", hra.zpracujPrikaz("jdi třída"));
        assertEquals(chodba, hra.getHerniPlan().getAktualniProstor());
        hra.zpracujPrikaz("seber klíč_od_třídy");
        hra.zpracujPrikaz("jdi třída");
        assertEquals(trida, hra.getHerniPlan().getAktualniProstor());
    }
    
    /**
     * Otestuje odchod z prostoru kde je nepřátelská postava.
     */
    @Test
    public void testOdchodNepritel() {
        int zivoty = hra.getHerniPlan().getHrdinka().vratZivotyCislo();
        Prostor trida = new Prostor(1, "třída","třída pro 30 žáků");
        Prostor chodba = new Prostor(1, "chodba","chodba, klasická školní chodba");
        hra.getHerniPlan().setAktualniProstor(trida);
        trida.setVychod(chodba);
        chodba.setVychod(trida);
        Postava ucitel = new Postava ("učitel", false, 10, 8);
        trida.vlozPostavu(ucitel);
        assertEquals(trida, hra.getHerniPlan().getAktualniProstor());
        hra.zpracujPrikaz("jdi chodba");
        assertEquals(chodba, hra.getHerniPlan().getAktualniProstor());
        assertEquals(zivoty - 2, hra.getHerniPlan().getHrdinka().vratZivotyCislo());
    }
}











