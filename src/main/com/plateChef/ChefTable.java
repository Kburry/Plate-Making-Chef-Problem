package com.plateChef;

public class ChefTable {

    private Ingredient ingredient1;
    private Ingredient ingredient2;

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

    private synchronized boolean isTableClean(){
        return ingredient1 == null && ingredient2 == null;
    }

    public synchronized void prepareEat(Chef chef) throws InterruptedException{
        while (isTableClean() || !prepare(chef)) wait();
        eat(chef);
        notifyAll();
    }

    private synchronized boolean prepare(Chef chef){
        return chef.prepare(ingredient1, ingredient2);
    }

    private synchronized void eat(Chef chef){
        chef.eat();
        ingredient1 = null;
        ingredient2 = null;
    }


}
