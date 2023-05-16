class ConcreteObserverA implements Observer {
    @Override
    public void update(int value) {
        System.out.println("ConcreteObserverA: " + value);
    }
}
