package com.github.zikl00.adventura;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.github.zikl00.adventura.logika.Hra;

/**
 * The test class PrikazKonecTest.
 *
 * @author  Libor Zíka
 * @version 1.01
 */
public class PrikazKonecTest
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
     * Otestuje, zda-li se hra správně ukončí
     * v případě nulové a záporné hodnoty
     * životů hrdinky.
     */
    @Test
    public void testSmrti() {
        hra.getHerniPlan().getHrdinka().seberZivoty(100);
        assertEquals(true, hra.konecHry());
    }
    
    /**
     * Otestuje správnost zadávaního příkazu.
     */
    @Test
    public void testPrikazu() {
        hra.zpracujPrikaz("konec");
        assertEquals(true, hra.konecHry());
    }
    
    /**
     * Otestuje přítomnost parametru za příkazem.
     */
    @Test
    public void testPrikazuParam() {
        hra.zpracujPrikaz("konec hry");
        assertEquals(false, hra.konecHry());
    }
}
















