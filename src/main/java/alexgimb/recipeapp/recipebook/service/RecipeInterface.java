package alexgimb.recipeapp.recipebook.service;

import alexgimb.recipeapp.recipebook.model.Recipe;

import java.util.Collection;

public interface RecipeInterface {
    public abstract Collection<Recipe> getAllRecipeBooks();

    public abstract Recipe addRecipe(Recipe recipe);

    public abstract Recipe removeRecipe(int id);
}
