public class ConcreteObserverB implements Observer {
    @Override
    public void update(int value) {
        System.out.println("ConcreteObserverB: " + value);
    }
}

