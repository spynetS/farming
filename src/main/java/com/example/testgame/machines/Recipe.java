package com.example.testgame.machines;

import java.util.List;

/**
 * Recipe
 */
public class Recipe {
    private List<Ingredient> input;
    private List<ItemStack> output;

    int time;

    public Recipe(int time, List<Ingredient> input, List<ItemStack> output) {
        this.time = time;
        this.input = input;
        this.output = output;
    }

    public List<Ingredient> getInput() {
        return input;
    }

    public List<ItemStack> getOutput() {
        return output;
    }


    public int getTime() {
        return time;
    }



    
}
