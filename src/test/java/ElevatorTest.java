import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElevatorTest {

    private Elevator subject;

    @Before
    public void setUp() {
        subject = new Elevator();
    }

    @Test
    public void moveSetpoint_called_once() {
        subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval

        double resultingSetpointHeight = subject.getSetpointHeight();
        assertEquals(Elevator.STARTING_HEIGHT + 1.0, resultingSetpointHeight, 0.01);
    }

    @Test
    public void moveSetpoint_called_several_times() {
        for (int i = 0; i < 30; i++) {
            subject.moveSetpoint(1.0); // 1.0 represents full speed "downward" for this time interval
        }

        double resultingSetpointHeight = subject.getSetpointHeight();
        assertEquals(Elevator.STARTING_HEIGHT + 30.0, resultingSetpointHeight, 0.01);
    }

    @Test
    public void toInches_from_0_counts() {
        double result = Elevator.toInches(0.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void toCounts_from_0_inches() {
        double result = Elevator.toCounts(0.0);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void encoder_count_4096_makes_about_2_inches_of_movement() {
        // 4096 is the encoder count for a full revolution at the gearbox
        double result = Elevator.toInches(4096);
        assertEquals(1.84, result, 0.01);
    }

    @Test
    public void encoder_count_8750_makes_1_revolution_of_movement() {
        double result = Elevator.toInches(8750);
        assertEquals(Math.PI * 1.25, result, 0.01);
    }

    @Test
    public void encoder_count_89000_makes_40_inches_of_movement() {
        double result = Elevator.toInches(89000);
        assertEquals(40, result, 0.01);
    }

}