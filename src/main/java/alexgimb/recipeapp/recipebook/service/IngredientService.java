package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService {
    public final Map<Integer, Ingredient> ingredients = new HashMap<>();

    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsValue(ingredient)) {
            throw new RuntimeException("Такой ингредиент уже есть");
        } else {
            ingredients.put(ingredient.getId(),ingredient);
        }
        return ingredient;
    }

    public Ingredient removeIngredient(int id) {
        if (!ingredients.containsKey(id)) {
            throw new RuntimeException("Такой ингредиент не найден");
        } else {
            return ingredients.remove(id);
        }
    }

    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }
}
