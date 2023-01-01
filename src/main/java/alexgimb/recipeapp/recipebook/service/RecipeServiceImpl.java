package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    public static final Map <Integer, Recipe> recipeBooks = new HashMap<>();
    private static int recipeId = 0;

    @Override
    public Set<Map.Entry<Integer, Recipe>> getAllRecipeBooks() {
        return recipeBooks.entrySet();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (recipeBooks.containsValue(recipe)) {
            throw new RecipeBookException("Такой рецепт уже существует");
        } else {
            if (StringUtils.isEmpty(recipe.getName()) || StringUtils.isBlank(recipe.getName()) ||
                    recipe.getCookingTime() < 0) {
                throw new RecipeBookException("Поля должны быть заполнены");
            }
        }
        return recipeBooks.put(recipeId++, recipe);
    }

    @Override
    public Recipe searchRecipe(int id) throws RuntimeException {
        Recipe search = null;
        if (!recipeBooks.containsKey(id)) {
            throw new RecipeBookException("Рецепт не найден!");
        } else {
            search = recipeBooks.get(id);
        }
        return search;
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        if (!recipeBooks.containsKey(id)) {
            throw new RecipeBookException("Такого рецепта нет");
        } else {
            recipeBooks.put(id, recipe);
        }
        return recipe;
    }

    @Override
    public Recipe removeRecipe(int id) throws RuntimeException {
        Recipe remove = null;
        if (!recipeBooks.containsKey(id)) {
            throw new RecipeBookException("Рецепт не найден!");
        } else {
            remove = recipeBooks.remove(id);
        }
        return remove;
    }
}

