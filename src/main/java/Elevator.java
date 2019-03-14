public class Elevator {

    private static final double Kf_default = 0.1;

    // Height units in inches
    public static final double MINIMUM_HEIGHT = 0.0;
    public static final double MAXIMUM_HEIGHT = 40.0; // Start with 40 inches, increase from there
    public static final double STARTING_HEIGHT = 0.0;

    /**
     * The sprocketScaleFactor accounts for how much the output of the gearbox must
     * spin to power the rotation of the elevator spool itself. If the gear at the
     * output of the gearbox has 15 teeth, and the gear connected via chain has 32
     * teeth, then the scale factor is 15/32.
     */
    private static final double sprocketScaleFactor = (15.0 / 32.0);
    private static final double spoolDiameter = 1.25; // Inches!
    private static final double spoolCircumference = Math.PI * spoolDiameter; // Inches in 1 revolution
    private static final double countsPerRevolution = 4096;
    private static final double inchesPerEncoderCount = (spoolCircumference / countsPerRevolution) * sprocketScaleFactor;

    private TalonAndController talonAndController;

    static double toInches(double counts) {
        return counts * inchesPerEncoderCount;
    }

    static double toCounts(double inches) {
        return inches / inchesPerEncoderCount;
    }

    Elevator() {
        talonAndController = new TalonAndController();
    }

    public void moveSetpoint(double speed) {
        double height = getSetpointHeight();
        height += speed;
        setHeight(height);
    }

    public void setHeight(double inches) {
        inches = Utility.clamp(inches, MINIMUM_HEIGHT, MAXIMUM_HEIGHT);
        double counts = toCounts(inches - STARTING_HEIGHT); // Measures the difference from the starting height
        talonAndController.setSetpoint(counts);
    }

    public double getHeight() {
        double quadraturePosition = talonAndController.getQuadraturePosition();
        return STARTING_HEIGHT + toInches(quadraturePosition);
    }

    public double getSetpointHeight() {
        double counts = talonAndController.getSetpoint();
        return STARTING_HEIGHT + toInches(counts);
    }

    double calculateFeedForward() {
        return getF();
    }

    private double getF() {
        return Kf_default;
    }

}
