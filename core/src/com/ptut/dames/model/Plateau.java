
package com.ptut.dames.model;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.Dames;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ptut.dames.model.pieces.Pion;
import com.ptut.dames.screens.GameScreen;
import com.ptut.dames.screens.JouerIA;

/**
 *
 * @author Jérémy Montrobert
 */
public class Plateau extends Table {

    public Piece pieceSelect;
    public int tour;

    /* Pointeurs vers les tiles pour accès plus facile */
    private final Tile[][] tiles = new Tile[10][10];

    /* Pointeurs vers les pieces pour accès plus facile */
    public static final Piece[][] pieces = new Piece[10][10];

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

    public void ajoutPions() {
        /* Ajout des pions */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j += 2) {
                if (i % 2 == 0) {
                    this.ajoutPieces(new Pion(i, j, true, (String) JouerIA.getColor1().first()));

                } else {
                    this.ajoutPieces(new Pion(i, j+1, true, (String) JouerIA.getColor1().first()));
                }
            }

        }

        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 10; j += 2) {
                if (i % 2 == 0) {
                    this.ajoutPieces(new Pion(i, j, false, (String) JouerIA.getColor2().first()));
                } else {
                    this.ajoutPieces(new Pion(i, j + 1, false, (String) JouerIA.getColor2().first()));
                }
            }

        }
    }

    public void ajoutPieces(Piece piece) {
        this.addActor(piece);
        this.pieces[(int) piece.getX()][(int) piece.getY()] = piece;
    }

    public void repositionnerPiece(int xOld, int yOld, int x, int y) {
        Piece piece = this.pieces[xOld][yOld];

        this.pieces[x][y] = piece;
        this.pieces[xOld][yOld] = null;
        piece.setX(x);
        piece.setY(y);
    }

    /**
     * Enlève la pièce à la position de la tile donnée
     *
     * @param x position horizontale de la tile
     * @param y position verticale de la tile
     */
    public void enleverPiece(int x, int y) {
        Piece piece = this.pieces[x][y];

        if (piece != null) {
            piece.remove();
            this.pieces[x][y] = null;
        }
    }

    /**
     * Enlève toutes les pièces du Plateau
     */
    public void enleverTout() {

        for (short x = 0; x < 8; x++) {

            for (short y = 0; y < 8; y++) {
                this.pieces[x][y].remove();
                this.pieces[x][y] = null;
            }
        }
    }

}
