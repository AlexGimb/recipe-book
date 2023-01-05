package alexgimb.recipeapp.recipebook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService{
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${recipe.of.data.file}")
    private String dataFileRecipe;
    private String dataFileIngredient;
    @Override
    public boolean saveFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, dataFileRecipe), json);
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка сохранения файла");
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath,dataFileRecipe));
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка чтения файла");
        }
    }
    @Override
    public boolean cleanFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileRecipe));
            Files.createFile(Path.of(dataFilePath, dataFileRecipe));
            return true;
        } catch (IOException e) {
            throw new RecipeBookException("Ошибка очистки файла");
        }
    }
}
