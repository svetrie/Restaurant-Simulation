import com.example.Equipment;
import com.example.Item;
import com.example.JsonFileLoader;
import com.example.Market;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarketTester {
    private Market market;

    @Before
    public void setUp() {
        market = JsonFileLoader.getMarketFromJsonFile("Market.json");
        market.initializeInventory();
    }

    @Test
    public void getFoodByNameTest() {
        assertTrue(market.getItemByName("milkshake").getBaseValue() == 200);
    }

    @Test
    public void getEquipmentByNameTest() {
        assertTrue(market.getItemByName("grill").getBaseValue() == 2000);
    }

    @Test
    public void getRecipeByNameTest() {
        assertTrue(market.getItemByName("burger_recipe").getBaseValue() == 750);
    }

    @Test
    public void getPurchasedItemsTest() {
        ArrayList<Item> purhasedItems = market.getPurchasedItems("fryer", 6);
        Item fryer = market.getItemByName("fryer");

        for (int i = 0; i < 6; i++) {
            assertTrue(purhasedItems.get(i).equals(fryer));
        }
    }
}
