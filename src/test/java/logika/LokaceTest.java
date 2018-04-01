package logika;

import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování třídy Lokace
 *
 * @author     Jarmila Pavlickova, Jan Riha, Markéta Halíková
 * @version    LS 2016/2017
 */
public class LokaceTest{

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Lokace lokace1 = new Lokace("hala", "vstupní hala budovy VŠE na Jižním městě", 0.0, 0.0);
        Lokace lokace2 = new Lokace("bufet", "bufet, kam si můžete zajít na svačinku", 0.0, 0.0);
        lokace1.setVychod(lokace2);
        lokace2.setVychod(lokace1);
        assertEquals(lokace2, lokace1.vratSousedniLokaci("bufet"));
        assertNull(lokace2.vratSousedniLokaci("pokoj"));
    }
    
    @Test
    public void testVeci(){
        Lokace lokace1 = new Lokace(null, null, 0.0, 0.0);
        Predmet predmet1 = new Predmet("a", "popis a", true);
        Predmet predmet2 = new Predmet("b", "popis b", false);
        lokace1.vlozPredmet(predmet1);
        lokace1.vlozPredmet(predmet2);
        assertTrue(lokace1.obsahujePredmet("a"));
        assertSame(predmet1, lokace1.najdiPredmet("a"));
        assertSame(predmet1, lokace1.vezmiPredmet("a"));
        assertTrue(lokace1.obsahujePredmet("b"));
        assertSame(predmet2, lokace1.najdiPredmet("b"));
        assertSame(predmet2, lokace1.vezmiPredmet("b"));
        assertFalse(lokace1.obsahujePredmet("c"));
        assertNull(lokace1.najdiPredmet("c"));
        assertNull(lokace1.vezmiPredmet("c"));
    }

}
