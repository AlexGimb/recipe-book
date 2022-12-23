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
        if (ingredients.containsKey(id)) {
            return ingredients.remove(id);
        } else {
            throw new RuntimeException("Такой ингредиент не найден");
        }
    }

    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }
}
