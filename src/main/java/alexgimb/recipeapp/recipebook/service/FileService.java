package alexgimb.recipeapp.recipebook.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {

    Path createTempFile(String suffix);

    boolean saveFileRecipe(String json);

    boolean saveFileIngredient(String json);

    String readFromFileRecipe();

    String readFromFileIngredient();

    boolean cleanFileRecipe();

    boolean cleanFileIngredient();

    File getDataFileRecipe();

    File getDataFileIngredient();
}
