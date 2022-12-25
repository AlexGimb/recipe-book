package alexgimb.recipeapp.recipebook.controller;


import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeBookController {
    private final RecipeServiceImpl recipeService;

    public RecipeBookController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Collection<Recipe> getAllRecipeBooks() {
        return this.recipeService.getAllRecipeBooks();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") int id) {
        return this.recipeService.removeRecipe(id);
    }
}
