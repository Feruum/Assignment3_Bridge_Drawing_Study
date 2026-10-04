public class Circle extends Shape {
    private final int radius;

    public Circle(String id, int radius, Renderer implementation) {
        super(id, implementation);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return getImplementation().renderCircle(radius);
    }
}
