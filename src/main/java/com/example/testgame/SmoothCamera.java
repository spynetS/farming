package com.example.testgame;

import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.components.Transform;
import com.github.spynets.speler.scripting.Script;

/**
 * SmoothCamera
 */
public class SmoothCamera extends Script {

    Transform target;
    Vector2 offset = new Vector2(0,0);

    public SmoothCamera(Transform target, Vector2 offset){
        this.target = target;
        this.offset = offset;
    }

    private Vector2 lerp(Vector2 a, Vector2 b, float t) {
        return a.add(b.subtract(a).multiply(t));
    }

    @Override
    public void update(float deltatime) {
        if(target == null) return;
        transform.position = lerp(transform.position, target.position.add(offset), 0.01f);
    }


}

