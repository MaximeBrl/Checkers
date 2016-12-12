/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ptut.dames.Assets;

/**
 *
 * @author Jérémy Montrobert
 */
public class Tile extends Actor {

    public boolean estEnSurbrillance;

    private final TextureRegion textureRegion;
    private TextureRegion TextureRegionEnSurbrillance;

    /**
     * Créer une tile pour le Plateau
     *
     * @param x position horizontale de la tile
     * @param y position verticale de la tile
     * @param estNoir Determine si la tile est noire ou non
     */
    public Tile(int x, int y, boolean estNoir) {
        this.setBounds(x, y, 1, 1);

        if (estNoir) {
            this.textureRegion = Assets.gameAtlas.findRegion("tile-2");
            this.TextureRegionEnSurbrillance = Assets.gameAtlas.findRegion("tile-2-highlighted");
        } else {
            this.textureRegion = Assets.gameAtlas.findRegion("tile-1");
            this.TextureRegionEnSurbrillance = Assets.gameAtlas
                    .findRegion("tile-1-highlighted");
        }
        
        
        
        
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (this.estEnSurbrillance) {
            batch.draw(this.TextureRegionEnSurbrillance, this.getX(), this.getY(),
                    1, 1);
        } else {
            batch.draw(this.textureRegion, this.getX(), this.getY(), 1, 1);
        }
    }

}
