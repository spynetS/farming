package com.example.testgame.items;

import java.util.ArrayList;
import java.util.List;

import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.animations.Animatable;
import com.github.spynets.speler.animations.AnimationTrack;
import com.github.spynets.speler.animations.AnimationTrack.Keyframe;
import com.github.spynets.speler.animations.TimeTrackedAnimation;
import com.github.spynets.speler.ecs.CollisionEvent;
import com.github.spynets.speler.ecs.components.AnimationComponent;
import com.github.spynets.speler.ecs.components.ColliderComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.ecs.systems.UpdateSystem;
import com.github.spynets.speler.scripting.Script;

/**
 * Item Script
 */
public class ItemScript extends Script {
    AnimationComponent component = new AnimationComponent();
    Item item;

    public ItemScript(Item item) {
        this.item = item;
    }

    @Override
    public void start() {
        SpriteComponent spriteComponent = gameObject.getComponent(SpriteComponent.class);
        System.out.println(spriteComponent);

        ColliderComponent cc = new ColliderComponent();
        cc.isTrigger = true;

        gameObject.addComponent(cc);

        List<Keyframe<Float>> keyframes = new ArrayList<>();
        keyframes.add(new Keyframe<>(0, 0.0f));
        keyframes.add(new Keyframe<>(1, 1.0f));
        keyframes.add(new Keyframe<>(2, 0.0f));

        TimeTrackedAnimation<Float> track = new TimeTrackedAnimation<Float>(keyframes, new Animatable<Float>() {
                @Override
                public void apply(Float value) {
                    spriteComponent.offset = new Vector2(0, value*10);
                }
            }, true);

        component.tracks.add(track);
        gameObject.addComponent(component);
    }

    @Override
    public void onTrigger(CollisionEvent event) {
        super.onTrigger(event);
        if(event.transformB.tag == "player") {
            transform.position = new Vector2(1000,100);
            System.out.println("picked up");
        }

    }

}
