import com.example.Time;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTester {

    @Before
    public void setUp() {
        Time.initializeTime();
    }

    @Test
    public void passTimeTest() {
        Time.passTime(80);
        assertTrue(Time.getHours() == 1);
        assertTrue(Time.getMinutes() == 20);
    }

    @Test
    public void passDayTest() {
        Time.passTime(1460);

        assertTrue(Time.getHours() == 0);
        assertTrue(Time.getMinutes() == 20);
        assertTrue(Time.getDays() == 1);
    }

    @Test
    public void resetTimeTest() {
        Time.passTime(80);
        Time.initializeTime();

        assertTrue(Time.getHours() == 0);
        assertTrue(Time.getMinutes() == 0);
    }
}
