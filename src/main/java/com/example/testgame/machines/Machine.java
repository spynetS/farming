package com.example.testgame.machines;

import java.util.ArrayList;

import org.lwjgl.opengl.AMDPerformanceMonitor;

import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.animations.Animatable;
import com.github.spynets.speler.animations.AnimationListener;
import com.github.spynets.speler.animations.AnimationTrack;
import com.github.spynets.speler.animations.TimeTrackedAnimation;
import com.github.spynets.speler.animations.AnimationTrack.Keyframe;
import com.github.spynets.speler.ecs.CollisionEvent;
import com.github.spynets.speler.ecs.components.AnimationComponent;
import com.github.spynets.speler.ecs.components.ColliderComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.input.Input;
import com.github.spynets.speler.input.Keys;
import com.github.spynets.speler.resources.ResourceManager;
import com.github.spynets.speler.resources.SpriteSheet;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.*;


public class Machine extends Script implements AnimationListener {

    SpriteComponent spriteComponent = new SpriteComponent();
    AnimationComponent animationComponent = new AnimationComponent();

    @Override
    public void start() {

        transform.worldScale = new Vector2(50,50);

        ArrayList<Keyframe<String>> idle = new ArrayList<>();
        ArrayList<Keyframe<String>> working = new ArrayList<>();
        SpriteSheet sheet = new SpriteSheet(ResourceManager.loadImage("./farm/wood_machine.png"), 16,16);

        for(int i = 0; i < 4;i ++) {
            try {
                idle.add(new Keyframe<String>(i*0.5f, Sprite.getSprite(sheet, 0, i)));
                working.add(new Keyframe<String>(i*0.5f, Sprite.getSprite(sheet, 1, i)));


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

        gameObject.addComponent(animationComponent);
        gameObject.addComponent(spriteComponent);
    }

    @Override
    public void animationEnded() {
        animationComponent.currentTrack = 0;
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

    }
}
