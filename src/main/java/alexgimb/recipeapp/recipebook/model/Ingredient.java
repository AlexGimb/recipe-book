package alexgimb.recipeapp.recipebook.model;


import java.util.Objects;

public class Ingredient {
    private final String name;
    private int count;
    private String unit;

    private final int id;

    private static int counter = 0;

    public Ingredient(String name, int count, String unit) {
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ingredient.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return count == that.count && Objects.equals(name, that.name) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count, unit);
    }
}

