class Arm {

    private static final double Kf_default = 0.1;

    private static final double MINIMUM_ANGLE = 85.0;
    private static final double MAXIMUM_ANGLE = 210.0;
    static final double STARTING_ANGLE = 85.0;

    /**
     * The sprocketScaleFactor accounts for how much the output of the gearbox must
     * spin to power the rotation of arm itself. If the gear at the output of the
     * gearbox has 15 teeth, and the gear connected via chain has 23 teeth, then the
     * scale factor is 15/23.
     */
    private static final double gearBoxScaleFactor = (15.0 / 23.0);
    private static final double degreesPerEncoderCount = (360.0 / 4096) * gearBoxScaleFactor;

    private TalonAndController talonAndController;

    static double toDegrees(double counts) {
        return counts * degreesPerEncoderCount;
    }

    static double toCounts(double degrees) {
        return degrees / degreesPerEncoderCount;
    }

    Arm() {
        talonAndController = new TalonAndController();
    }

    void moveSetpoint(double speed) {
        double ang = getSetpointDegrees();
        ang += speed;
        setAngle(ang);
    }

    private void setAngle(double angle) {
        angle = Utility.clamp(angle, MINIMUM_ANGLE, MAXIMUM_ANGLE);
        double counts = toCounts(STARTING_ANGLE - angle);
        talonAndController.setSetpoint(counts);
    }

    double getAngle() {
        double quadraturePosition = talonAndController.getQuadraturePosition();
        return STARTING_ANGLE - toDegrees(quadraturePosition);
    }

    double getSetpointDegrees() {
        double counts = talonAndController.getSetpoint();
        return STARTING_ANGLE - toDegrees(counts);
    }

    double calculateFeedForward() {
        return getF() * -Math.cos(Math.toRadians(getAngle()));
    }

    private double getF() {
        return Kf_default;
    }

}
