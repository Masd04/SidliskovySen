package cz.vse.adventura.logika;

/**
 *  Třída PrikazSeber - implementace příkazu "seber" pro hru
 *
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor
     *
     * @param herniPlan
     */

    public PrikazSeber(HerniPlan herniPlan) {

        this.herniPlan = herniPlan;

    }

    /**
     *  Metoda se pokouší sebrat věc. Na základě správnosti zadaného příkazu, přítomnosti věci v prostoru a sebratlnosti.
     *  Pokud věc lze sebrat, umístí ji do igelitky (pokud je v ní volno)
     *
     * @return zpráva hráči o tom, zda bylo sebrání úspěšné
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím, co mám sebrat.";

        } else if (parametry.length != 1) {
            return "Víc věcí naráz nesebereš.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        Vec vec = aktualniProstor.vratVec(nazevVeci);

        if (vec == null) {
            return "Nic takového tady není. Nepřepsal ses?";
        } else if (!vec.isSebratelna()) {
            return "Tuhle věc nelze sebrat.";
        }

        Igelitka igelitka = herniPlan.getIgelitka();
        if (igelitka.volneMisto()) {
            igelitka.pridejVec(vec);
            aktualniProstor.removeVec(nazevVeci);
            return "Šup s tím do igelitky";
        } else { aktualniProstor.addVec(vec);
            return "Igelitka není bezedná!";

        }
    }

    /**
     * Getter
     *
     * @return NAZEV (název příkazu)
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
