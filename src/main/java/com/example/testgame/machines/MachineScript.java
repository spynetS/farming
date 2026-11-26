package com.example.testgame.machines;

import java.util.ArrayList;

import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.animations.Animatable;
import com.github.spynets.speler.animations.AnimationListener;
import com.github.spynets.speler.animations.AnimationTrack;
import com.github.spynets.speler.animations.TimeTrackedAnimation;
import com.github.spynets.speler.animations.AnimationTrack.Keyframe;
import com.github.spynets.speler.ecs.CollisionEvent;
import com.github.spynets.speler.ecs.components.AnimationComponent;
import com.github.spynets.speler.ecs.components.ColliderComponent;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.input.Input;
import com.github.spynets.speler.input.Keys;
import com.github.spynets.speler.resources.ResourceManager;
import com.github.spynets.speler.resources.SpriteSheet;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.*;

import java.util.Set;
import java.util.List;

import com.example.testgame.items.*;


public class MachineScript extends Script implements AnimationListener, MachineListener {

    SpriteComponent spriteComponent = new SpriteComponent();
    AnimationComponent animationComponent = new AnimationComponent();
    Machine m1 = new Machine();

    @Override
    public void start() {

        m1.addListener(this);

        Item wood = new Item("wood", "wood", Set.of(ItemTag.FUEL), 64);
        Item ore = new Item("iron ore", "iron ore", Set.of(ItemTag.ORE), 64);
        Item metal = new Item("iron", "iron", Set.of(ItemTag.METAL), 64);


        Recipe r1 = new Recipe(2,
                               List.of(new Ingredient(wood, ItemTag.FUEL, 1), new Ingredient(ore, ItemTag.ORE, 1)),
                               List.of(new ItemStack(metal, 1)));
        m1.setLoadedRecipe(r1);

        transform.worldScale = new Vector2(50,50);

        createAnimation();

        gameObject.addComponent(animationComponent);
        gameObject.addComponent(spriteComponent);
    }

    @Override
    public void animationEnded() {
        
    }

    @Override
    public void animationStarted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(float deltatime) {
        // TODO Auto-generated method stub
        super.update(deltatime);

        if(Input.isKeyDown(Keys.SPACE)) {
            animationComponent.currentTrack = 1;
        }
        if(animationComponent.currentTrack == 1) {
            m1.tick(deltatime);
        }
        
    }

    private void createAnimation() {
        ArrayList<Keyframe<String>> idle = new ArrayList<>();
        ArrayList<Keyframe<String>> working = new ArrayList<>();
        SpriteSheet sheet = new SpriteSheet(ResourceManager.loadImage("./farm/wood_machine.png"), 16,16);

        for(int i = 0; i < 4;i ++) {
            try {
                idle.add(new Keyframe<String>(i*0.2f, Sprite.getSprite(sheet, 0, i)));
                working.add(new Keyframe<String>(i*0.2f, Sprite.getSprite(sheet, 1, i)));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        animationComponent.tracks.add(new TimeTrackedAnimation<>(idle, new Animatable<String>() {
                public void apply(String value) {
                    spriteComponent.image = value;
                };
            }, true));

        animationComponent.tracks.add(new TimeTrackedAnimation<>(working, new Animatable<String>() {
                public void apply(String value) {
                    spriteComponent.image = value;
                };
            }, true));

        animationComponent.tracks.get(1).addListener(this);
    }

    @Override
    public void onRecipeCompleted(Recipe recipe) {
        animationComponent.currentTrack = 0;

        for(ItemStack stack : recipe.getOutput()) {
            System.out.println("dropping " + stack.getAmount() +" of " + stack.getItem() );

            GameObject ob = new GameObject();
            ob.instantiate(gameObject.getEcs());
            try {
                SpriteSheet itemsheet = new SpriteSheet(ResourceManager.loadImage("./farm/objects&items/items free.png"), 16,16);
                ob.addComponent(new SpriteComponent(Sprite.getSprite(itemsheet, 2, 0)));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ob.addComponent(new ScriptComponent(new ItemScript(stack.getItem())));
            ob.transform.worldScale = new Vector2(30,30);
            ob.transform.position = transform.position.add(new Vector2(0, -100));




        }
    }
}
