import org.junit.Test;

import static org.junit.Assert.*;

public class UtilityTest {

    @Test
    public void clamp_less_than_min() {
        double result = Utility.clamp(75.0, 85.0, 210.0);
        assertEquals(85.0, result, 0.0001);
    }

    @Test
    public void clamp_within_range() {
        double result = Utility.clamp(105.0, 85.0, 210.0);
        assertEquals(105.0, result, 0.0001);
    }

    @Test
    public void clamp_greater_than_max() {
        double result = Utility.clamp(335.0, 85.0, 210.0);
        assertEquals(210.0, result, 0.0001);
    }
}