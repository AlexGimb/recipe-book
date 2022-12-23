package alexgimb.recipeapp.recipebook.controller;


import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.RecipeService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class RecipeBookController {
    private final RecipeService recipeService;

    public RecipeBookController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe")
    public Collection<Recipe> getAllRecipeBooks() {
        return this.recipeService.getAllRecipeBooks();
    }

    @PostMapping("/newrecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/delete/{id}")
    public Recipe deleteRecipe(@PathVariable("id") int id) {
        return this.recipeService.removeRecipe(id);
    }
}
