package alexgimb.recipeapp.recipebook.service;

import alexgimb.recipeapp.recipebook.model.Recipe;

import java.util.Collection;

public interface IngredientService {

    Recipe addIngredient(Recipe ingredient);

    Recipe removeIngredient(int id);

    public Collection<Recipe> getAllIngredients();
}
