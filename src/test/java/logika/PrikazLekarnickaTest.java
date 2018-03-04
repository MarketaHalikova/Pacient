/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazLekarnickaTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Markéta Halíková
 * @version   LS 2016/17
 */
public class PrikazLekarnickaTest{
    private static final String NAZEV_PRIKAZU = "lekarnicka";
    private static final String NAZEV_1 = "predmet1";
    private static final String NAZEV_2 = "predmet2";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";
    private static final String VYSLEDEK = "predmet1" + "\n" + "predmet2"+ "\n";
    private static final String ODPOVED_NA_VIC_PARAM = "Nevím, co po mě chceš";
 
    Predmet predmet1;
    Predmet predmet2;
    
    PrikazLekarnicka prikazLekarnicka;
    HerniPlan plan;
    
    @Before
    public void setUp(){
        plan = new HerniPlan();
        prikazLekarnicka = new PrikazLekarnicka(plan);
        predmet1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        predmet2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
        
    }
    /**
     * Testovací metoda metody Proved třídy PrikazLekarnicka.
     */
    @Test
    public void testProved() {
       // více parametrů
       String result = prikazLekarnicka.proved(NEPODSTATNY_TEXT);
       assertEquals(ODPOVED_NA_VIC_PARAM, result);

       plan.getLekarnicka().vlozPredmet(predmet1);
       plan.getLekarnicka().vlozPredmet(predmet2);
       
       String result1 = prikazLekarnicka.proved();
       assertEquals(VYSLEDEK, result1);
       
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazLekarnicka.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazLekarnicka.getNazev());
    }

}
