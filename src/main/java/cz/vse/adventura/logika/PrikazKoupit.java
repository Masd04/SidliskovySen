package cz.vse.adventura.logika;

/**
 * Třída PrikazKoupit - implementace příkazu "koupit" pro hru
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class PrikazKoupit implements IPrikaz {


    private static final String NAZEV = "koupit";
    private HerniPlan herniPlan;
    private Igelitka igelitka;
    private String nazevProstoru = "zastavarna";

    /**
     *  Konstruktor
     *
     * @param herniPlan
     */

    public PrikazKoupit(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.igelitka = igelitka;
    }

    /**
     *  Metoda provádí příkaz "koupit".
     *  Validuje uživatelský vstup (vypisuje hlášky v závislosti na správnosti napsání příkazu, parametru).
     *  správně napsaný příkaz, možnost nákupu pouze v zastavárně, stav hráčových peněz
     *  Při splnění podmínek hlásí výhru a odemyká tajný item.
     *
     * @return validační hlášky
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Jak mám vědět, co mám koupit?? Musíš zadat název věci!";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Igelitka igelitka = aktualniProstor.getVeciKProdeji();

        if (!herniPlan.getAktualniProstor().getNazev().equals("zastavarna")) {
            return "Věci můžeš koupit pouze v zastavárně.";
        }
        if (!igelitka.maVec(nazevVeci)) {
         return "Tuhle věc tu nemám.";

        }
        if (igelitka.prohledejIgelitku(nazevVeci).getCena() > herniPlan.getPenezenka()) {
            return "Nemáš dostatek peněz.";
        }
        herniPlan.getIgelitka().pridejVec(igelitka.odhodVec(nazevVeci));

        if (nazevVeci.equals("rolexky")) {
            Hra.getSingleton().setGameWon(true);
            System.out.println("╒══════════════════════════════════════════════════════╕" +"\n" + "\n"
                    + "                                                        " + "\n" +
                    "                                           VYHRÁL JSI!!!                     "  + "\n" +
                    "                         Gratuluji! Na mapě se objevil bonusový item.      " + "\n" +
                    "\n"  +    "                                                        " + "\n" +
                    "\n"  +    "╘══════════════════════════════════════════════════════╛");
            herniPlan.setJointViditelna(true);
        }
        return "Zakoupeno!";


    }

    /**
     *  Getter
     *
     * @return NAZEV - vrací název příkazu
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}


















