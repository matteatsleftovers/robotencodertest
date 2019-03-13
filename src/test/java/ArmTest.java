import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmTest {

    private Arm subject;

    @Before
    public void setUp() {
        subject = new Arm();
    }

    @Test
    public void calculateFeedForward_to_90_degrees() {
        for (int i = 0; i < 5; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingAngle = subject.getAngle();
        assertEquals(90.0, resultingAngle, 0.01);

        double feedForward = subject.calculateFeedForward();
        assertEquals(0.0, feedForward, 0.01);
    }

    @Test
    public void calculateFeedForward_to_100_degrees() {
        for (int i = 0; i < 15; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingAngle = subject.getAngle();
        assertEquals(100.0, resultingAngle, 0.01);

        double feedForward = subject.calculateFeedForward();
        assertEquals(0.1 * -Math.cos(Math.toRadians(100.0)), feedForward, 0.01);
    }

    @Test
    public void calculateFeedForward_to_180_degrees() {
        for (int i = 0; i < 95; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingAngle = subject.getAngle();
        assertEquals(180.0, resultingAngle, 0.01);

        double feedForward = subject.calculateFeedForward();
        assertEquals(0.1, feedForward, 0.01);
    }

    @Test
    public void calculateFeedForward_to_210_degrees() {
        for (int i = 0; i < 125; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingAngle = subject.getAngle();
        assertEquals(210.0, resultingAngle, 0.01);

        double feedForward = subject.calculateFeedForward();
        assertEquals(0.1 * -Math.cos(Math.toRadians(210.0)), feedForward, 0.01);
    }

    @Test
    public void calculateFeedForward_from_210_to_180_degrees() {
        for (int i = 0; i < 125; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        for (int i = 0; i < 30; i++) {
            subject.moveSetpoint(-1.0); // -1.0 represents full speed "upward" for this time interval
        }

        double resultingAngle = subject.getAngle();
        assertEquals(180.0, resultingAngle, 0.01);

        double feedForward = subject.calculateFeedForward();
        assertEquals(0.1, feedForward, 0.01);
    }

    @Test
    public void moveSetpoint_called_once() {
        subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval

        double resultingSetpointDegrees = subject.getSetpointDegrees();
        assertEquals(Arm.STARTING_ANGLE + 1.0, resultingSetpointDegrees, 0.01);
    }

    @Test
    public void moveSetpoint_called_several_times() {
        for (int i = 0; i < 30; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingSetpointDegrees = subject.getSetpointDegrees();
        assertEquals(Arm.STARTING_ANGLE + 30.0, resultingSetpointDegrees, 0.01);
    }

    @Test
    public void toDegrees_from_0_counts() {
        double result = Arm.toDegrees(0.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void toCounts_from_0_degrees() {
        double result = Arm.toCounts(0.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void toCounts_from_90_degrees() {
        double result = Arm.toCounts(90.0);
        assertEquals(1570.133, result, 0.01);
    }

    @Test
    public void toCounts_from_180_degrees() {
        double result = Arm.toCounts(180.0);
        assertEquals(3140.267, result, 0.01);
    }

    @Test
    public void toCounts_from_270_degrees() {
        double result = Arm.toCounts(270.0);
        assertEquals(4710.4, result, 0.01);
    }

    @Test
    public void toCounts_from_360_degrees() {
        double result = Arm.toCounts(360.0);
        assertEquals(6280.533, result, 0.01);
    }
}