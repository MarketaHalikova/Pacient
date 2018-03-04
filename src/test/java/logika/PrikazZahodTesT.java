/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazZahodTest slouží ke komplexnímu otestování třídy PrikazZahod 
 *
 * @author    Markéta Halíková
 * @version   LS 2016017
 */
public class PrikazZahodTesT{
    private static final String NAZEV_PRIKAZU = "zahod";
    private static final String ODPOVED_NA_BEZ_PARAM = "Nevím, co mám zahodit";
    private static final String ODP_NA_VIC_PARAM = "Tomu nerozumím, nedokážu zahodit více věcí najednou";
    private static final String ODP_NA_NENI_V_LEKAR = "Předmět predmet1 v lékárničce nemáš";
    private static final String ODP_NA_ZAHOZENI = "Zahodil(a) si předmět predmet1";
    
    private static final String NAZEV_1 = "predmet1";
    private static final String NAZEV_2 = "predmet2";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";
    
    Predmet predmet1;
    Predmet predmet2;

    PrikazZahod prikazZahod;
    HerniPlan plan;
    
    @Before
    public void setUp()
    {
        plan = new HerniPlan();
        prikazZahod = new PrikazZahod(plan);
        
        predmet1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        predmet2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
    }
    /**
     * Testovací metoda metody Proved třídy PrikazZahod.
     */
    @Test
    public void testProved() {
       // bez parametru
       String result = prikazZahod.proved();
       assertEquals(ODPOVED_NA_BEZ_PARAM, result);
       
       // více parametrů
       String result2 = prikazZahod.proved(NEPODSTATNY_TEXT, NEPODSTATNY_TEXT);
       assertEquals(ODP_NA_VIC_PARAM, result2);
       
       // předmět není v lékárničce
       String result3 = prikazZahod.proved(NAZEV_1);
       assertEquals(ODP_NA_NENI_V_LEKAR, result3);
       
       // vložení předmětů
       plan.getLekarnicka().vlozPredmet(predmet1);
       plan.getLekarnicka().vlozPredmet(predmet2);
       
       // zahození předmětů
       String result4 = prikazZahod.proved(NAZEV_1);
       assertEquals(ODP_NA_ZAHOZENI, result4);

       assertTrue(plan.getLekarnicka().obsahujePredmet(NAZEV_2));
       assertTrue(plan.getAktualniLokace().obsahujePredmet(NAZEV_1));
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazVezmi.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazZahod.getNazev());
    }


}
