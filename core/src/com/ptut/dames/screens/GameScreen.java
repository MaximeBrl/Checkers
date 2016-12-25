package com.ptut.dames.screens;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ptut.dames.Assets;
import com.ptut.dames.controller.ControlPlateau;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.views.GameRenderer;

public class GameScreen implements Screen {

    private GameRenderer renderer;
    public static String couleur1, couleur2, nom1, nom2;
    Stage stage;
    Skin skinneon;
    OrthographicCamera camera;
    TextButton menu;
    InputMultiplexer multiplexer;

    public GameScreen() {
        stage = new Stage();
        skinneon = new Skin(Gdx.files.internal("neonui/neonui/neon-ui.json"));
        menu = new TextButton("menu", skinneon);
        camera = new OrthographicCamera();
        stage.getViewport().setCamera(camera);
        multiplexer = new InputMultiplexer();

    }

    @Override
    public void render(float delta) {

        this.renderer.render(delta);
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        this.renderer.setSize(width, height);

    }

    @Override
    public void show() {
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
        Plateau plateau;
        ControlPlateau controller;

        Assets.loadGame();

        plateau = new Plateau();
        plateau.ajoutPions();
        controller = new ControlPlateau(plateau);

        this.renderer = new GameRenderer(plateau, controller);
        this.renderer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menu.setPosition(850, 550);
        stage.addActor(menu);

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
