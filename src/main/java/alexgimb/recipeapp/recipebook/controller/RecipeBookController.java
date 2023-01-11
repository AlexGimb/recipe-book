package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.model.Recipe;
import alexgimb.recipeapp.recipebook.service.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Рецепты: просмотр всех, добавление, поиск, редактирование, удаление")
public class RecipeBookController {
    private final RecipeServiceImpl recipeService;
    public RecipeBookController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(summary = "Список всех рецептов",
                description = "Список всех рецептов добавленных в книгу")
    public Set<Map.Entry<Integer, Recipe>> getAllRecipeBooks() {
        return this.recipeService.getAllRecipeBooks();
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Поиск рецепта",
                description = "Поиск добавленного в книгу рецепта по ID")
    @Parameter(name = "id", example = "Введите ID рецепта")
    public Recipe searchRecipe(@PathVariable("id") int id) {
        return this.recipeService.searchRecipe(id);
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта",
            description = "Добавление нового рецепта в книгу")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @PutMapping("{id}")
    @Operation(summary = "Редактирование рецептов",
            description = "Редактирование добавленного в книгу рецепта")
    public Recipe updateRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return this.recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление рецепта",
            description = "Удаление рецепта по ID из книги")
    @Parameter(name = "id", example = "Введите ID рецепта")
    public Recipe deleteRecipe(@PathVariable("id") int id) {
        return this.recipeService.removeRecipe(id);
    }

    @GetMapping("/export")
    @Operation(summary = "Экспорт рецептов",
            description = "Скачивание всех рецептов из книги")
    public ResponseEntity<Object> downloadAllRecipe() {
        try {
            Path path = recipeService.createRecipePathReport();
            if (Files.size(path) == 0) {
                return ResponseEntity.notFound().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok().
                    contentType(MediaType.TEXT_PLAIN).
                    contentLength(Files.size(path)).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllRecipes.txt\"").
                    body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
