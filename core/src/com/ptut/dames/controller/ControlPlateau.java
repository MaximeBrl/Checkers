package com.ptut.dames.controller;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.model.Piece;
import com.ptut.dames.model.Tile;
import com.ptut.dames.model.pieces.Pion;
import com.ptut.dames.views.GameRenderer;

public class ControlPlateau extends ActorGestureListener implements GestureListener {

    private final Plateau plateau;
    private final Array<Tile> tileEnSurbrillance = new Array<Tile>();
    private GameRenderer view = null;
    public InputEvent event;

    public ControlPlateau(Plateau plateau) {

        this.plateau = plateau;
    }

    private void deplacerPiece(Piece piece, int x, int y) {
        /* vérifier validitée du déplacement */
        if ((piece == null) || !this.plateau.getTilePos(x, y).estEnSurbrillance) {
            return;
        }

        int xOld = (int) piece.getX();
        int yOld = (int) piece.getY();

        /* enlever la surbrillance */
        this.enleverSurbrillanceDeplacement();

        /* Pièce mangée */
        if (this.plateau.getPiecePos(x, y) != null) {
            this.plateau.enleverPiece(x, y);
        }

        /* Pièce déplacée */
        this.plateau.repositionnerPiece(xOld, yOld, x, y);
        this.plateau.pieceSelect.moved();

        /* désectionner piece et passer au tour suivant */
        this.plateau.pieceSelect = null;
        this.plateau.tour++;

    }

    private void selectPiece(Piece piece) {
        this.enleverSurbrillanceDeplacement();
        this.plateau.pieceSelect = piece;
        this.ajouterSurbrillanceDeplacement(piece);
    }

    private void ajouterSurbrillanceDeplacement(Piece piece) {
        Array<Tile> tiles = piece.getDeplacPossible(this.plateau, true);

        tiles.addAll(piece.getTilesManger(this.plateau, true));

        for (Tile tile : tiles) {
            int tx = (int) tile.getX();
            int ty = (int) tile.getY();

        }
    }

    private void enleverSurbrillanceDeplacement() {

        while (this.tileEnSurbrillance.size > 0) {
            this.tileEnSurbrillance.pop().estEnSurbrillance = false;
        }
    }

    public void addView(GameRenderer view) {
        this.view = view;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("test22222");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        System.out.println("test44444");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        System.out.println("test55555");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        System.out.println("test66666");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        System.out.println("test77777");
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        System.out.println("test8888888");
        return false;
    }

    @Override
    public void pinchStop() {
        System.out.println("test9999999");

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Actor cible = event.getTarget();
        int tx = (int) cible.getX(); // pos x tile cliquée
        int ty = (int) cible.getY(); // pos y tile cliquée
        System.out.println("touch");
        System.out.println(tx + " " + ty);
        
        //si la cible est une Piece
        if (cible.getClass().getSuperclass().equals(Piece.class)) { 
            System.out.println("piece");
            Piece piece = (Piece) cible; // on transforme l'acteur en Pièce

            if ((((this.plateau.tour % 2) == 0) && piece.estBlanc)
                    || (((this.plateau.tour % 2) == 1) && !piece.estBlanc)) {
                this.selectPiece(piece);
            } else {
                this.deplacerPiece(this.plateau.pieceSelect, tx, ty);
            }
        } else {
            this.deplacerPiece(this.plateau.pieceSelect, tx, ty);
        }
        return true;

    }

    @Override
    public boolean longPress(float x, float y) {
        return false;

    }

}
