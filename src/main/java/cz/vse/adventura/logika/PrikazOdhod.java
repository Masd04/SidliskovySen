package cz.vse.adventura.logika;

/**
 *  Třída PrikazOdhod - implementace příkazu "odhod" pro hru
 *
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class PrikazOdhod implements IPrikaz{

    private static final String NAZEV = "odhod";
    private HerniPlan herniplan;

    /**
     * Konstruktor
     *
     * @param herniPlan
     */

    public PrikazOdhod(HerniPlan herniPlan) {
        this.herniplan = herniPlan;
    }

    /**
     *  Provádí příkaz "odhod". Validuje uživateský vstup (správné zadání parametru, dostupnost věci v igelitce)
     *
     * @return validační hlášky, hlášku odhození věci a její název
     *
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím, co mám odhodit.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniplan.getAktualniProstor();
        Igelitka igelitka = herniplan.getIgelitka();
        if (igelitka.maVec(nazevVeci)) {
            Vec vec = igelitka.odhodVec(nazevVeci);
            aktualniProstor.addVec(vec);
            return "Odhodil jsi " + nazevVeci;
        } else {
            return "Tuhle věc v igelitce nemáš!";
        }
    }

    /**
     *  Getter
     *
     * @return NAZEV (příkazu)
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
