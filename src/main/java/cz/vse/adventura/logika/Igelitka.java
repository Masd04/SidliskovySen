package cz.vse.adventura.logika;

import cz.vse.adventura.util.Observer;
import cz.vse.adventura.util.SubjectOfChange;
import java.util.*;

/** Třída Igelitka - představuje igelitku(hráčův inventář), do které ukládáme věci příkazem "seber".
 *  maxVelikost udává kapacitu igelitky
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */


public class Igelitka implements SubjectOfChange {

    public final static int maxVelikost = 3;
    public ArrayList<Vec> igelitka;


    private Set<Observer> observers = new HashSet<>();
    /**
     *  Konstruktor zakládající novou igelitku
     *
     */

    public Igelitka() {
        igelitka = new ArrayList<Vec>();
  //      obsahIgelitky = new HashMap<String, Vec>();
    }

    /**
     *  Metoda určí, zda byla naplněna kapacita igelitky
     *
     * @return true = igelitka ještě není plná
     *         false = igelitka je plná
     */

    public boolean volneMisto() {
        if (igelitka.size() < maxVelikost) {
            return true;
        }
        return false;
    }

    /**
     *  Metoda přidává věci do igelitky, pokud se tam vejdou
     *
     * @param pridana = věc, kterou přidáváme do batohu
     */

    public void pridejVec(Vec pridana) {
        if (volneMisto()) {
            igelitka.add(pridana);
        }
        notifyObservers();
        }

        /**
         *   Vrací oshah igelitky
         *
         * @return věci v igelitce, pokud tam nějaké jsou (pokud ne, vrátí prázdnou igelitku)
         */

        public String otevriIgelitku () {
            String a = " ";
            for (Vec vec : igelitka) {
                a += "  " + vec.getNazev();
            }
            if (igelitka.size() == 0) {
                return " " ;
            }
            return a;
        }

    /**
     *  Vyhazuje věc z igelitky
     *
     * @param nazevVeci = odhazovaná věc
     */

        public Vec odhodVec(String nazevVeci) {
        Vec zIgelitky = null;
        for (Vec vec : igelitka) {
            if (vec.getNazev().equals(nazevVeci)) {
                zIgelitky = vec;
            }
        }
        if (zIgelitky != null) {
            igelitka.remove(zIgelitky);
        }
        notifyObservers();
        return zIgelitky;
        }

    /**
     * Metoda prohledává igelitku
     *
     * @return zIgelitky - vrací obsah igelitky
     */

    public Vec prohledejIgelitku(String nazevVeci) {
        Vec zIgelitky = null;
        for (Vec vec : igelitka) {
            if (vec.getNazev().equals(nazevVeci)) {
                zIgelitky = vec;
            }
        }

        return zIgelitky;
    }

    /**
     * Zjišťuje, zda se daná věc nachází v igelitce
     *
     * @param nazevVeci = název hledané věci
     * @return true = věc je v igelitce
     *         false = věc není v igelitce
     */

        public boolean maVec(String nazevVeci) {

        for (Vec vec : this.igelitka) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
        }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Igelitka igelitka1 = (Igelitka) o;
        return Objects.equals(igelitka, igelitka1.igelitka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(igelitka);
    }


    @Override
    public void registerObserver(Observer observer) {
    this.observers.add(observer);
    }

    @Override
    public  void unregisterObserver(Observer observer) {
    this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
    for(Observer observer : observers) {
        observer.update();
        }
    }

    public ArrayList<Vec> getMnozinaVeci() {
    return igelitka;
    }

}




