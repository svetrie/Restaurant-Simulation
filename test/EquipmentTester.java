import com.example.Equipment;
import com.example.JsonFileLoader;
import com.example.Market;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquipmentTester {
    private Equipment equipment;

    @Before
    public void setUp() {
        Market market = JsonFileLoader.getMarketFromJsonFile("Market.json");
        market.initializeInventory();
        equipment = (Equipment) market.getItemByName("grill");
    }

    @Test
    public void getNameTest() {
        assertEquals("grill", equipment.getName());
    }

    @Test
    public void getBaseValueTest() {
        assertEquals(2000, equipment.getBaseValue(), 0.00001);
    }

    @Test
    public void getUpkeepCost() {
        assertEquals(75, equipment.getDailyUpkeep(), 0.0001);
    }

    @Test
    public void getMarketValueTest() {
        assertEquals(1000, equipment.getMarketValue(), 0.00001);
    }
}
