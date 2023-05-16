import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {
    private int value;
    private List<Observer> observers = new ArrayList<>();

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(value);
        }
    }
}