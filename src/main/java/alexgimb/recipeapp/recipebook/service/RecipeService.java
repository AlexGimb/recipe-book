package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class RecipeService  implements RecipeInterface {
    private final Map <Integer, Recipe> recipeBooks = new HashMap<>();
    IngredientService ingredientService = new IngredientService();

    @Override
    public Collection <Recipe> getAllRecipeBooks() {
        return recipeBooks.values();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (recipeBooks.containsValue(recipe)) {
            throw new RuntimeException("Такой рецепт уже существует");
        } else {
            recipeBooks.put(recipe.getId(), recipe);
            addIngredient(recipe);
        }
        return recipe;
    }

    public void addIngredient (Recipe recipe) {
        for (Recipe ingredient : recipeBooks.values()) {
            ingredientService.addIngredient(ingredient);
        }
    }

    @Override
    public Recipe removeRecipe(int id) {
        if (!recipeBooks.containsKey(id)) {
            throw new RuntimeException("Рецепт не найден!");
        } else {
            return recipeBooks.remove(id);
        }
    }
}
