/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.List;
import java.util.ArrayList;

/**
 * Instance třídy Lekarnicka představují lákárničku, kam si hráč
 * ukládá sebrané předměty
 *
 * @author    Markéta Halíková
 * @version   LS 2016/17
 */
public class Lekarnicka{
    private List<Predmet> predmety;
    private int pocetPredmetu = 0;
    private static final int KAPACITA_LEKARNICKY = 2;
    
    /**
     *  Vytvoření lékárničky v podobě listu předmětů
     */
    public Lekarnicka(){
        predmety = new ArrayList<>();
    }
    
    /**
     * Zjistí kolik předmětů je v lékárničce.
     * 
     * @return počet předmětů   
     */
    public int pocetPredmetu(){
        
        return pocetPredmetu;
    }
    
    /**
     * Přidá předmět do lékárničky.
     * 
     * @param    prřdmet    který má být přidán do lékárničky
     * @return   boolean    true pokud byl předmět přidán
     */
    public boolean vlozPredmet(Predmet predmet){
        
        if (pocetPredmetu()>= KAPACITA_LEKARNICKY){
            return false;
        }
        predmety.add(predmet);
        pocetPredmetu ++;
        return true;
    }
    
     /**
     * Vyndá předmět z lékárničky.
     * 
     * @param    název předmětu, který má být odebrán z lékárničky
     * @return   předmět, který byl odebrán (v případě, že lékárničky předmět neobsahuje vrací null)
     */
    public Predmet vyndejPredmet(String predmet){
        Predmet result = null;
        for (Predmet p : predmety){
            if(p.getNazev().equals(predmet)){
                predmety.remove(p);
                pocetPredmetu --;
                result = p;
                break;
            }
            
        }
        return result;
    }
    
     /**
     * Vypíše obsah lékárničky.
     * 
     * @return    list předmětů z lékárničky
     */
    public List<Predmet> obsahLekarnicky(){
        return predmety;
    }
    
     /**
     * Zjistí, zda lékárnička obsahuje předmět
     * 
     * @param    název předmětu, který hledáme
     * @return    boolean zda byl předmět nalezen
     */
    public boolean obsahujePredmet(String testovanyPredmet){
        boolean obsahuje = false;
        for(Predmet p : predmety) {
            if(p.getNazev().equals(testovanyPredmet)){
                obsahuje = true;
                break;
            } 
        }
        return obsahuje;
    }
    
    /**
     * Najde v lokaci předmět s daným názvem a vrátí ho. Pokud v lokaci předmět s daným
     * názvem není, vrátí null.
     * 
     * @param    název předmětu, který hledáme
     * @returns    předmět který byl nalezen, jinak null
     */
    public Predmet najdiPredmet(String nazevPredmetu){
        Predmet nalezeny = null;
        for(Predmet p : predmety) {
            if(p.getNazev().equals(nazevPredmetu)){
                nalezeny = p;
                break;
            } 
        }
        return nalezeny;
    }
}
