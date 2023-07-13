package cz.vse.adventura.logika;



/**
 *  Třída NPC - obsahuje postavy ve hře
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class NPC {

    private String jmeno;
    private String proslov;

    /**
     *  Konstruktor - vytváří postavy
     *
     * @param jmeno = název postavy
     * @param proslov =  monolog postavy
     */

    public NPC (String jmeno, String proslov) {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    /**
     *  Getter - vrací jméno postavy
     * @return jmeno
     */

    public String getJmeno() {
        return jmeno;
    }

    /**
     *  Getter - vrací proslov postavy
     * @return proslov
     */

    public String getProslov() {
        return proslov;
    }



}
