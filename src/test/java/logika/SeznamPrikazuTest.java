package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author     Luboš Pavlíček, Jan Říha, Markéta Halíková
 * @version    LS 2016/2017
 */
public class SeznamPrikazuTest{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    
    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
    }

    /**
     * Testovací metoda metody vlozeniVybrani třídy SeznamPrikazu.
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertNull(seznPrikazu.vratPrikaz("napoveda"));
    }
    
    /**
     * Testovací metoda metody jePlatnyPrikaz třídy SeznamPrikazu.
     */
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertTrue(seznPrikazu.jePlatnyPrikaz("konec"));
        assertTrue(seznPrikazu.jePlatnyPrikaz("jdi"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertFalse(seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    /**
     * Testovací metoda metody nazvyPrikazu třídy SeznamPrikazu.
     */
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertTrue(nazvy.contains("konec"));
        assertTrue(nazvy.contains("jdi"));
        assertFalse(nazvy.contains("napoveda"));
        assertFalse(nazvy.contains("Konec"));
    }
    
}
