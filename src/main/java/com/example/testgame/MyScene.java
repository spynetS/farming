package com.example.testgame;


import com.github.spynets.speler.swing.SwingRenderer;
import com.example.testgame.machines.Machine;
import com.github.spynets.speler.Game;
import com.github.spynets.speler.Scene;
import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.resources.SpriteSheet;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.GameObject;

public class MyScene extends Scene {

		@Override
		public void start (Game game) throws Exception {
				super.start(game);
        setRenderer(new SwingRenderer(this.camera));


        GameObject player = new GameObject(game.getEcs());
        player.addComponent(new ScriptComponent(new Player()));

        // GameObject machine = new GameObject(game.getEcs());
        // machine.addComponent(new ScriptComponent(new Machine()));

        GameObject mouse = new GameObject(game.getEcs());
        mouse.addComponent(new ScriptComponent(new Mouse()));

        // GameObject test = new GameObject(game.getEcs());
        // test.addComponent(new SpriteComponent (Sprite.getSprite("/home/spy/Pictures/0283a911-accc-4b72-bd7b-2692d0440810-1184525191.png")));
        // test.transform.worldScale = new Vector2(20,20);

		}
}
