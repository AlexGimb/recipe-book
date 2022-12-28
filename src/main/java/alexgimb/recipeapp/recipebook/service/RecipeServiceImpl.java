package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
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
        try {
            if (recipeBooks.containsValue(recipe)) {
                throw new RuntimeException("Такой рецепт уже существует");
            } else {
                recipeBooks.put(recipeId++, recipe);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return recipe;
    }

    @Override
    public Recipe searchRecipe(int id) {
        Recipe search = null;
        try {
            if (!recipeBooks.containsKey(id)) {
                throw new RuntimeException("Рецепт не найден!");
            } else {
                search = recipeBooks.get(id);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return search;
    }

    @Override
    public Recipe removeRecipe(int id) {
        Recipe remove = null;
        try {
            if (!recipeBooks.containsKey(id)) {
                throw new RuntimeException("Рецепт не найден!");
            } else {
                remove = recipeBooks.remove(id);
            }
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return remove;
    }
}

