package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    public final Map<Integer, Ingredient> ingredientBooks = new HashMap<>();
    private static int ingredientId = 0;

    @Override
    public Set<Map.Entry<Integer, Ingredient>> getAllIngredientBooks() {
        for (Recipe recipe : RecipeServiceImpl.recipeBooks.values()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (!ingredientBooks.containsValue(ingredient)) {
                    ingredientBooks.put(ingredientId++, ingredient);
                }
            }
        }
        return ingredientBooks.entrySet();
    }

    @Override
    public Ingredient searchIngredient(int id) {
        Ingredient search;
        if (!ingredientBooks.containsKey(id)) {
            throw new RecipeBookException("Ингредиент не найден");
        } else {
            search = ingredientBooks.get(id);
        }
        return search;
    }

    @Override
    public Recipe searchRecipeByIngredient(int id) {
        Recipe search = null;
        for (Recipe recipe : RecipeServiceImpl.recipeBooks.values()) {
            for (Ingredient searchRecipe : recipe.getIngredients()) {
                if (searchRecipe.equals(searchIngredient(id))) {
                    search = recipe;
                }
            }
        }
        return search;
    }

    @Override
    public Ingredient removeIngredient(int id) throws RuntimeException {
        Ingredient remove = null;
        if (!ingredientBooks.containsKey(id)) {
            throw new RecipeBookException("Такой ингредиент не найден");
        } else {
            remove = ingredientBooks.remove(id);
        }
        return remove;
    }
}
