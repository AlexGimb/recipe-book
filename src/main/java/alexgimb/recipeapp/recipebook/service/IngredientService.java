package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService implements IngredientInterface {
    public final Map<Integer, Recipe> ingredients = new HashMap<>();

    @Override
    public Recipe addIngredient(Recipe ingredient) {
        if (ingredients.containsValue(ingredient)) {
            throw new RuntimeException("Такой ингредиент уже есть");
        } else {
            ingredients.put(ingredient.getId(), ingredient);
        }
        return ingredient;
    }

    @Override
    public Recipe removeIngredient(int id) {
        if (!ingredients.containsKey(id)) {
            throw new RuntimeException("Такой ингредиент не найден");
        } else {
            return ingredients.remove(id);
        }
    }
    @Override
    public Collection<Recipe> getAllIngredients() {
        return ingredients.values();
    }
}
