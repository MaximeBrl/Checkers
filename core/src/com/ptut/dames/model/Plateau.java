/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.model;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.Dames;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ptut.dames.model.pieces.Pion;

/**
 *
 * @author Jérémy Montrobert
 */
public class Plateau extends Table {

    public Piece selectedPiece;
    public int round;

    /* Pointeurs vers les tiles pour accès plus facile */
    private final Tile[][] tiles = new Tile[10][10];

    /* Pointeurs vers les pieces pour accès plus facile */
    private final Piece[][] pieces = new Piece[10][10];

    /*  Getters  */
    public Tile getTilePos(int x, int y) {
        return this.tiles[x][y];

    }

    public Piece getPiecePos(int x, int y) {
        return this.pieces[x][y];

    }

    /* Crée un plateau vide */
    public Plateau() {

        this.setBounds(0, 0, Dames.UWIDTH, Dames.UWIDTH);
        this.setClip(true);


        /* Ajout des tiles */
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                this.tiles[i][j] = new Tile(i, j, ((i + j) % 2) == 0);
                this.addActor(this.tiles[i][j]);
            }
        }
    }

    public void populate() {
        /* Ajout des pions */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j += 2) {
                if (i % 2 == 0) {
                    this.addPiece(new Pion(i, j, true));

                } else {
                    this.addPiece(new Pion(i, j + 1, true));
                }
            }

        }

        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 10; j += 2) {
                if (i % 2 == 0) {
                    this.addPiece(new Pion(i, j, false));
                } else {
                    this.addPiece(new Pion(i, j + 1, false));
                }
            }

        }
    }

    public void addPiece(Piece piece) {
        this.addActor(piece);
        this.pieces[(int) piece.getX()][(int) piece.getY()] = piece;
    }

    public void relocatePieceAt(int xOld, int yOld, int x, int y) {
        Piece piece = this.pieces[xOld][yOld];

        this.pieces[x][y] = piece;
        this.pieces[xOld][yOld] = null;
        piece.setX(x);
        piece.setY(y);
    }

    /**
     * Removes a piece that is on a given tile location.
     *
     * @param x Horizontal index of the tile.
     * @param y Vertical index of the tile.
     */
    public void removePieceAt(int x, int y) {
        Piece piece = this.pieces[x][y];

        if (piece != null) {
            piece.remove();
            this.pieces[x][y] = null;
        }
    }

    /**
     * Removes all pieces from the <code>Board<code>.
     */
    public void removeAll() {

        for (short x = 0; x < 8; x++) {

            for (short y = 0; y < 8; y++) {
                this.pieces[x][y].remove();
                this.pieces[x][y] = null;
            }
        }
    }

}
