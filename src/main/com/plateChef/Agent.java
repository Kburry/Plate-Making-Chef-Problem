package com.plateChef;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * Agrent Runnable that provides ingredients for the chefs to make food.
 */
public class Agent implements Runnable{

    private static final int NUMBER_OF_TIMES_TO_PLACE = 20;
    private ChefTable table;
    private List<Ingredient> ingredients;

    /**
     * Initialize Agent.
     * @param table - The table the chefs will be working at.
     */
    Agent(ChefTable table){
        ingredients = new ArrayList<>(EnumSet.allOf(Ingredient.class));
        this.table = table;
    }

    /**
     * Place two random ingredients on the table.
     * @throws InterruptedException - Throws interrupted exception when interrupted.
     */
    private void place() throws InterruptedException {
        Ingredient ingredient1, ingredient2;
        Random randomizer = new Random();
        //selecting 2 random ingredients.
        ingredient1 = ingredients.get(randomizer.nextInt(ingredients.size()));
        do{
            ingredient2 = ingredients.get(randomizer.nextInt(ingredients.size()));
        } while (ingredient1 == ingredient2);
        //place random ingredients on table.
        table.place(ingredient1,ingredient2);
    }

    /**
     * Runs until 20 sets of ingredients have been placed on the table and then stops.
     */
    @Override
    public void run() {
        for(int i = 0; i < NUMBER_OF_TIMES_TO_PLACE; i++) {
            try {
                place();
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Agent has place his 20th ingredients.");
    }
}
