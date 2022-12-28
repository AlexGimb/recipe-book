package alexgimb.recipeapp.recipebook.service;

import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.model.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IngredientService {
    Set<Map.Entry<Integer, Ingredient>> getAllIngredientBooks();

    Ingredient searchIngredient(int id);

    List<Recipe> searchRecipeByIngredient(int id);

    Ingredient removeIngredient(int id);
}
