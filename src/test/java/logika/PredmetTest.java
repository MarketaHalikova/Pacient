package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída PredmetTest slouží k testování metod třídy Predmet
 * 
 */
public class PredmetTest {

    private final static String NAZEV = "nazev predmetu";
    private final static String POPIS = "popis predmetu";
    private final static String POPIS_2 = "popis predmetu 2";
    private final static boolean PRENOSITELNY = false;
    private final static boolean DEFAULT_PRENOSITELNY = true;
    
    private Predmet predmetDvaParametry;
    private Predmet predmetTriParametry;
    
    @Before
    public void setUp() {
        predmetDvaParametry = new Predmet(NAZEV, POPIS);
        predmetTriParametry = new Predmet(NAZEV, POPIS, PRENOSITELNY);
    }


    /**
     * Testovací metoda metody getNazev třídy Predmet.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV, predmetDvaParametry.getNazev());
        assertEquals(NAZEV, predmetTriParametry.getNazev());
    }

    /**
     * Testovací metoda metody getPopis třídy Predmet.
     */
    @Test
    public void testGetPopis() {
        assertEquals(POPIS, predmetDvaParametry.getPopis());
        assertEquals(POPIS, predmetTriParametry.getPopis());
    }

    /**
     * Testovací metoda metody isPrenositelny třídy Predmet.
     */
    @Test
    public void testIsPrenositelny() {
        assertEquals(DEFAULT_PRENOSITELNY, predmetDvaParametry.isPrenositelny());
        assertEquals(PRENOSITELNY, predmetTriParametry.isPrenositelny());
    }

    /**
     * Testovací metoda metody setPopis třídy Predmet.
     */
    @Test
    public void testSetPopis() {
        assertEquals(POPIS, predmetDvaParametry.getPopis());
        predmetDvaParametry.setPopis(POPIS_2);
        assertEquals(POPIS_2, predmetDvaParametry.getPopis());
    }

    /**
     * Testovací metoda metody setPrenositelny třídy Predmet.
     */
    @Test
    public void testSetPrenositelny() {
        assertEquals(DEFAULT_PRENOSITELNY, predmetDvaParametry.isPrenositelny());
        predmetDvaParametry.setPrenositelny(PRENOSITELNY);
        assertEquals(PRENOSITELNY, predmetDvaParametry.isPrenositelny());
    }

    /**
     * Testovací metoda metody toString třídy Predmet.
     */
    @Test
    public void testToString() {
        assertEquals("Predmet: " + NAZEV, predmetDvaParametry.toString());

    }
}
        
        
    