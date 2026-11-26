package com.example.testgame;

import com.example.testgame.machines.MachineScript;
import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
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

        if(Input.isMouseButtonPressed(Keys.LEFTCLICK)) {
            System.out.println("clicked");
            
            GameObject machine = new GameObject(gameObject.getEcs());
            machine.addComponent(new ScriptComponent(new MachineScript()));
            machine.transform.position = transform.position;

            //gameObject.instantiate(machine);
        }
    }
}
