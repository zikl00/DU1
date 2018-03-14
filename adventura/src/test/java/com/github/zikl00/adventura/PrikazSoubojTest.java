package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.Postava;
import com.github.zikl00.adventura.logika.Prostor;
import com.github.zikl00.adventura.logika.Vec;

/**
 * The test class PrikazSoubojTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazSoubojTest
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
    public void testParam() {
        assertEquals("Příkaz souboj je bez parametru.", hra.zpracujPrikaz("souboj goblin"));
    }
    
    /**
     * Otestuje NEpřítomnost postavy.
     */
    @Test
    public void testPostavPritomna() {
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        assertEquals("Nikdo tu není.", hra.zpracujPrikaz("souboj"));
    }
    
    /**
     * Otestuje příkaz souboj pro prostor
     * s přátelskou postavou.
     */
    @Test
    public void testPritel() {
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        Postava spoluzak = new Postava ("spolužák", true, 20, 12);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(spoluzak);
        assertEquals("Nemůžeš bojovat s přátelskou postavou.", hra.zpracujPrikaz("souboj"));
    }
        
    /**
     * Otestuje situaci po příkazu souboj když
     * hráč VYHRAJE && postava NEMÁ loot
     */
    @Test
    public void testPostavaBezLootu() {
        int pocetVeci =  hra.getHerniPlan().getAktualniProstor().getVeci().size();
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        
        Postava pavouk = new Postava ("pavouk", false, 1, 1);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(pavouk);
        hra.zpracujPrikaz("souboj");
        assertEquals(pocetVeci, hra.getHerniPlan().getAktualniProstor().getVeci().size());
        assertEquals(null, hra.getHerniPlan().getAktualniProstor().getPostava());
    }
    
    /**
     * Otestuje situaci po příkazu souboj když
     * hráč VYHRAJE && postava MÁ loot
     */
    @Test
    public void testPostavaSLootem() {
        int pocetVeci =  hra.getHerniPlan().getAktualniProstor().getVeci().size();
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        
        Postava pavouk = new Postava ("pavouk", false, 1, 1);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(pavouk);
        Vec klicek = new Vec ("klíček", true, 0);
        hra.getHerniPlan().getAktualniProstor().getPostava().vlozVec(klicek);
        
        hra.zpracujPrikaz("souboj");
        assertEquals(pocetVeci + 1, hra.getHerniPlan().getAktualniProstor().getVeci().size());
        assertEquals(null, hra.getHerniPlan().getAktualniProstor().getPostava());
    }
    
    /**
     * Otestuje situaci po příkazu souboj když hráč PROHRAJE
     */
    @Test
    public void testSoubojProhra() {
        int pocetVeci =  hra.getHerniPlan().getAktualniProstor().getVeci().size();
        hra.getHerniPlan().getAktualniProstor().odeberPostavu();
        
        Postava pavouk = new Postava ("pavouk", false, 20, 25);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(pavouk);
        hra.getHerniPlan().getHrdinka().seberZivoty(12);
        hra.zpracujPrikaz("souboj");
        assertEquals(pavouk, hra.getHerniPlan().getAktualniProstor().getPostava());
        assertEquals(true, hra.konecHry());
    }
    
    /**
     * Test konce hry, který je následkem příkazu souboj (čaroděj).
     * Konec hry nastane, pokud se nachází hrdinka v posledním prostoru
     * a zabije postavu v něm.
     */
    @Test
    public void testZabitiFinalBosse() {
        Prostor komnata = new Prostor(1, "kouzelníkova_komnata","kouzelníkova komnata, finální prostor");
        Postava pavouk = new Postava ("pavouk", false, 1, 1);
        hra.getHerniPlan().setAktualniProstor(komnata);
        hra.getHerniPlan().getAktualniProstor().vlozPostavu(pavouk);
        hra.zpracujPrikaz("souboj");
        assertEquals(null, hra.getHerniPlan().getAktualniProstor().getPostava());
        assertEquals(true, hra.konecHry());
    }
}







