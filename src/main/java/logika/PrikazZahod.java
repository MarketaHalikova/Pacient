package logika;


/**
 * Třída implementuje příkaz zahod pro zahození předmětu z lékárničky
 * 
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    
    private HerniPlan hPlan;
    
    public PrikazZahod(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    
    /**
     * metoda zjistí jestli je požadovaný předmět v lékářničce a
     * případně ho vyhodí a zároveň vloží do aktuální lokace
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry){        
        if (parametry.length < 1)
        {
            return "Nevím, co mám zahodit";
        }
        
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nedokážu zahodit více věcí najednou";
        }
        
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        Lekarnicka lekarnicka = hPlan.getLekarnicka();
        
        if (!lekarnicka.obsahujePredmet(nazevPredmetu))
        {
            return "Předmět " + nazevPredmetu + " v lékárničce nemáš";
        }
        
        Predmet vyndanyPredmet = lekarnicka.vyndejPredmet(nazevPredmetu);
        aktLokace.vlozPredmet(vyndanyPredmet);
        
        return "Zahodil(a) si předmět " + nazevPredmetu;
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    public String getNazev(){
        return NAZEV;
    }
    
}
