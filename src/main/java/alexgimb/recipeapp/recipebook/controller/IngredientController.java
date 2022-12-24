package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping
    public Collection<Recipe> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @PostMapping
    public Recipe addIngredient(@RequestBody Recipe ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteIngredient(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredient(id);
    }
}
