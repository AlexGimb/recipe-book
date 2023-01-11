package alexgimb.recipeapp.recipebook.service;
import alexgimb.recipeapp.recipebook.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.join;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final FileServiceImpl fileService;
    public static HashMap <Integer, Recipe> recipeBooks = new HashMap<>();
    private static int recipeId = 0;


    public RecipeServiceImpl(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    public void init() {
        readFile();
    }

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
            } else {
                recipeBooks.put(recipeId++, recipe);
                saveFile();
            }
        }
        return recipe;
    }

    @Override
    public Recipe searchRecipe(int id) throws RuntimeException {
        Recipe search;
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
            saveFile();
        }
        return recipe;
    }

    @Override
    public Recipe removeRecipe(int id) throws RuntimeException {
        Recipe remove;
        if (!recipeBooks.containsKey(id)) {
            throw new RecipeBookException("Рецепт не найден!");
        } else {
            remove = recipeBooks.remove(id);
        }
        return remove;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeBooks);
            fileService.saveFileRecipe(json);
        } catch (JsonProcessingException e) {
            throw new RecipeBookException("Ошибка сохранения файла");
        }
    }

    public void readFile() {
        try {
            String json = fileService.readFromFileRecipe();
            recipeBooks = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
    }


    public Path createRecipePathReport() throws IOException {
        Path path = fileService.createTempFile("allRecipe");
        for (Recipe recipe : recipeBooks.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Рецепт: " + recipe.getName() + "\n" +
                        "Время приготовления: " + recipe.getCookingTime() + " минут" + "\n" +
                        "Ингредиенты:" + "\n" +
                        join(recipe.getIngredients(), "\n") + "\n" +
                        "Инструкция по приготовлению:" + "\n" +
                        join(recipe.getCookingInstructions(), "\n"));
                writer.append("\n");
                writer.append("\n");
                writer.append("-----------------------------------------------------------------------");
                writer.append("\n");
                writer.append("\n");
            }
        }
        return path;
    }
}

