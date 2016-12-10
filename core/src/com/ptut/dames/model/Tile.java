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

    public boolean isHighlighted;

    private final TextureRegion textureRegion;
    private TextureRegion highlightedTextureRegion;

    /**
     * Creates a board tile.
     *
     * @param x Horizontal index of the tile.
     * @param y Vertical index of the tile.
     * @param isDark Determines whether the tile will be light or dark colored.
     */
    public Tile(int x, int y, boolean isDark) {
        this.setBounds(x, y, 1, 1);

        if (isDark) {
            this.textureRegion = Assets.gameAtlas.findRegion("tile-2");
            this.highlightedTextureRegion = Assets.gameAtlas.findRegion("tile-2-highlighted");
        } else {
            this.textureRegion = Assets.gameAtlas.findRegion("tile-1");
            this.highlightedTextureRegion = Assets.gameAtlas
                    .findRegion("tile-1-highlighted");
        }
        
        
        
        
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (this.isHighlighted) {
            batch.draw(this.highlightedTextureRegion, this.getX(), this.getY(),
                    1, 1);
        } else {
            batch.draw(this.textureRegion, this.getX(), this.getY(), 1, 1);
        }
    }

}
