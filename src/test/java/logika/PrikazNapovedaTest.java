/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída PrikazNapovedaTest slouží k testování metod třídy PrikazNapoveda
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazNapovedaTest {   
   
    private static final String NAZEV_PRIKAZU = "napoveda";
    private static final String TEXT_NAPOVEDY =  "Tvým úkolem je vyléčit pacienta.\n"
        + "To provedeš tak, že odemkneš jeho hlavu pomocí dvou prášků na hlavu.\n"
        + "\n";

    private static final String PRIKAZY = "Můžeš zadat tyto příkazy:\njdi konec ";

    PrikazNapoveda prikazNapoveda;
    Hra hra;

    @Before
    public void setUp() {
        IPrikaz prikaz1 = new PrikazJdi(null);
        IPrikaz prikaz2 = new PrikazKonec(null);
        SeznamPrikazu prikazy = new SeznamPrikazu();
        prikazy.vlozPrikaz(prikaz1);
        prikazy.vlozPrikaz(prikaz2);
        prikazNapoveda = new PrikazNapoveda(prikazy);
    }

    /**
     * Testovací metoda metody proved
     */
    @Test
    public void testProved() {
         assertEquals(TEXT_NAPOVEDY + PRIKAZY, prikazNapoveda.proved());
    }

    /**
     * Testovací metoda metody getNazev
     */
    @Test
    public void testGetNazev() {
         assertEquals(NAZEV_PRIKAZU, prikazNapoveda.getNazev());
    }
    
}
