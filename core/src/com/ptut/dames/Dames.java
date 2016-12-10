package com.ptut.dames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ptut.dames.screens.FirstPage;
import com.ptut.dames.screens.GameScreen;
import com.ptut.dames.screens.JouerIA;
import com.ptut.dames.screens.JouerJoueur;
import com.ptut.dames.screens.SelectGame;

public class Dames extends Game {

    public static Game game;

    /*
     UWIDTH = variable correspondant au nombre d'unité de jeu pour la taille
    de l'écran, a noter que chaque pièce vaudra 1 unité de longueur et 1 de
    largeur
     */
    public static final int UWIDTH = 10;
    public GameScreen gamescreen;
    public FirstPage firstpage;
    public SelectGame selectgame;
    public JouerIA jouerIA;
    public JouerJoueur jouerjoueur;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(false);
        game = this;
        gamescreen = new GameScreen();
        firstpage = new FirstPage(this);
        selectgame = new SelectGame(this);

        jouerIA = new JouerIA(this);
        jouerjoueur = new JouerJoueur(this);

        this.setScreen(firstpage);
    }
}
