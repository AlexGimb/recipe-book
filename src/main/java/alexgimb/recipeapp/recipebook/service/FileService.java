package alexgimb.recipeapp.recipebook.service;

public interface FileService {
    boolean saveFile(String json);


    String readFromFile();

    boolean cleanFile();
}
