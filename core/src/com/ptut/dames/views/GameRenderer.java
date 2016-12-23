
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

    private final Stage stage = new Stage(new FitViewport(10, 10));
    private Table hud;
    InputMultiplexer multiplexer;
    protected ControlPlateau controller;
    TextButton menu;
    OrthographicCamera camera;
    Stage stage2;
    Skin skinneon;


    public GameRenderer(Plateau plateau, ControlPlateau controller) {
        
        
         
        Gdx.input.setInputProcessor(this.stage);
        this.stage.addActor(plateau);
        this.controller = controller;
         multiplexer= new InputMultiplexer();
         camera=new OrthographicCamera();
        stage2 = new Stage();
        stage2.getViewport().setCamera(camera);
        skinneon = new Skin(Gdx.files.internal("neonui/neonui/neon-ui.json" ));
         menu = new TextButton("menu", skinneon);
         menu.setPosition(10,10);
         stage2.addActor(menu);
         
         
       
        
        /*addListener(new InputListener() {
            public void actionPerformed(InputEvent event) {
                controller.tap();
            }
        });*/
    }

    @Override
    public void render(float delta) {
        Plateau plateau = new Plateau();
       
        Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        this.stage.draw();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(new ControlPlateau(plateau)));
        multiplexer.addProcessor(stage2);
        
        Gdx.input.setInputProcessor(multiplexer);
        stage2.act();
        stage2.draw();
    }

    @Override
    public void setSize(int width, int height) {
        this.stage.getViewport().update(width, height, false);
        Gdx.graphics.requestRendering();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

}
