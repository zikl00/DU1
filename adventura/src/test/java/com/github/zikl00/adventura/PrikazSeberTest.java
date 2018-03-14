package com.github.zikl00.adventura;



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Prostor;
import com.github.zikl00.adventura.logika.Vec;

/**
 * The test class PrikazSeberTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazSeberTest
{
    private Hra hra;
    /**
     * Nastaví počáteční hodnoty pro každý test.
     */
    @Before
    public void setUp()
    {
        hra = new Hra();
    }

    /**
     * Test přítomnosti parametru.
     */
    @Test
    public void testNoParameter() {
        assertEquals("Co mám sebrat?", hra.zpracujPrikaz("seber")) ;
    }
    
    /**
     * Otestuje přítomnost zadané věci z parametru,
     * jestli se nachází v aktuálním prostoru.
     */
    @Test
    public void testPritomnosti() {
        hra.getHerniPlan().getAktualniProstor().odeberVec("dýka");
        assertEquals("Nic takového tu není.", hra.zpracujPrikaz("seber dýka")) ;
    }
    
    /**
     * Test zlé postavy, pokud je nepřítel v aktuálním
     * prostoru, není možné sebrat věc. Hráčovi ubere 2 životy.
     */
    @Test
    public void testZlePostavy() {
        int maxHP = hra.getHerniPlan().getHrdinka().vratZivotyCislo();
        Vec zmrzlina = new Vec ("zmrzlina", true, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(zmrzlina);
        assertEquals("", hra.zpracujPrikaz("seber zmrzlina"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(zmrzlina));
        assertEquals(hra.getHerniPlan().getHrdinka().vratZivotyCislo(), maxHP - 2);
    }
    
    /**
     * Test plného inventáře, není možné sebrat věc.
     */
    @Test
    public void testPlnyInventar() {
        Vec zmrzlina = new Vec ("zmrzlina", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(zmrzlina);
        Vec nanuk = new Vec ("nanuk", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(nanuk);
        Vec susenka = new Vec ("susenka", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(susenka);
        Vec banan = new Vec ("banan", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(banan);
        Vec jablko = new Vec ("jablko", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(jablko);
        Vec rajce = new Vec ("rajce", true, 4);
        hra.getHerniPlan().getHrdinka().vlozVec(rajce);
        
        Vec kalhoty = new Vec ("kalhoty", true, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(kalhoty);
        assertEquals("Víc věcí se do inventáře nevejde.", hra.zpracujPrikaz("seber kalhoty"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(kalhoty));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(kalhoty));
    }
    
    /**
     * Test věci, která nelze sebrat z aktuálního prostoru.
     */
    @Test
    public void testTezkaVec() {
        Vec zmrzlina = new Vec ("zmrzlina", false, 4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(zmrzlina);
        assertEquals("", hra.zpracujPrikaz("seber zmrzlina"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(zmrzlina));
        assertEquals(false, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(zmrzlina));
    }
    
    /**
     * Test příkazu seber, které proběhně správně.
     */
    @Test
    public void testSpravneSebrani() {
        Prostor sklepik = new Prostor(2, "sklep","je tu tma a..");
        Vec chleba = new Vec ("chleba", true, -5);
        sklepik.vlozVec(chleba);
        hra.getHerniPlan().setAktualniProstor(sklepik);
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(chleba));
        hra.zpracujPrikaz("seber chleba");
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().getVeci().containsValue(chleba));
        assertEquals(true, hra.getHerniPlan().getHrdinka().vratInventar().containsValue(chleba));
    }
}













