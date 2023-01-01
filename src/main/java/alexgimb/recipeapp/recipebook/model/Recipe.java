package alexgimb.recipeapp.recipebook.model;

import lombok.Data;
import java.util.List;

@Data
public class Recipe {
    private final String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private final List<String> cookingInstructions;
}