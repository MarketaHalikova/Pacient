/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazProzkoumejTest slouží ke komplexnímu otestování třídy PrikazProzkoumej 
 *
 * @author    Markéta Halíková
 * @version   LS 2016/17
 */
public class PrikazProzkoumejTest
{
    private static final String NAZEV_PRIKAZU = "prozkoumej";
    private static final String ODPOVED_NA_BEZ_PARAM = "Jsi v místnosti/lokaci pusa pacienta." + "\n" + "východy: krk" + "\n" + "předměty: jazyk";
    private static final String ODP_NA_VIC_PARAM = "Tomu nerozumím, nemůžeš prozkoumat více předmětů najednou";
    private static final String ODP_NA_NENI_NIKDE = "Předmět predmet1 není v lékárničce ani v aktuální lokaci, ani se nejedná o nepřítele";
    private static final String ODP_NA_JE = "nepodstatne";
    private static final String ODP_NA_NEPRITEL = "popis nepritele";
    
    private static final String NAZEV_1 = "predmet1";
    private static final String NAZEV_2 = "predmet2";
    private static final String NAZEV_3 = "predmet3";
    private static final String NEPODSTATNY_TEXT = "nepodstatne";
    
    Predmet predmet1;
    Predmet predmet2;
    Predmet predmet3;
    
    Nepritel nepritel;
    
    PrikazProzkoumej prikazProzkoumej;
    HerniPlan plan;

    @Before
    public void setUp(){
        plan = new HerniPlan();
        prikazProzkoumej = new PrikazProzkoumej(plan);
        
        predmet1 = new Predmet(NAZEV_1, NEPODSTATNY_TEXT);
        predmet2 = new Predmet(NAZEV_2, NEPODSTATNY_TEXT);
        predmet3 = new Predmet(NAZEV_3, NEPODSTATNY_TEXT, false);
        
    }
    
    /**
     * Testovací metoda metody Proved třídy PrikazProzkoumej.
     */
    @Test
    public void testProved() {
       // bez parametru
       String result = prikazProzkoumej.proved();
       assertEquals(ODPOVED_NA_BEZ_PARAM, result);
       
       // více parametrů
       String result2 = prikazProzkoumej.proved(NEPODSTATNY_TEXT, NEPODSTATNY_TEXT);
       assertEquals(ODP_NA_VIC_PARAM, result2);
       
       // předmět není v lokaci ani v lékárničce
       String result3 = prikazProzkoumej.proved(NAZEV_1);
       assertEquals(ODP_NA_NENI_NIKDE, result3);
       
       // vložení předmětů do lokace
       plan.getAktualniLokace().vlozPredmet(predmet2);
       
       // předmět v lokaci
       String result4 = prikazProzkoumej.proved(NAZEV_2);
       assertEquals(ODP_NA_JE, result4);
       
       // vložení předmětů do lékárničky
       plan.getLekarnicka().vlozPredmet(predmet3);
       
       // předmět v lékárničce
       String result5 = prikazProzkoumej.proved(NAZEV_3);
       assertEquals(ODP_NA_JE, result5);
       
       // nepřítel
       nepritel = new Nepritel("nepritel", "popis nepritele", true, "lek", "lek");
       plan.getAktualniLokace().vlozNepritele(nepritel);
       String result6 = prikazProzkoumej.proved("nepritel");
       assertEquals(ODP_NA_NEPRITEL, result6);
    }

    /**
     * Testovací metoda metody getNazev třídy PrikazVezmi.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV_PRIKAZU, prikazProzkoumej.getNazev());
    }


}
