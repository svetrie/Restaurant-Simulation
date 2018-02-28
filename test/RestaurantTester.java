import com.example.Food;
import com.example.Equipment;
import com.example.Restaurant;
import com.example.Simulation;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class RestaurantTester {
    private Restaurant restaurant;

    @Before
    public void setUp() {
        Simulation simulation = new Simulation();
        restaurant = simulation.getRestaurant();
    }

    @Test
    public void getWealthTest() {
        assertTrue(restaurant.getWealth() == 500);
    }

    @Test
    public void getPeakBreakastHrTest() {
        assertTrue(restaurant.getPeakBreakfastHr() == 9);
    }

    @Test
    public void getRecipes() {
        assertTrue(restaurant.getRecipes().get(1).getName().equals("fries_recipe"));
        assertTrue(restaurant.getRecipes().get(0).getName().equals("salad_recipe"));
    }

    @Test
    public void hasFoodsTest() {
        String[] foodNames = {"potatoes", "fries", "lettuce"};
        assertTrue(restaurant.hasFoods(foodNames));
    }

    @Test
    public void getFoodByNameTest() {
        assertTrue(restaurant.getFoodByName("lettuce").getBaseValue() == 20);
    }

    @Test
    public void getRecipeByNameTest() {
        assertTrue(restaurant.getRecipeByName("fries_recipe").getBaseValue() == 500);
    }

    @Test
    public void getEquipmentByName() {
        assertTrue(restaurant.getEquipmentByName("fryer").getBaseValue() == 1000);
    }

    @Test
    public void addToMenuTest() {
        restaurant.addToMenu("potatoes");
        assertTrue(restaurant.getMenu().contains("potatoes"));
    }

    @Test
    public void removeFromMenuTest() {
        restaurant.removeFromMenu("fries");
        assertTrue(!restaurant.getMenu().contains("fries"));
    }

    @Test
    public void getEquipmentUpkeep() {
        assertTrue(restaurant.getEquipmentUpkeepCost() == 50);
    }

    @Test
    public void sellItemsTest() {
        Food fries = restaurant.getFoodByName("fries");
        restaurant.sellItems(fries, 2);

        assertTrue(!restaurant.getFoodInventory().contains(fries));
    }

    @Test
    public void cookFoodTest() {
        Food potatoes = restaurant.getFoodByName("potatoes");
        restaurant.cookFood(restaurant.getRecipeByName("fries_recipe"), 2);

        assertTrue(!restaurant.getFoodInventory().contains(potatoes));
    }
}
