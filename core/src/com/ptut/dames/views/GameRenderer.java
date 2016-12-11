/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.views;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ptut.dames.Assets;
import com.ptut.dames.Dames;
import com.ptut.dames.controller.ControlPlateau;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.screens.GameScreen;

public class GameRenderer implements Renderer {

    private final Stage stage = new Stage(new FitViewport(10, 10));
    private Table hud;
   
    protected ControlPlateau controller;
   /* public void addListener(InputListener a){ stage.addInputListener(a);}*/

    public GameRenderer(Plateau plateau, ControlPlateau controller) {
        Gdx.input.setInputProcessor(this.stage);
        this.stage.addActor(plateau);
        this.controller = controller;
        
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
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setInputProcessor(new GestureDetector(new ControlPlateau(plateau)));
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
