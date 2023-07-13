package cz.vse.adventura.logika;

/**
 *  Třída PrikazIgelitka - implementace příkazu "igelitka" pro hru
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class PrikazIgelitka implements IPrikaz {

    private static final String NAZEV = "igelitka";
    private HerniPlan herniPlan;

    /**
     *  Konstruktor
     *
     * @param herniPlan
     */

    public PrikazIgelitka (HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "igelitka. Do rámečku vypisuje obsah igelitky a peníze v hráčově vlastnictví.
     *
     * @return vypíše hráči rámeček s obsahem igelitky
     */

    @Override
    public String provedPrikaz(String... parametry) {
        Igelitka igelitka = herniPlan.getIgelitka();
        return  "╔═════════════════▣◎▣═══════════════════╗" + "\n" +
                   igelitka.otevriIgelitku() + "  " + " PENĚZ:" + herniPlan.getPenezenka() +  "\n" +
                "╚═════════════════▣◎▣═══════════════════╝";

    }

    /**
     *  Getter - vrací název příkazu
     *
     * @return NAZEV (název příkazu)
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
