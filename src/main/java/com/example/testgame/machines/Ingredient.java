package com.example.testgame.machines;


import com.example.testgame.items.*;

public class Ingredient {
    private final Item item;
    private final ItemTag tag;
    private final int amount;
    
    public Ingredient(Item item, ItemTag tag, int amount) {
        this.item = item;
        this.tag = tag;
        this.amount = amount;
    }
}
