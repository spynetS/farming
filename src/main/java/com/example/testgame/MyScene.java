package com.example.testgame;


import com.github.spynets.speler.swing.SwingRenderer;
import com.example.testgame.machines.MachineScript;
import com.github.spynets.speler.Game;
import com.github.spynets.speler.Scene;
import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.components.ColliderComponent;
import com.github.spynets.speler.ecs.components.Renderable;
import com.github.spynets.speler.ecs.components.Rigidbody;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.ecs.components.ui.TextElement;
import com.github.spynets.speler.ecs.components.ui.UiElement;
import com.github.spynets.speler.resources.SpriteSheet;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.GameObject;

public class MyScene extends Scene {

		@Override
		public void start (Game game) throws Exception {
				super.start(game);
        setRenderer(new SwingRenderer());


        GameObject player = new GameObject(game.getEcs());
        player.transform.position = new Vector2(120,0);
        player.addComponent(new ScriptComponent(new Player()));


        // GameObject machine = new GameObject(game.getEcs());
        // machine.addComponent(new ScriptComponent(new Machine()));

        GameObject mouse = new GameObject(game.getEcs());
        mouse.addComponent(new ScriptComponent(new Mouse()));

        // GameObject text = new GameObject(game.getEcs());
        // text.transform.position = new Vector2(500,500);
        // text.addComponent(new TextElement("Hej", 32));
        
        // GameObject test = new GameObject(game.getEcs());
        // test.addComponent(new Renderable());
        // test.addComponent(new ColliderComponent());
        // test.addComponent(new Rigidbody(false));
        


		}
}
