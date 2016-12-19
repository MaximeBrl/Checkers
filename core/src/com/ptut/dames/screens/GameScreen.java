
package com.ptut.dames.screens;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ptut.dames.Assets;
import com.ptut.dames.controller.ControlPlateau;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.views.GameRenderer;

public class GameScreen implements Screen {

    private GameRenderer renderer;
    public static String couleur1, couleur2, nom1, nom2;
    
    



    @Override
    public void render(float delta) {
        this.renderer.render(delta);
       
    }

    @Override
    public void resize(int width, int height) {
        this.renderer.setSize(width, height);
    }

    @Override
    public void show() {
        Plateau plateau;
        ControlPlateau controller;
        

        Assets.loadGame();
        
        plateau = new Plateau();
        plateau.ajoutPions();
        controller= new ControlPlateau(plateau);
        this.renderer = new GameRenderer(plateau, controller);
        this.renderer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void hide() {
        this.renderer.dispose();
        Assets.disposeGame();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    } 

}
