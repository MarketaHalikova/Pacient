/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazZautocTest slouží ke komplexnímu otestování třídy PrikazZautoc
 *
 * @author    Markéta Halíková
 * @version   LS 2016/17
 */
public class PrikazZautocTest{
    private static final String NAZEV_PRIKAZU = "zautoc";
    private static final String ODPOVED_NA_BEZ_PARAM = "Neřekl/a si na co chceš zaútočit.";
    private static final String ODP_NA_VIC_PARAM = "Nemůžeš zaútočit na víc věcí najednou.";
    private static final String ODP_NA_NENI_NEPRITEL = "lek1 není tvůj nepřítel, v této místnosti žádného nepřítele nemáš, takže klid. ";
    private static final String ODP_NA_PREDMET_NEPR = "lek1 není tvůj nepřítel, proč na to útočíš?!? ";
    private static final String ODP_NA_ZADNY_LEK = "Nepřítel tě zabil, což znamená konec hry. Ale můžeš si za to sám/a. Zaútočil/a jsi na něj bez potřebných léků.";
    private static final String ODP_NA_JEDEN_LEK = "Ale néé!! Nepřítel tě zabil, hra skončila. V lékárničce si měl/a jen jeden z potřebných léků pro jeho zabití. Zkus to znovu!";
    private static final String ODP_NA_OBA_LEKY = "HURÁÁÁ!!! Zabil/a si nepřítele, nyní si můžeš vzít předmět, který hlídal";
    
    private static final String NAZEV_1 = "lek1";
    private static final String NAZEV_2 = "lek2";
    private static final String NAZEV_3 = "nepritel";
    private static final String NAZEV_NEPRITELE = "nepritel";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";
    
    Nepritel nepritel;
    Predmet lek1;
    Predmet lek2;
    Predmet paralen2;
    
    PrikazZautoc prikazZautoc;
    HerniPlan plan;
    private Hra hra;
    
    @Before
    public void setUp(){
        plan = new HerniPlan();
        hra = new Hra();
        prikazZautoc = new PrikazZautoc(plan, hra);
        lek1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        lek2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
        paralen2 = new Predmet("paralen2", NEPODSTATNY_TEXT, false);
    }

    /**
     * Testovací metoda metody Proved třídy PrikazZautoc.
     */
    @Test
    public void testProved() {
       assertFalse(hra.konecHry());
        // paralen2 je potřeba v metodě proveď - nastaví se jako přenositelný
       plan.getAktualniLokace().vlozPredmet(paralen2);
       
       // bez parametru
       String result = prikazZautoc.proved();
       assertEquals(ODPOVED_NA_BEZ_PARAM, result);
       
       // více parametrů
       String result2 = prikazZautoc.proved(NEPODSTATNY_TEXT, NEPODSTATNY_TEXT);
       assertEquals(ODP_NA_VIC_PARAM, result2);
       
       // předmět není nepřítel
       String result3 = prikazZautoc.proved(NAZEV_1);
       assertEquals(ODP_NA_NENI_NEPRITEL, result3);
       
       // vložení nepřítele
       nepritel = new Nepritel(NAZEV_NEPRITELE, NEPODSTATNY_TEXT, true, NAZEV_1, NAZEV_2);
       plan.getAktualniLokace().vlozNepritele(nepritel);
       
       
       // utok na předmět, kdy je v místnosti nepřítel
       String result4 = prikazZautoc.proved(NAZEV_1);
       assertEquals(ODP_NA_PREDMET_NEPR, result4);
       
       // utok bez jedineho leku
       String result5 = prikazZautoc.proved(NAZEV_3);
       assertEquals(ODP_NA_ZADNY_LEK, result5);

       // vložení leku1 do lékárničky
       plan.getLekarnicka().vlozPredmet(lek1);

       String result6 = prikazZautoc.proved(NAZEV_3);
       assertEquals(ODP_NA_JEDEN_LEK, result6);
       
       // vložení leku2 do lékárničky
       plan.getLekarnicka().vlozPredmet(lek2);
       
       String result7 = prikazZautoc.proved(NAZEV_3);
       assertEquals(ODP_NA_OBA_LEKY, result7);
       
       assertTrue(plan.getAktualniLokace().najdiPredmet("paralen2").isPrenositelny());  
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazZautoc.
     */
    @Test
    public void testGetZautoc() {
        assertEquals(NAZEV_PRIKAZU, prikazZautoc.getNazev());
    }

    
}
