public class ObserverPatternExample {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserverA observerA = new ConcreteObserverA();
        ConcreteObserverB observerB = new ConcreteObserverB();

        subject.attach(observerA);
        subject.attach(observerB);

        subject.setValue(10);

        subject.detach(observerB);

        subject.setValue(20);
    }
}x'