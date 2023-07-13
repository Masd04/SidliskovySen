package cz.vse.adventura.logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída Vec obsahující instance, jež představují předměty ve hře.
 *
 * @author David Mašek
 * @version pro školní rok 2020/2021
 */

public class Vec {

    private final String nazev;
    private boolean sebratelna;
    private int cena;
    private boolean viditelna;
    private double posLeft;
    private double posTop;

    /**
     * Konstruktor
     *
     * @param nazev - název věci
     * @param viditelna - viditelnost věci
     * @param sebratelna - sebratelnost věci
     * @param cena
     */

    public Vec(String nazev,boolean viditelna, boolean sebratelna , int cena, double posLeft, double posTop) {
        this.nazev = nazev;
        this.sebratelna = sebratelna;
        this.cena = cena;
        this.viditelna = viditelna;
        this.posLeft = posLeft;
        this.posTop = posTop;
    }

    /**
     * Getter
     *
     * @return nazev - název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Ověří sebratelnost věci (zda jde sebrat)
     *
     * @return sebratelna
     */

    public boolean isSebratelna() {
        return sebratelna;
    }

    /**
     * Getter
     *
     * @return cena
     */

    public int getCena() {
        return cena;
    }

    /**
     * Nastavuje viditelnost věci (zda ji hráč uvidí)
     *
     * @param hodnota
     */

    public void setViditelna(Boolean hodnota){
        viditelna = hodnota;
    }

    /**
     * Ověří viditelnost věci
     *
     * @return viditelna
     */

    public boolean getViditelna(){
     return viditelna;
    }

    public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }
}
