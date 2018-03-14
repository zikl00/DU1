package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Vec;

/**
 * The test class PrikazPouzijTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazPouzijTest
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
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
    }
    
    /**
     * Otestuje přítomnost parametru.
     */
    @Test
    public void testParametru() {
        assertEquals("Co mám použít?", hra.zpracujPrikaz("použij"));
    }
    
    /**
     * Otestuje přítomnost věci zadané z příkazu,
     * zda-li je v inventáři.
     */
    @Test
    public void testPritomnosti() {
        assertEquals("To v inventáři nemáš.", hra.zpracujPrikaz("použij pivo"));
    }
    
    /**
     * Otestování věci, zda-li se správně použije,
     * tedy vloží do ruky, Má se také zvýšit útočné číslo.
     * V ruce předtím nebyl žádný předmět.
     */
    @Test
    public void testEquipnutiPrvni() {
        int utok = hra.getHerniPlan().getHrdinka().ukazUtocneCislo();
        Vec kladivo = new Vec ("kladivo", true, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(kladivo);
        
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
        hra.zpracujPrikaz("seber kladivo");
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
        assertEquals("teď bojuješ s: kladivo", hra.zpracujPrikaz("použij kladivo"));
        assertEquals(utok + 4, hra.getHerniPlan().getHrdinka().ukazUtocneCislo());
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
    }
    
    /**
     * Otestování věci, zda-li se správně použije,
     * tedy vloží do ruky, Má se také zvýšit útočné číslo.
     * V ruce předtím byl používaný předmět.
     * Otestuje také, zda-li se věci správně vyměnili.
     * 
     */
    @Test
    public void testEquipnutiDruhe() {
        Vec kladivo = new Vec ("kladivo", true, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(kladivo);
        int utok = hra.getHerniPlan().getHrdinka().ukazUtocneCislo();
        Vec tomahawk = new Vec ("tomahawk", true, 8);
        hra.getHerniPlan().getAktualniProstor().vlozVec(tomahawk);
        
        hra.zpracujPrikaz("seber tomahawk");
        hra.zpracujPrikaz("použij tomahawk");
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(tomahawk));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(tomahawk));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(tomahawk));
        hra.zpracujPrikaz("seber kladivo");
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(tomahawk));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(tomahawk));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(tomahawk));
        assertEquals("teď bojuješ s: kladivo", hra.zpracujPrikaz("použij kladivo"));
        assertEquals(utok + 4, hra.getHerniPlan().getHrdinka().ukazUtocneCislo());
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratEquipped().containsValue(tomahawk));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kladivo));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(tomahawk));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kladivo));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(tomahawk));
    }
    
    /**
     * Otestování správného použití léčivé věci.
     * Hráč NEPOTŘEBUJE vyléčit.
     */
    @Test
    public void testHealPrvni() {
        Vec pivo = new Vec ("pivo", true, -4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(pivo);
        
        hra.zpracujPrikaz("seber pivo");
        assertEquals("", hra.zpracujPrikaz("použij pivo"));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(pivo));
    }
    
    /**
     * Otestování správného použití léčivé věci.
     * Hráč POTŘEBUJE vyléčit.
     */
    @Test
    public void testHealDruhy() {
        Vec pivo = new Vec ("pivo", true, -4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(pivo);
        
        int zivoty = hra.getHerniPlan().getHrdinka().vratZivotyCislo();
        hra.zpracujPrikaz("seber pivo");
        hra.getHerniPlan().getHrdinka().seberZivoty(6);
        assertEquals("", hra.zpracujPrikaz("použij pivo"));
        assertEquals(zivoty - 2, hra.getHerniPlan().getHrdinka().vratZivotyCislo());
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(pivo));
    }
    
}








