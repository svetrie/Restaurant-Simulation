import com.example.Simulation;
import com.example.Time;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimulationTester {
    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation();
    }

    @Test
    public void passInvalidTimeTest() {
        assertEquals("Sorry, the time you inputted is invalid", simulation.passTime("fjhf"));
    }

    @Test
    public void passNegativeTimeTest() {
        assertEquals("Sorry, the time you inputted is invalid", simulation.passTime("-8"));
    }

    @Test
    public void passTimeTest() {
        simulation.usersNextMove("pass time 60");
        assertTrue(Time.getHours() == 1);
        assertTrue(Time.getMinutes() == 0);
    }

    @Test
    public void cookValidFoodTest() {
        assertEquals("Cooked fries", simulation.cookValidFood("fries_recipe", "2"));
    }

    @Test
    public void cookInvalidFoodTest() {
        assertEquals("Sorry, you can't cook sdjkd", simulation.cookValidFood("sdjkd", "2"));
    }

    @Test
    public void buyValidMarketItem() {
        simulation.buyFromMarket("milkshake", "1");
        assertTrue(simulation.getRestaurant().getFoodByName("milkshake") != null);
        assertTrue(simulation.getRestaurant().getWealth() == 300);
    }

    @Test
    public void buyInvalidMarketItem() {
        assertEquals("Sorry you can't buy 2 phone",
                simulation.buyFromMarket("phone", "2"));
    }

    @Test
    public void sellValidMarketItem() {
        simulation.sellToMarket("fryer", "1");
        assertTrue(simulation.getRestaurant().getEquipmentInventory().size() == 0);
        assertTrue(simulation.getRestaurant().getWealth() == 1000);
    }

    @Test
    public void sellInvalidMarketItem() {
        assertEquals("Sorry, you can't sell 2 phone",
                simulation.sellToMarket("phone", "2"));
    }

    @Test
    public void payEquipmentUpkeepTest() {
        simulation.passTime("1439");
        double wealthBeforeUpkeep = simulation.getRestaurant().getWealth();

        simulation.passTime("5");
        double wealthAfterUpkeep = simulation.getRestaurant().getWealth();

        assertTrue(wealthBeforeUpkeep - wealthAfterUpkeep == 50);
    }

    @Test
    public void simulateRestaurantSales() {
        double initialWealth = simulation.getRestaurant().getWealth();
        simulation.passTime("1440");

        assertTrue(simulation.getRestaurant().getWealth() > initialWealth);
        assertTrue(simulation.getRestaurant().getMenu().size() == 0);
    }
}
