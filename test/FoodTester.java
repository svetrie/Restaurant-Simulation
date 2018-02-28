import com.example.JsonFileLoader;
import com.example.Food;
import com.example.Restaurant;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FoodTester {
    private Food food;

    @Before
    public void setUp() {
        Restaurant restaurant = JsonFileLoader.getRestaurantFromJsonFile("Restaurant.json");
        food = restaurant.getFoodByName("fries");
    }

    @Test
    public void getNameTest() {
        assertEquals("fries", food.getName());
    }

    @Test
    public void getBaseValueTest() {
        assertEquals(200, food.getBaseValue(), 0.00001);
    }

    @Test
    public void getCookingTime() {
        assertEquals(15, food.getCookingTime());
    }

    @Test
    public void getMarketValueTest() {
        assertEquals(160, food.getMarketValue(), 0.00001);
    }
}
