package com.example.testgame.machines;

import java.util.ArrayList;

/**
   A machine is a object that takes in items and after working on them,
   produces another.

   A machine follows a recipe on whats needed and what it should output.

*/

public class Machine {
    Recipe loadedRecipe;
    float elapsedTime = 0;

    private ArrayList<MachineListener> listeners = new ArrayList<>();
    
    

    public void tick(float dt) {
        if(this.loadedRecipe == null) return;
        
        this.elapsedTime += dt;
        if(this.elapsedTime > loadedRecipe.time) {

            this.elapsedTime = 0;
            for(MachineListener l : listeners) l.onRecipeCompleted(loadedRecipe);
        }
    }


    public void addListener(MachineListener listener) {
        listeners.add(listener);
    }

    public Recipe getLoadedRecipe() {
        return loadedRecipe;
    }

    public void setLoadedRecipe(Recipe loadedRecipe) {
        this.loadedRecipe = loadedRecipe;
    }
    
}
