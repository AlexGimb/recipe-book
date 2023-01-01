package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.apache.commons.lang3.StringUtils;
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
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredientBooks.containsValue(ingredient)) {
            throw new RecipeBookException("Такой ингредиент уже существует");
        } else {
            if (StringUtils.isEmpty(ingredient.getName()) || StringUtils.isEmpty(ingredient.getUnit()) ||
                    StringUtils.isBlank(ingredient.getName()) || StringUtils.isBlank(ingredient.getUnit()) ||
                    ingredient.getCount() < 0) {
                throw new RecipeBookException("Поля должны быть заполнены");
            }
        }
        return ingredientBooks.put(ingredientId++, ingredient);
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
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (!ingredientBooks.containsKey(id)) {
            throw new RecipeBookException("Такого ингредиента нет");
        } else {
            ingredientBooks.put(id, ingredient);
        }
        return ingredient;
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
