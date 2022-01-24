package Observer;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<Observer> observers = new ArrayList<Observer>();
    private ArrayList<ArrayList<String>> data = null;

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
        notifyAllObservers();
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for(Observer observer : observers)
            observer.update();
    }
}
