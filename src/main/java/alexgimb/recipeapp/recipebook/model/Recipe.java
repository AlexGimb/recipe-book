package alexgimb.recipeapp.recipebook.model;

import alexgimb.recipeapp.recipebook.service.IngredientService;

import java.util.ArrayList;

import java.util.Objects;

public class Recipe {
    private final String name;
    private int cookingTime;
    private ArrayList<Ingredient> ingredients;
    private final ArrayList<String> cookingInstructions;
    private final int id;
    private static int counter = 0;

    public Recipe(String name, int cookingTime, ArrayList<Ingredient> ingredients, ArrayList<String> cookingInstructions) {
        this.name = name;
        this.cookingTime = cookingTime;
        setIngredients(ingredients);
        this.cookingInstructions = cookingInstructions;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        IngredientService ingredientService = new IngredientService();
        for (Ingredient ingredient : ingredients) {
            ingredientService.addIngredient(ingredient);
        }
        this.ingredients = ingredients;
    }

    public ArrayList<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Recipe.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(cookingInstructions, recipe.cookingInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cookingTime, ingredients, cookingInstructions);
    }
}

