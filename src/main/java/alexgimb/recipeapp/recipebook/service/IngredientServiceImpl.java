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
            throw new RuntimeException("Рецепт с таким ингредиентом не найден!");
        } else {
            search = ingredientBooks.get(id);
        }
        return search;
    }

    @Override
    public List<Recipe> searchRecipeByIngredient(int id) {
        List<Recipe> search = null;
        try {
            search = new ArrayList<>();
            for (Recipe recipe : RecipeServiceImpl.recipeBooks.values()) {
                for (Ingredient searchRecipe : recipe.getIngredients()) {
                    if (searchRecipe.equals(searchIngredient(id))) {
                        search.add(recipe);
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return search;
    }

    @Override
    public Ingredient removeIngredient(int id) {
        Ingredient remove = null;
        try {
            if (!ingredientBooks.containsKey(id)) {
                throw new RuntimeException("Такой ингредиент не найден");
            } else {
                remove = ingredientBooks.remove(id);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return remove;
    }
}
