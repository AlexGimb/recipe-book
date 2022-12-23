package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping("/ingredient")
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @PostMapping("/newingredient")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/deleteingredient/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredient(id);
    }
}
