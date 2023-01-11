package alexgimb.recipeapp.recipebook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${recipe.of.data.file}")
    private String dataFileRecipe;
    @Value("${ingredient.of.data.file}")
    private String dataFileIngredient;

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "temp", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveFileRecipe(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, dataFileRecipe), json);
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка сохранения файла рецепта");
        }

    }

    @Override
    public boolean saveFileIngredient(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, dataFileIngredient), json);
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка сохранения файла ингредиента");
        }
    }

    @Override
    public String readFromFileRecipe() {
        try {
            if (dataFileRecipe.isEmpty() || dataFileRecipe.isBlank() || dataFileRecipe == null) {
                System.out.println("Файл пустой");
            } else {
                return Files.readString(Path.of(dataFilePath, dataFileRecipe));
            }
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка чтения файла рецептов");
        }
        return readFromFileRecipe();
    }

    @Override
    public String readFromFileIngredient() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileIngredient));
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка чтения файла ингредиентов");
        }
    }

    @Override
    public boolean cleanFileRecipe() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileRecipe));
            Files.createFile(Path.of(dataFilePath, dataFileRecipe));
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка очистки файла");
        }

    }

    @Override
    public boolean cleanFileIngredient() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileIngredient));
            Files.createFile(Path.of(dataFilePath, dataFileIngredient));
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка очистки файла");
        }
    }

    @Override
    public File getDataFileRecipe() {
        return new File(dataFilePath + "/" + dataFileRecipe);
    }

    @Override
    public File getDataFileIngredient() {
        return new File(dataFilePath + "/" + dataFileIngredient);
    }
}
