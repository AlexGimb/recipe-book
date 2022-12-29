package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Collection<Map.Entry<Integer, Ingredient>> allIngredient() {
        return ingredientService.getAllIngredientBooks();
    }

    @GetMapping("search/{id}")
    public Recipe searchRecipeForIngredient(@PathVariable("id") int id) {
        return this.ingredientService.searchRecipeByIngredient(id);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteRecipe(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredient(id);
    }
}
