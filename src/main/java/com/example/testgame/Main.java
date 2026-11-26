package com.example.testgame;

import java.awt.image.BufferedImage;
import java.io.File;

import com.example.testgame.machines.*;
import com.example.testgame.machines.MachineScript;
import com.github.spynets.speler.Game;
import com.github.spynets.speler.Scene;
import com.github.spynets.speler.Vector2;
import com.github.spynets.speler.ecs.Camera;
import com.github.spynets.speler.ecs.components.ColliderComponent;
import com.github.spynets.speler.ecs.components.Renderable;
import com.github.spynets.speler.ecs.components.Rigidbody;
import com.github.spynets.speler.ecs.components.ScriptComponent;
import com.github.spynets.speler.ecs.components.SpriteComponent;
import com.github.spynets.speler.resources.ResourceManager;
import com.github.spynets.speler.resources.SpriteSheet;
import com.github.spynets.speler.resources.ResourceManager.Sprite;
import com.github.spynets.speler.scripting.GameObject;
import com.github.spynets.speler.scripting.Script;
import com.github.spynets.speler.swing.JGameWindow;
import com.github.spynets.speler.swing.SwingRenderer;
import com.github.spynets.speler.tiled.*;


public class Main {

		public static Game game;// = new Game(new JGameWindow(), new MyScene());
		public static void main(String[] args) {


				game = new Game(new JGameWindow(), new MyScene());
    
        LevelLoader loader = new LevelLoader(game);

        loader.setObjectHandler(new ObjectHandler() {
                @Override
                public void instaniateObject(LevelObject obj) {

                    GameObject player = new GameObject(game.getEcs());
                    player.transform.position = obj.transform.position;
                    

                    if (obj.getOriginal().getName().equals("player")) {
                        player.transform.worldScale = new Vector2(100,100);
                        player.addComponent(new ScriptComponent(new Player()));
                    }
                    else if(obj.getOriginal().getName().equals("collider"))  {
                        player.transform.worldScale = obj.transform.worldScale;
                        //player.addComponent(new ColliderComponent());
                        player.addComponent(new Renderable());
                    }

                }
            });
        //game.getSelectedScene().getCamera().zoom = 1.5f;
        try{
            loader.load("/home/spy/dev/farming/src/main/resources/tiled/map.tmx");
        } catch(Exception e) {e.printStackTrace();}


        

				//game.getWindow().setBlankCursor();
				game.run();
    }

}
