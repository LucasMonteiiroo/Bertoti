class Singleton {
    private static Singleton instance;

    // Private constructor to prevent instantiation from outside the class
    private Singleton() {}
public static Singleton getInstance() {
        if (instance == null) {
        instance = new Singleton();
        }
        return instance;
        }

public void doSomething() {
        System.out.println("Singleton is doing something.");
        }
        }