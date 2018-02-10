package com.plateChef;

public class Chef implements Runnable {

    private Ingredient mainIngredient;
    private ChefTable table;

    public Chef(Ingredient mainIngredient, ChefTable table){
        this.mainIngredient = mainIngredient;
        this.table = table;
    }

    public void eat(){
        System.out.println(mainIngredient + " Chef is eating");
    }

    public boolean prepare(Ingredient i1, Ingredient i2){
        if(mainIngredient == i1 || mainIngredient == i2){
            printCantPrepare();
            return false;
        }
        System.out.println(mainIngredient + " Chef is preparing his meal with " + i1 + " and " + i2);
        return true;
    }

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

    private void printCantPrepare(){
        System.out.println(mainIngredient + " Chef doesn't need more " + mainIngredient + " going to wait.");
    }

}
