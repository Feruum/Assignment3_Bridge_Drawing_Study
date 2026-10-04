import java.util.Objects;

public abstract class Shape {
    private final String id;
    private Renderer implementation;

    protected Shape(String id, Renderer implementation) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.implementation = Objects.requireNonNull(implementation, "Renderer cannot be null");
    }

    public String getId() {
        return id;
    }

    protected Renderer getImplementation() {
        return implementation;
    }

    public void setImplementation(Renderer implementation) {
        this.implementation = Objects.requireNonNull(implementation, "Renderer cannot be null");
    }

    public abstract String execute();
}
