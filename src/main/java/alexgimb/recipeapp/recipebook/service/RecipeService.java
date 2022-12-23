package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class RecipeService {
    private final Map <Integer, Recipe> recipeBooks = new HashMap<>();

    public Collection <Recipe> getAllRecipeBooks() {
        return recipeBooks.values();
    }

    public Recipe addRecipe(Recipe recipe) {
        if (recipeBooks.containsValue(recipe)) {
            throw new RuntimeException("Такой рецепт уже существует");
        } else {
            recipeBooks.put(recipe.getId(), recipe);
        }
        return recipe;
    }

    public Recipe removeRecipe(int id) {
        if (recipeBooks.containsKey(id)) {
            return recipeBooks.remove(id);
        }
        else {
            throw new RuntimeException("Рецепт не найден!");
        }
    }
}
