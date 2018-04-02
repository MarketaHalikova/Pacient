/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída PrikazJdiTest slouží k testování metod třídy PrikazJdi
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazJdiTest {
    private static final String NAZEV_PRIKAZU = "jdi";
    private static final String ODP_NA_CHYBNE_ZADANI = "Kam mám jít? Musíš zadat jméno východu";
    private static final String ODP_NA_CHYBNY_CIL = "Tam se odsud jít nedá!";
    private static final String ODP_PRECHOD_DO_KRKU = "Jsi v místnosti/lokaci krk pacienta.\nvýchody: telo pusa hlava(zamknuto)\npředměty:";
    private static final String KRK_NAZEV = "krk";
    private static final String PUSA_NAZEV = "pusa";

    PrikazJdi prikazJdi;
    HerniPlan plan;
    
    @Before
    public void setUp() {
        plan = new HerniPlan();
        prikazJdi = new PrikazJdi(plan);
    }

    /**
     * Testovací metoda metody Proved třídy PrikazJdi.
     */
    @Test
    public void testProved() {
       // aktualni lokace je pusa
       assertEquals(PUSA_NAZEV, plan.getAktualniLokace().getNazev());

       String result = prikazJdi.proved();
       assertEquals(ODP_NA_CHYBNE_ZADANI, result);
       // aktualni lokace se nezmenila
       assertEquals(PUSA_NAZEV, plan.getAktualniLokace().getNazev());
       
       // z pusy nelze jit do hlavy
       result = prikazJdi.proved("hlava");
       assertEquals(ODP_NA_CHYBNY_CIL, result);
       // aktualni lokace se nezmenila
       assertEquals(PUSA_NAZEV, plan.getAktualniLokace().getNazev());
       
       // lokace neexistuje
       result = prikazJdi.proved("blabla");
       assertEquals(ODP_NA_CHYBNY_CIL, result);
       // aktualni lokace se nezmenila
       assertEquals(PUSA_NAZEV, plan.getAktualniLokace().getNazev());
      
       // prechod do krku
       result = prikazJdi.proved("krk");
       assertEquals(ODP_PRECHOD_DO_KRKU, result);
       // aktualni lokace je krk
       assertEquals(KRK_NAZEV, plan.getAktualniLokace().getNazev());
    }

    /**
     * Testovací metoda metody getNázev třídy PrikazJdi.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazJdi.getNazev());
    }

}
