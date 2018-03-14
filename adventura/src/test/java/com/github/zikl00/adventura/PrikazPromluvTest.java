package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Postava;
import com.github.zikl00.adventura.logika.Prostor;

/**
 * The test class PrikazPromluvTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazPromluvTest
{
    private Hra hra;
    private String nazev = "promluv";
    /**
     * Nastaví počáteční hodnoty pro každý test.
     */
    @Before
    public void setUp()
    {
        hra = new Hra();
        Prostor randomProstor = new Prostor(0, "náhodný_prostor","Náhodný popis náhodného prostoru.");
        hra.getHerniPlan().setAktualniProstor(randomProstor);
    }

    /**
     * Otestuje přítomnost parametru zadaného za příkazem.
     */
    @Test
    public void testParametru() {
        assertEquals("Příkaz promluv je bez parametru.", hra.zpracujPrikaz("promluv parametr")) ;
    }
    
    /**
     * Otestuje přítomnost postavy. V prostoru není postava.
     */
    @Test
    public void testPritomnosti() {
        assertEquals("Nikdo tu není.", hra.zpracujPrikaz(nazev)) ;
    }
    
    /**
     * Otestuje, zda-li chce postava něco sdělit nebo ne.
     * Stane se tak, pokud je postava přátelská a nemá
     * nadefinováné sdělení pro hráče.
     */
    @Test
    public void testOchoty() {
        Postava goblin = new Postava ("goblin", true, 8, 6);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(goblin);
        assertEquals("goblin ti nechce nic říct.", hra.zpracujPrikaz(nazev));
    }
    
    /**
     * Otestuje, zda-li postava správně předá sdělení.
     * Postava musí být přátelská.
     */
    @Test
    public void testPostavaMluvi() {
        Postava goblin = new Postava ("goblin", true, 8, 6);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(goblin);
        goblin.setKecy("ahoj, jak to jde?");
        assertEquals("ahoj, jak to jde?", hra.zpracujPrikaz(nazev));
    }
    
    /**
     * Test nepřátelské postavy, nepředá žádné sdělení.
     */
    @Test
    public void testNepritelNemluvi() {
        Postava goblin = new Postava ("goblin", false, 8, 6);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(goblin);
        assertEquals("Nepřítel si s tebou moc povídat nebude...", hra.zpracujPrikaz(nazev));
    }
}
