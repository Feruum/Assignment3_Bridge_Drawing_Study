public class Main {
    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }
        runDemo();
        if (passed != total) {
            System.exit(1);
        }
    }

    private static void runDemo() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Circle circleVector = new Circle("circle-1", 2, vector);
        Circle circleRaster = new Circle("circle-2", 2, raster);
        Square squareVector = new Square("square-1", 3, vector);
        Square squareRaster = new Square("square-2", 3, raster);

        checkResult("T1", "Circle + VectorRenderer", circleVector.execute(), "VECTOR circle radius=2");
        checkResult("T2", "Circle + RasterRenderer", circleRaster.execute(), "RASTER circle radius=2");
        checkResult("T3", "Square + VectorRenderer", squareVector.execute(), "VECTOR square side=3");
        checkResult("T4", "Square + RasterRenderer", squareRaster.execute(), "RASTER square side=3");
        checkRuntimeSwitch();

        Renderer ascii = new AsciiRenderer();
        Circle circleAscii = new Circle("circle-3", 2, ascii);
        Square squareAscii = new Square("square-3", 3, ascii);
        checkResult("T6", "Circle + AsciiRenderer", circleAscii.execute(), "ASCII circle radius=2");
        checkResult("T7", "Square + AsciiRenderer", squareAscii.execute(), "ASCII square side=3");

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static void checkRuntimeSwitch() {
        Circle original = new Circle("circle-switch", 2, new VectorRenderer());
        Circle current = original;
        String idBefore = original.getId();
        int radiusBefore = original.getRadius();
        String before = current.execute();

        current.setImplementation(new RasterRenderer());
        String after = current.execute();
        boolean sameObject = original == current;
        boolean stateUnchanged = idBefore.equals(current.getId())
                && radiusBefore == current.getRadius();
        String expectedBefore = "VECTOR circle radius=2";
        String expectedAfter = "RASTER circle radius=2";
        boolean success = sameObject && stateUnchanged
                && expectedBefore.equals(before) && expectedAfter.equals(after);

        printCheck("T5", success, "Circle + VectorRenderer -> RasterRenderer"
                + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged
                + " | id=" + idBefore + " -> " + current.getId()
                + " | radius=" + radiusBefore + " -> " + current.getRadius());
        System.out.println("  before=" + before + " | after=" + after);
        if (!success) {
            System.out.println("  expected: sameObject=true | stateUnchanged=true"
                    + " | id=" + idBefore + " | radius=" + radiusBefore
                    + " | before=" + expectedBefore + " | after=" + expectedAfter);
        }
    }

    private static void checkResult(String testId, String classes, String actual, String expected) {
        boolean success = expected.equals(actual);
        printCheck(testId, success, classes + " | result=" + actual);
        if (!success) {
            System.out.println("  expected=" + expected);
        }
    }

    private static void printCheck(String testId, boolean success, String details) {
        total++;
        if (success) {
            passed++;
        }
        String status = success ? "PASS" : "FAIL";
        System.out.println(testId + " " + status + " | " + details);
    }
}
