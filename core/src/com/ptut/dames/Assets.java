/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author Jérémy Montrobert
 */
public class Assets {

    public static TextureAtlas gameAtlas;

    /* Charge tous les élélents graphiques tel que les Textures, nécessaires au jeu*/
    public static void loadGame() {
        gameAtlas = new TextureAtlas(Gdx.files.internal("atlases/open-chess-atlas.atlas"));
    }
    
    public static void disposeGame() {
		gameAtlas.dispose();
	}
}
