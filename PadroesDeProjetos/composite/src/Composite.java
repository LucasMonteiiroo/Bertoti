import java.util.ArrayList;
import java.util.List;

// Composite class
public class Composite implements Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Composite operation:");
        for (Component component : components) {
            component.operation();
        }
    }
}