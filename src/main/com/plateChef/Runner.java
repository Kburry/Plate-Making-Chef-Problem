package com.plateChef;

public class Runner {
    public static void main(String[] args) {
        ChefTable table = new ChefTable();
        Agent agent = new Agent(table);
        Chef chickenChef = new Chef(Ingredient.Chicken, table);
        Chef saladChef = new Chef(Ingredient.Salad, table);
        Chef riceChef = new Chef(Ingredient.Rice, table);

        Thread agentThread = new Thread(agent);
        Thread chef1Thread = new Thread(chickenChef);
        Thread chef2Thread = new Thread(saladChef);
        Thread chef3Thread = new Thread(riceChef);

        agentThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chef1Thread.start();
        chef2Thread.start();
        chef3Thread.start();
        while (agentThread.isAlive()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chef1Thread.interrupt();
        chef2Thread.interrupt();
        chef3Thread.interrupt();
    }
}
