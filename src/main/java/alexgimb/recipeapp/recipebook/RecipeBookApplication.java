package alexgimb.recipeapp.recipebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeBookApplication.class, args);
//        RecipeService recipeService = new RecipeService();
//        recipeService.addRecipe(new Recipe("Суп", 30, new Ingredient("марковь", 5, "шт"),
//                "Варить"));
//        System.out.println(recipeService.getAllRecipeBooks().toString());
    }
}
