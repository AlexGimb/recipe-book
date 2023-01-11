package alexgimb.recipeapp.recipebook.controller;

import alexgimb.recipeapp.recipebook.service.FileServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("/files")
@Tag(name = "Импорт, экспорт файлов", description = "Импорт и экспорт файлов рецептов и ингредиентов")
public class FileController {
    private final FileServiceImpl fileService;

    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/export/recipe")
    @Operation(summary = "Экспорт рецептов",
            description = "Экспорт всех рецептов добавленных в книгу")
    public ResponseEntity<InputStreamResource> downloadFileRecipe() throws FileNotFoundException {
        File file = fileService.getDataFileRecipe();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeBook.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/export/ingredient")
    @Operation(summary = "Экспорт ингредиентов",
            description = "Экспорт всех ингредиентов добавленных в книгу")
    public ResponseEntity<InputStreamResource> downloadFileIngredient() throws FileNotFoundException {
        File file = fileService.getDataFileIngredient();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredients.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Импорт рецептов")
    public ResponseEntity<Void> upLoadDataFileRecipe(@RequestParam MultipartFile file) {
        fileService.cleanFileRecipe();
        File dataFileRecipe = fileService.getDataFileRecipe();

        try (FileOutputStream fos = new FileOutputStream(dataFileRecipe)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Импорт Ингредиентов")
    public ResponseEntity<Void> upLoadDataFileIngredient(@RequestParam MultipartFile file) {
        fileService.cleanFileIngredient();
        File dataFileIngredient = fileService.getDataFileIngredient();

        try (FileOutputStream fos = new FileOutputStream(dataFileIngredient)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
