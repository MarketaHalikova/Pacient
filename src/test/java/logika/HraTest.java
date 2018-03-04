package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }


    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * 
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pusa", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi krk");
        assertFalse(hra1.konecHry());
        assertEquals("krk", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi telo");
        assertFalse(hra1.konecHry());
        assertEquals("telo", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());
    }
    
    /***************************************************************************
     * Testuje zda je možnost vyhrát
     * 
     */
    @Test
    public void testVyhry() {
        assertEquals("pusa", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi krk");
        hra1.getHerniPlan().setAktualniLokace(hra1.getHerniPlan().getAktualniLokace().vratSousedniLokaci("hlava"));
        assertEquals("hlava", hra1.getHerniPlan().getAktualniLokace().getNazev());
        // protoze jsem aktualni lokaci nezadala pres prikazJdi, musim zadat prikazJdi aby se vyhra hrace promitla (je implementovana v prikazJdi)
        hra1.zpracujPrikaz("jdi hlava");

        assertTrue(hra1.getHerniPlan().hracVyhral());
        assertTrue(hra1.konecHry());
    }
}
