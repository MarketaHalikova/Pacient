/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída PrikazVezmiTest slouží ke komplexnímu otestování třídy PrikazVezmi 
 *
 * @author    Markéta Halíková
 * @version   LS 2016/2017
 */
public class PrikazVezmiTest {
    private static final String NAZEV_PRIKAZU = "vezmi";
    private static final String ODPOVED_NA_BEZ_PARAM = "Nevim, co mam sebrat";
    private static final String ODP_NA_VIC_PARAM = "Tomu nerozumim, nedokazu sebrat vice veci najednou";
    private static final String ODP_NA_NENI_V_LOKACI = "Predmet predmet1 tady neni";
    private static final String ODP_NA_NEPRENOS = "predmet4 si vzit nemuzes, bez toho pacient neprezije!";
    private static final String ODP_NA_NENI_MISTO = "V lekarnicce uz nemas volne misto, muzes nest jen dve veci - musis neco zahodit";
    private static final String ODP_NA_SEBRANI = "Sebral(a) jsi predmet predmet1";
    private static final String ODP_NA_NEPRITEL = "predmet4 si vzit nemuzes, nejdřív musíš zneškodnit nepřítele!";
    
    private static final String NAZEV_1 = "predmet1";
    private static final String NAZEV_2 = "predmet2";
    private static final String NAZEV_3 = "predmet3";
    private static final String NAZEV_4 = "predmet4";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";

    Predmet predmet1;
    Predmet predmet2;
    Predmet predmet3;
    Predmet predmet4;
    
    Nepritel nepritel;
    
    PrikazVezmi prikazVezmi;
    HerniPlan plan;
    
    @Before
    public void setUp() {
        plan = new HerniPlan();
        prikazVezmi = new PrikazVezmi(plan);
        
        predmet1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        predmet2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
        predmet3 = new Predmet(NAZEV_3, NEPODSTATNY_TEXT);
        predmet4 = new Predmet(NAZEV_4, NEPODSTATNY_TEXT, false);
    }

    /**
     * Testovací metoda metody Proved třídy PrikazVezmi.
     */
    @Test
    public void testProved() {
       // bez parametru
       String result = prikazVezmi.proved();
       assertEquals(ODPOVED_NA_BEZ_PARAM, result);
       
       // více parametrů
       String result2 = prikazVezmi.proved(NEPODSTATNY_TEXT, NEPODSTATNY_TEXT);
       assertEquals(ODP_NA_VIC_PARAM, result2);
       
       // předmět není v lokaci
       String result3 = prikazVezmi.proved(NAZEV_1);
       assertEquals(ODP_NA_NENI_V_LOKACI, result3);
       
       // vložení předmětů
       plan.getAktualniLokace().vlozPredmet(predmet1);
       plan.getAktualniLokace().vlozPredmet(predmet2);
       plan.getAktualniLokace().vlozPredmet(predmet3);
       plan.getAktualniLokace().vlozPredmet(predmet4);
       
       // nepřenositelný předmět
       String result4 = prikazVezmi.proved(NAZEV_4);
       assertEquals(ODP_NA_NEPRENOS, result4);
       
       // sebrání předmětů
       String result5 = prikazVezmi.proved(NAZEV_1);
       assertEquals(ODP_NA_SEBRANI, result5);
       prikazVezmi.proved(NAZEV_2);
       
       // není místo v lékárničce
       String result6 = prikazVezmi.proved(NAZEV_3);
       assertEquals(ODP_NA_NENI_MISTO, result6);
       
       // nepřítel hlídá předmět
       nepritel = new Nepritel("nepritel", "popis", true, "lek", "lek");
       plan.getAktualniLokace().vlozNepritele(nepritel);
       String result7 = prikazVezmi.proved(NAZEV_4);
       assertEquals(ODP_NA_NEPRITEL, result7);
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazVezmi.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazVezmi.getNazev());
    }

}
