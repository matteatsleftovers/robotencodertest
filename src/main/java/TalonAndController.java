class TalonAndController {

    private double quadraturePositionInCounts = 0.0;
    private double setpoint = 0.0;

    void setSetpoint(double counts) {
        this.setpoint = counts;
        this.quadraturePositionInCounts = counts; // Assumption: the quadrature position (quadraturePositionInCounts) will update as the setpoint does
    }

    double getQuadraturePosition() {
        return quadraturePositionInCounts;
    }

    double getSetpoint() {
        return setpoint;
    }
}
