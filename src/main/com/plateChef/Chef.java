package com.plateChef;

/**
 * Chef is a runnable Chef that tries to make meals with 3 ingredients.
 */
public class Chef implements Runnable {

    private Ingredient mainIngredient;
    private ChefTable table;

    /**
     * Initialize Chef
     * @param mainIngredient - The ingredient the chef has unlimited supply of
     * @param table - Table the chef is working at.
     */
    public Chef(Ingredient mainIngredient, ChefTable table){
        this.mainIngredient = mainIngredient;
        this.table = table;
    }

    /**
     * Eat prepared meal.
     */
    public void eat(){
        System.out.println(mainIngredient + " Chef is eating");
    }

    /**
     * prepare a meal to eat.
     * @param i1 - Ingredient 1
     * @param i2 - Ingredient 2
     * @return - whether the Chef can successfully prepare his meal with the ingredients provided.
     */
    public boolean prepare(Ingredient i1, Ingredient i2){
        if(mainIngredient == i1 || mainIngredient == i2){
            printCantPrepare();
            return false;
        }
        System.out.println(mainIngredient + " Chef is preparing his meal with " + i1 + " and " + i2);
        return true;
    }

    /**
     * The Runnable Method for Chef. Will have the chef continually try to prepare and eat meal until told to stop.
     */
    @Override
    public void run(){
        while (true) {
            try {
                table.prepareEat(this);
            } catch (InterruptedException e) {
                System.out.println(mainIngredient + " Chef was stopped.");
                break;
            }
        }
    }

    /**
     * Prints Message when improper ingredients are provided.
     */
    private void printCantPrepare(){
        System.out.println(mainIngredient + " Chef doesn't need more " + mainIngredient + " going to wait.");
    }

}
