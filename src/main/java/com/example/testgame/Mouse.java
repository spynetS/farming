package com.example.testgame;

import java.util.UUID;

import com.example.testgame.machines.MachineScript;
import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.ecs.components.Transform;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.*;
import com.github.spynets.speler.input.*;

public class Mouse extends Script {

    @Override
    public void start() {
        transform.worldScale = new Vector2(50,50);
        try {
            SpriteComponent comp = new SpriteComponent(Sprite.getSprite("./farm/tileselector.png"));
            gameObject.addComponent(comp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float deltatime) {
        // TODO Auto-generated method stub
        super.update(deltatime);
        Vector2 mouse = Input.getMousePosition();
        transform.position = new Vector2(
                                         Math.round(mouse.getX() / 50f) * 50f,
                                         Math.round(mouse.getY() / 50f) * 50f
                                        );

        if(Input.isMouseButtonDown(Keys.LEFTCLICK)) {
            System.out.println("clicked");


            for(UUID i : gameObject.getEcs().getEntities()){
                Transform t = gameObject.getEcs().getComponent(i, Transform.class);
                if(t == null) continue;
                if (!transform.position.equals(t.position)) continue;
                
                ScriptComponent sc = gameObject.getEcs().getComponent(i, ScriptComponent.class);
                if(sc == null) continue;



                if (sc.getScript(MachineScript.class) != null)
                    return;
            }


            GameObject machine = new GameObject();
            machine.instantiate(gameObject.getEcs());
            machine.addComponent(new ScriptComponent(new MachineScript()));
            machine.transform.position = transform.position;

            //gameObject.instantiate(machine);
        }
    }
}
