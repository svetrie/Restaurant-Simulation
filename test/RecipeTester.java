import com.example.JsonFileLoader;
import com.example.Recipe;
import com.example.Restaurant;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTester {
    private Recipe recipe;

    @Before
    public void setUp() {
        Restaurant restaurant = JsonFileLoader.getRestaurantFromJsonFile("Restaurant.json");
        recipe = restaurant.getRecipeByName("fries_recipe");
    }

    @Test
    public void getNameTest() {
        assertEquals("fries_recipe", recipe.getName());
    }

    @Test
    public void getBaseValueTest() {
        assertEquals(500, recipe.getBaseValue(), 0.00001);
    }

    @Test
    public void getIngredientTest() {
        assertTrue(recipe.getIngredients()[0].equals("potatoes"));
    }

    @Test
    public void getCookedDishTest() {
        assertEquals("fries", recipe.getCookedDish().getName());
    }

    @Test
    public void getRequiredEquipmentTest() {
        assertEquals("fryer", recipe.getRequiredEquipments()[0]);
    }

    @Test
    public void getMarketValueTest() {
        assertEquals(250, recipe.getMarketValue(), 0.00001);
    }

}
