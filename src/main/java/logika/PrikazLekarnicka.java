package logika;


/**
 * Třída implementuje příkaz lekarnicka pro vrácení obsahu lékarničky
 * 
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazLekarnicka implements IPrikaz{
    private static final String NAZEV = "lekarnicka";
    
    private HerniPlan hPlan;
    
    /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */  
    public PrikazLekarnicka(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda vrátí v jednom stringu predmety v lékárničce
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce - zde pouze název příkazu
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry){        
        if (parametry.length > 0)
        {
            return "Nevím, co po mě chceš";
        }
        
        Lekarnicka lekarnicka = hPlan.getLekarnicka();
        
        StringBuilder builder = new StringBuilder();
        for (Predmet p : lekarnicka.obsahLekarnicky()) {
            builder.append(p.getNazev()).append("\n");
        }
        return builder.toString();
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