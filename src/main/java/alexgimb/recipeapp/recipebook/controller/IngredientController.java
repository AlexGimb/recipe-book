package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Ingredient;
import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Ингредиенты: просмотр всех, добавление, поиск, " +
        "поиск рецепта по наличию ингредиента в нем, редактирование, удаление")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @Operation(summary = "Список всех ингредиентов",
            description = "Список всех ингредиентов добавленных в книгу")
    public Collection<Map.Entry<Integer, Ingredient>> allIngredient() {
        return ingredientService.getAllIngredientBooks();
    }

    @GetMapping("search/recipe/{id}")
    @Operation(summary = "Поиск рецепта по ингредиенту",
            description = "Поиск добавленного в книгу рецепта по наличию ингредиента в нем, по ID")
    @Parameter(name = "id", example = "Введите ID ингредиента")
    public Recipe searchRecipeForIngredient(@PathVariable("id") int id) {
        return this.ingredientService.searchRecipeByIngredient(id);
    }

    @GetMapping("search/{id}")
    @Operation(summary = "Поиск ингредиента",
            description = "Поиск добавленного в книгу ингредиента по ID")
    @Parameter(name = "id", example = "Введите ID ингредиента")
    public Ingredient searchIngredient(@PathVariable("id") int id) {
        return this.ingredientService.searchIngredient(id);
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента",
            description = "Добавление нового ингредиента в книгу")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("{id}")
    @Operation(summary = "Редактирование ингредиента",
            description = "Редактирование добавленного в книгу ингредиента")
    public Ingredient updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление ингредиента",
            description = "Удаление ингредиента по ID из книги")
    @Parameter(name = "id", example = "Введите ID ингредиента")
    public Ingredient deleteRecipe(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredient(id);
    }
}
