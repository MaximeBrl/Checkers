/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.model;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.Assets;


public class Piece extends Actor {
    
    public boolean estBlanc;
    public boolean peutManger = true; // Can this piece capture an enemy
    // piece using one of its normal
    // moves?

    /**
     * An array that contains valid moves for the chess piece.
     */
    protected Array<Coup> deplacPossible = new Array<Coup>();

    /**
     * An array that contains moves for the chess piece that are valid only when
     * used for capturing other pieces.
     */
    protected Array<Coup> coupManger = new Array<Coup>();
    private final TextureRegion textureRegion;

    public Piece(int y, int x, boolean isWhite, String regionName) {
        
        this.setBounds(x, y, 1, 1);
        this.estBlanc = isWhite;
        this.textureRegion = Assets.gameAtlas.findRegion(regionName);

        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Piece piece = (Piece) event.getListenerActor();
                System.out.println("test");
                int tx = (int) piece.getX(); // Tapped tile x.
                int ty = (int) piece.getY(); // Tapped tile y.
                System.out.println(tx +" "+ ty);
        
    


                return true;
            }

        });
    }

    public Array<Tile> getDeplacPossible(Plateau plateau, boolean checkFriendly) {
        Array<Tile> tiles = new Array<Tile>();
        int x = (int) this.getX();
        int y = (int) this.getY();

        for (Coup coup : this.deplacPossible) {
            boolean boucle = true;

            for (int i = 1; boucle; i++) {
                int tx = x + (coup.xOffset * i); // Tile x.
                int ty = y
                        + ((this.estBlanc ? coup.yOffset : -coup.yOffset) * i); // Tile
                // y.

                if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
                    Tile tile = plateau.getTilePos(tx, ty);
                    Piece autrepiece = plateau.getPiecePos(tx, ty);

                    if (autrepiece != null) {

                        if ((!checkFriendly || (autrepiece.estBlanc != this.estBlanc))
                                && this.peutManger) {
                            tiles.add(tile);
                        }
                        boucle = false;
                    } else {
                        tiles.add(tile);
                    }
                } else {
                    boucle = false;
                }

                if (!coup.continuous) {
                    boucle = false;
                }
            }
        }
        return tiles;
    }

    /**
     * Returns tiles on a board that can be accessed by this
	 * <code>Piece<code> instance according to its <code>captureOnlyMoves</code>
     * array.
     *
     * @param plateau The <code>Board</code> instance to fetch tiles from.
     * @param check Check the validity of the capturing move before adding the
     * tile to the list. e.g: If a capturable piece is present.
     * @return Resulting tile array.
     */
    public Array<Tile> getTilesManger(Plateau plateau, boolean check) {
        Array<Tile> tiles = new Array<Tile>();
        int x = (int) this.getX();
        int y = (int) this.getY();

        for (Coup coup : this.coupManger) {
            int tx = x + coup.xOffset; // Tile x.
            int ty = y + (this.estBlanc ? coup.yOffset : -coup.yOffset); // Tile
            // y.
            if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
                Tile tile = plateau.getTilePos(tx, ty);
                Piece autrepiece = plateau.getPiecePos(tx, ty);

                if (!check
                        || ((autrepiece != null) && (autrepiece.estBlanc != this.estBlanc))) {
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }

    public void moved() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(this.textureRegion, this.getX(), this.getY(), 1, 1);
    }

}
