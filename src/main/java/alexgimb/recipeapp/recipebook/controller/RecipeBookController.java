package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/recipe")
public class RecipeBookController {
    private final RecipeServiceImpl recipeService;
    public RecipeBookController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping
    public Set<Map.Entry<Integer, Recipe>> getAllRecipeBooks() {
        return this.recipeService.getAllRecipeBooks();
    }

    @GetMapping("/search/{id}")
    public Recipe searchRecipe(@PathVariable("id") int id) {
        return this.recipeService.searchRecipe(id);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @PutMapping("{id}")
    public Recipe updateRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return this.recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("{id}")
    public Recipe deleteRecipe(@PathVariable("id") int id) {
        return this.recipeService.removeRecipe(id);
    }
}
