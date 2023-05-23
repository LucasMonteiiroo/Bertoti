public class test {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf("Leaf 1");
        Leaf leaf2 = new Leaf("Leaf 2");
        Leaf leaf3 = new Leaf("Leaf 3");

        Composite composite1 = new Composite();
        composite1.addComponent(leaf1);
        composite1.addComponent(leaf2);

        Composite composite2 = new Composite();
        composite2.addComponent(leaf3);

        Composite composite3 = new Composite();
        composite3.addComponent(composite1);
        composite3.addComponent(composite2);

        composite3.operation();
    }
}