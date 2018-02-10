package com.plateChef;

/**
 * The Tables the chefs are working at.
 */
public class ChefTable {

    private Ingredient ingredient1;
    private Ingredient ingredient2;

    /**
     * Agents tries to place ingredients on table.
     * @param i1 - Ingredient 1
     * @param i2 - Ingredient 2
     * @throws InterruptedException - Throws when interrupted.
     */
    public synchronized void place(Ingredient i1, Ingredient i2) throws InterruptedException{
        while (!isTableClean()) {
            System.out.println("Agent tried but couldn't put food on the table.");
            wait();
        }
        ingredient1 = i1;
        ingredient2 = i2;
        System.out.println("Agent Placed Ingredients on the Table!");
        System.out.println("Ingredient 1: " + ingredient1);
        System.out.println("Ingredient 2: " + ingredient2);
        notifyAll();
    }

    /**
     * Checks if Table is clean.
     * @return - true if table is clean.
     */
    private synchronized boolean isTableClean(){
        return ingredient1 == null && ingredient2 == null;
    }

    /**
     * Allows a chef to attempt to prepare and eat his meal. Waits if no ingredients are available or incorrect
     * ingredients are there.
     * @param chef - The Chef trying to prepare and eat his meal.
     * @throws InterruptedException - When interrupted throws this exception.
     */
    public synchronized void prepareEat(Chef chef) throws InterruptedException{
        while (isTableClean() || !prepare(chef)) wait();
        eat(chef);
        notifyAll();
    }

    /**
     * Tells Chef to try and prepare his meal.
     * @param chef - chef trying to prepare meal.
     * @return - boolean on if he was successful.
     */
    private synchronized boolean prepare(Chef chef){
        return chef.prepare(ingredient1, ingredient2);
    }

    /**
     * Chef eats and clears table.
     * @param chef - Chef that is eating.
     */
    private synchronized void eat(Chef chef){
        chef.eat();
        ingredient1 = null;
        ingredient2 = null;
    }


}
