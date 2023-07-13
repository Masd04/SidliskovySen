package cz.vse.adventura.util;

public interface SubjectOfChange {

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    void notifyObservers();
}
