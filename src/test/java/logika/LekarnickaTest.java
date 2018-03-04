package logika;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída LekarnickaTest slouží ke komplexnímu otestování třídy Lekarnicka 
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class LekarnickaTest {
    
    public final static String NAZEV_1 = "predmet1";
    public final static String NAZEV_2 = "predmet2";
    public final static String NAZEV_3 = "predmet3";
    public final static String NAZEV_4 = "predmet4";
    public final static String NEPODSTATNY_TEXT = "nepodstatne";
    
    Lekarnicka lekarnicka; 
    Predmet predmet1;
    Predmet predmet2;
    Predmet predmet3;
    Predmet predmet4;
    
    @Before
    public void setUp() {
        lekarnicka = new Lekarnicka();
        predmet1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        predmet2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
        predmet3 = new Predmet(NAZEV_3, NEPODSTATNY_TEXT);
        predmet4 = new Predmet(NAZEV_4, NEPODSTATNY_TEXT, false);
    }

    /**
     * Testovací metoda metody PocetPredmetu třídy Lekarnicka.
     */
    @Test
    public void testPocetPredmetu() {
        // lékárnička je prázdná
        assertEquals(0, lekarnicka.pocetPredmetu());
        
        // vložení jednoho předmětu
        lekarnicka.vlozPredmet(predmet1);
        assertEquals(1, lekarnicka.pocetPredmetu());
        
        // vložení druhého předmětu
        lekarnicka.vlozPredmet(predmet2);
        assertEquals(2, lekarnicka.pocetPredmetu());
        
        
        // vložení třetího předmětu - předmět se nevloží
        lekarnicka.vlozPredmet(predmet3);
        assertEquals(2, lekarnicka.pocetPredmetu()); 
        
        // vyjmutí předmětů
        lekarnicka.vyndejPredmet(NAZEV_1);
        assertEquals(1, lekarnicka.pocetPredmetu());
        lekarnicka.vyndejPredmet(NAZEV_2);
        assertEquals(0, lekarnicka.pocetPredmetu());
    }

    /**
     * Testovací metoda metody vlozPredmetu třídy Lekarnicka.
     */
    @Test
    public void testVlozPredmet() {
        // lékárnička je prázdná
        assertEquals(0, lekarnicka.pocetPredmetu());
        
        // vložení prvního předmětu
        boolean vysledek = lekarnicka.vlozPredmet(predmet1);
        assertTrue(vysledek);
        assertEquals(1, lekarnicka.pocetPredmetu());
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_2));

        // vložení druhého předmětu
        boolean vysledek1 = lekarnicka.vlozPredmet(predmet2);
        assertTrue(vysledek1);
        assertEquals(2, lekarnicka.pocetPredmetu());
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));      
        
        // nelze vložit další předmět
        boolean vysledek2 = lekarnicka.vlozPredmet(predmet3);
        
        assertFalse(vysledek2);
        assertEquals(2, lekarnicka.pocetPredmetu());
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2)); 
    }

    /**
     * Testovací metoda metody vyndejPredmetu třídy Lekarnicka.
     */
    @Test
    public void testVyndejPredmet() {
        lekarnicka.vlozPredmet(predmet1);
        lekarnicka.vlozPredmet(predmet2);
        assertEquals(2, lekarnicka.pocetPredmetu());
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));
        
        // vyndání neexistujícího předmět
        lekarnicka.vyndejPredmet(NEPODSTATNY_TEXT);
        assertEquals(2, lekarnicka.pocetPredmetu());
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));       
        
        
        lekarnicka.vyndejPredmet(NAZEV_1);
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));

        lekarnicka.vyndejPredmet(NAZEV_2);
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_2));

    }

    /**
     * Testovací metoda metody obsahLekarnicky třídy Lekarnicka.
     */
    @Test
    public void testObsahLekarnicky() {
        List<Predmet> prazdna = lekarnicka.obsahLekarnicky();
        assertEquals(0, prazdna.size());
        
        lekarnicka.vlozPredmet(predmet1);
        List<Predmet> jedenPredmet = lekarnicka.obsahLekarnicky();
        assertEquals(1, jedenPredmet.size());
        assertTrue(jedenPredmet.contains(predmet1));
        
        lekarnicka.vlozPredmet(predmet2);
        List<Predmet> dvaPredmety = lekarnicka.obsahLekarnicky();
        assertEquals(2, dvaPredmety.size());
        assertTrue(dvaPredmety.contains(predmet1));        
        assertTrue(dvaPredmety.contains(predmet2));        
    }

    /**
     * Testovací metoda metody obsahujePredmet třídy Lekarnicka.
     */
    @Test
    public void testObsahujePredmet() {
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_2));

        lekarnicka.vlozPredmet(predmet1);
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_2));

        lekarnicka.vlozPredmet(predmet2);
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));
        
        // vložení předmětu znovu
        lekarnicka.vlozPredmet(predmet2);
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));
        
        lekarnicka.vyndejPredmet(NAZEV_1);
        assertFalse(lekarnicka.obsahujePredmet(NAZEV_1));       
        assertTrue(lekarnicka.obsahujePredmet(NAZEV_2));
        
    }
    
    /**
     * Testovací metoda metody najdiPredmet třídy Lekarnicka.
     */
    @Test
    public void testNajdiPredmet() {
        // po inicializaci prazdna
        assertNull(lekarnicka.najdiPredmet(NAZEV_1));       
        assertNull(lekarnicka.najdiPredmet(NAZEV_2));

        lekarnicka.vlozPredmet(predmet1);
        assertEquals(predmet1, lekarnicka.najdiPredmet(NAZEV_1));       
        assertNull(lekarnicka.najdiPredmet(NAZEV_2));

        lekarnicka.vlozPredmet(predmet2);
        lekarnicka.vlozPredmet(predmet2);
        assertEquals(predmet1, lekarnicka.najdiPredmet(NAZEV_1));      
        assertEquals(predmet2, lekarnicka.najdiPredmet(NAZEV_2));
        
        lekarnicka.vyndejPredmet(NAZEV_1);
        assertNull(lekarnicka.najdiPredmet(NAZEV_1));      
        assertEquals(predmet2, lekarnicka.najdiPredmet(NAZEV_2));

    }
}
