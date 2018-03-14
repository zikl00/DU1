package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Vec;

/**
 * The test class PrikazZahodTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazZahodTest
{
    private Hra hra;
    /**
     * Nastaví počáteční hodnoty pro každý test.
     */
    @Before
    public void setUp()
    {
        hra = new Hra();
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
    }

    /**
     * Otestuje přítomnost parametru.
     */
    @Test
    public void testParametru() {
        assertEquals("co mám zahodit?", hra.zpracujPrikaz("zahoď"));
    }
    
    /**
     * Otestuje přítomnost věci zadané z příkazu,
     * zda-li je v inventáři.
     */
    @Test
    public void testPritomnosti() {
        assertEquals("To v inventáři nemáš.", hra.zpracujPrikaz("zahoď pivo"));
    }
    
    /**
     * Otestování zahození věci z ruky.
     */
    @Test
    public void testEquipu() {
        Vec savle = new Vec ("šavle", true, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(savle);
        
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(savle));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(savle));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(savle));
        hra.zpracujPrikaz("seber šavle");
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(savle));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(savle));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(savle));
        hra.zpracujPrikaz("použij šavle");
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(savle));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(savle));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(savle));
        assertEquals("z ruky jsi zahodila: šavle", hra.zpracujPrikaz("zahoď šavle"));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(savle));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(savle));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(savle));
    }
    
    /**
     * Otestování zahození věci z inventáře.
     */
    @Test
    public void testInventare() {
        Vec pivo = new Vec ("pivo", true, -4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(pivo);
        
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(pivo));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(pivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(pivo));
        hra.zpracujPrikaz("seber pivo");
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(pivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(pivo));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(pivo));
        assertEquals("zahodila jsi: pivo", hra.zpracujPrikaz("zahoď pivo"));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(pivo));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(pivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(pivo));
    }
}
