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

    @GetMapping("search/recipe/{id}")
    public Recipe searchRecipeForIngredient(@PathVariable("id") int id) {
        return this.ingredientService.searchRecipeByIngredient(id);
    }

    @GetMapping("search/{id}")
    public Ingredient searchIngredient(@PathVariable("id") int id) {
        return this.ingredientService.searchIngredient(id);
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("update/{id}")
    public Ingredient updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("delete/{id}")
    public Ingredient deleteRecipe(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredient(id);
    }
}
