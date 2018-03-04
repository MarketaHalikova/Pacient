package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída PrikazKonecTest slouží k testování metod třídy PrikazKonec
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazKonecTest {

    private static final String NAZEV_PRIKAZU = "konec";
    private static final String ODPOVED_CHYB_ZADANI = "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
    private static final String ODPOVED_KONEC_HRY = "hra ukončena příkazem konec";

    PrikazKonec prikazKonec;
    Hra hra;

    @Before
    public void setUp() {
        hra = new Hra();
        prikazKonec = new PrikazKonec(hra);
    }

    /**
     * Testovací metoda metody proved
     */
    @Test
    public void testProved() {

        String result = prikazKonec.proved("xxxx");
        assertEquals(ODPOVED_CHYB_ZADANI, result);

        // hra není ukončena
        assertFalse(hra.konecHry());
        
        result = prikazKonec.proved();
        assertEquals(ODPOVED_KONEC_HRY, result);
        
        // hra ukončena
        assertTrue(hra.konecHry());

    }

    /**
     * Testovací metoda metody getNazev
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazKonec.getNazev());
    }

}
