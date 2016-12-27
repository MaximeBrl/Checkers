
package com.ptut.dames.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ptut.dames.controller.ControlPlateau;
import com.ptut.dames.model.Plateau;

public class GameRenderer implements Renderer {

    private final Stage stage = new Stage(new FitViewport(10, 10)); // I recommend initializing this stage in your constructor the same way you do for stage2.
    private Table hud;
    InputMultiplexer multiplexer;
    protected ControlPlateau controller;
    TextButton menu;
    OrthographicCamera camera;
    Stage stage2;
    Skin skinneon;


    public GameRenderer(Plateau plateau, ControlPlateau controller) {
        
        
         
        Gdx.input.setInputProcessor(this.stage); // Replace this line with your multiplexer code in render(); you want to set the multiplexer as your input processor.
        this.stage.addActor(plateau);           // Good.
        this.controller = controller;           // Good.
         multiplexer= new InputMultiplexer();   // Good.
         camera=new OrthographicCamera();       // Good.
        stage2 = new Stage();                   // Good.
        
        skinneon = new Skin(Gdx.files.internal("neonui/neonui/neon-ui.json" )); // Good.
         menu = new TextButton("menu", skinneon);                               // Good.
         menu.setPosition(10,10);               // Good.
         stage2.addActor(menu);                 // Good.
         
         
       
        
        /*addListener(new InputListener() {
            public void actionPerformed(InputEvent event) {
                controller.tap();
            }
        });*/
    }

    @Override
    public void render(float delta) {
        Plateau plateau = new Plateau(); // As a general rule of thumb, you do not want to create new classes in your render method. Move this to the constructor.
       
        Gdx.gl.glClearColor(.0f, .3f, .4f, 1);      // Good.
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);   // Good.
        this.stage.draw();                          // Good, but you should also call "this.stage.act()" like you do for stage2
        multiplexer.addProcessor(stage);            // This belongs in the constructor; you don't want it to reset every frame in render().
        multiplexer.addProcessor(new GestureDetector(new ControlPlateau(plateau))); // Same as above.
        multiplexer.addProcessor(stage2);           // Same as above.
        
        Gdx.input.setInputProcessor(multiplexer);   // Same as above.
        stage2.act();                               // Good.
        stage2.draw();                              // Good.
    }

    @Override
    public void setSize(int width, int height) {
        this.stage.getViewport().update(width, height, false);
        Gdx.graphics.requestRendering();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
       stage2.dispose();
    }

}
