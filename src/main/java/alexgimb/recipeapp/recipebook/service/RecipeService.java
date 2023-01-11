package alexgimb.recipeapp.recipebook.service;

import alexgimb.recipeapp.recipebook.model.Recipe;

import java.util.Map;
import java.util.Set;

public interface RecipeService {
    Set<Map.Entry<Integer, Recipe>> getAllRecipeBooks();

    Recipe addRecipe(Recipe recipe);

    Recipe searchRecipe(int id);

    Recipe updateRecipe(int id, Recipe recipe);

    Recipe removeRecipe(int id);
}
