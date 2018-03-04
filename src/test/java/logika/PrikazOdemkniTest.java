/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazOdemkniTest slouží ke komplexnímu otestování třídy PrikazOdemkni 
 *
 * @author    Markéta Halíková
 * @version   LS 2016/17
 */
public class PrikazOdemkniTest{
    private static final String NAZEV_PRIKAZU = "odemkni";
    private static final String ODPOVED_NA_BEZ_PARAM = "Nevím co mám odemknout? Musíš zadat název lokace";
    private static final String ODP_NA_VIC_PARAM = "Nemůžeš odemknout víc věcí najednou";
    
    private static final String ODP_NA_NENI_SOUSED = "Odsud se do lokace telo nedá jít!";
    private static final String ODP_NA_NENI_KLIC = "Pro odemčení lokace krk potřebuješ mít u sebe lek1  a lek2";
    private static final String ODP_NA_ODEMCENO = "Podařilo se ti odemknout lokaci krk. Správné prášky tedy máš. Stačí jen vstoupit a máš vyhráno";
    private static final String ODP_NA_UZ_ODEMCENO = "Lokaci krk je už odemčená!!!";
    
    private static final String NAZEV_1 = "lek1";
    private static final String NAZEV_2 = "lek2";
    private static final String NAZEV_LOKACE = "krk";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";

    Predmet lek1;
    Predmet lek2;
    
    PrikazOdemkni prikazOdemkni;
    HerniPlan plan;

    @Before
    public void setUp(){
        plan = new HerniPlan();
        prikazOdemkni = new PrikazOdemkni(plan);
        lek1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        lek2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
    }

    /**
     * Testovací metoda metody Proved třídy PrikazOdemkni.
     */
    @Test
    public void testProved() {
       // bez parametru
       String result = prikazOdemkni.proved();
       assertEquals(ODPOVED_NA_BEZ_PARAM, result);
       
       // více parametrů
       String result2 = prikazOdemkni.proved(NEPODSTATNY_TEXT, NEPODSTATNY_TEXT);
       assertEquals(ODP_NA_VIC_PARAM, result2);
       
       Lokace krk = plan.getAktualniLokace().vratSousedniLokaci(NAZEV_LOKACE);
       krk.odemknout(false);
       krk.nastavKlice(lek1, lek2);
       assertTrue(krk.jeZamceno());
       
       // není sousední lokace
       String result3 = prikazOdemkni.proved("telo");
       assertEquals(ODP_NA_NENI_SOUSED, result3);
       
       // nemám klíče
       String result4 = prikazOdemkni.proved(NAZEV_LOKACE);
       assertEquals(ODP_NA_NENI_KLIC, result4);
       
       //vložení jednoho klíče
       plan.getLekarnicka().vlozPredmet(lek1);
       
       // nemám klíče
       String result5 = prikazOdemkni.proved(NAZEV_LOKACE);
       assertEquals(ODP_NA_NENI_KLIC, result5);
       
       //vložení druhého klíče
       plan.getLekarnicka().vlozPredmet(lek2);
       
       String result6 = prikazOdemkni.proved(NAZEV_LOKACE);
       assertEquals(ODP_NA_ODEMCENO, result6);
       
       assertFalse(krk.jeZamceno());
       
       String result7 = prikazOdemkni.proved(NAZEV_LOKACE);
       assertEquals(ODP_NA_UZ_ODEMCENO, result7);       
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazOdemkni.
     */
    @Test
    public void testGetZautoc() {
        assertEquals(NAZEV_PRIKAZU, prikazOdemkni.getNazev());
    }

    
}

