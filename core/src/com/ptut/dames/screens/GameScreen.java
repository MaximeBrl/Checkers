/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.screens;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ptut.dames.Assets;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.views.GameRenderer;

public class GameScreen implements Screen {

    private GameRenderer renderer;

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
        Plateau plateau; // Can't call the constructor here. Assets have to be
        // loaded first.

        Assets.loadGame();
        plateau = new Plateau();
        plateau.populate();
        this.renderer = new GameRenderer(plateau);
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
    } // Never called automatically.

}
