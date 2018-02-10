package com.plateChef;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;


public class Agent implements Runnable{

    private ChefTable table;

    private List<Ingredient> ingredients;

    Agent(ChefTable table){
        ingredients = new ArrayList<>(EnumSet.allOf(Ingredient.class));
        this.table = table;
    }

    private void place() throws InterruptedException {
        Ingredient ingredient1, ingredient2;
        Random randomizer = new Random();
        ingredient1 = ingredients.get(randomizer.nextInt(ingredients.size()));
        do{
            ingredient2 = ingredients.get(randomizer.nextInt(ingredients.size()));
        } while (ingredient1 == ingredient2);
        table.place(ingredient1,ingredient2);
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++) {
            try {
                place();
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Agent has place his 20th ingredients.");
    }
}
