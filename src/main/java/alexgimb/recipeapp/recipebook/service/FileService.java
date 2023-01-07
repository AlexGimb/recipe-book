package alexgimb.recipeapp.recipebook.service;

import java.io.File;

public interface FileService {

    boolean saveFileRecipe(String json);

    boolean saveFileIngredient(String json);

    String readFromFileRecipe();

    String readFromFileIngredient();

    boolean cleanFileRecipe();

    boolean cleanFileIngredient();

    File getDataFileRecipe();

    File getDataFileIngredient();
}
