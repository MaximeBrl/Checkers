/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    static final Vector2 tmpCoords = new Vector2(), tmpCoords2 = new Vector2();

	
	InputEvent event;
	Actor actor, touchDownTarget;
   
    private final Plateau plateau;
    private final Array<Tile> highlightedTiles = new Array<Tile>();
    private GameRenderer view = null;

    public ControlPlateau(Plateau plateau) {

        this.plateau = plateau;
    }

   
    

        
        private void movePiece(Piece piece, int x, int y) {
        /* Check move validity. */
        if ((piece == null) || !this.plateau.getTilePos(x, y).isHighlighted) {
            return;
        }

        int xOld = (int) piece.getX();
        int yOld = (int) piece.getY();

        /* Remove highlights. */
        this.removeMoveHighlights();

        /* Capture. */
        if (this.plateau.getPiecePos(x, y) != null) {
            this.plateau.removePieceAt(x, y);
        }

        /* Move. */
        this.plateau.relocatePieceAt(xOld, yOld, x, y);
        this.plateau.selectedPiece.moved();

        /* Deselect and advance round. */
        this.plateau.selectedPiece = null;
        this.plateau.round++;

    }

    private void selectPiece(Piece piece) {
        this.removeMoveHighlights();
        this.plateau.selectedPiece = piece;
        this.addMoveHighlightsForPiece(piece);
    }

    private void addMoveHighlightsForPiece(Piece piece) {
        Array<Tile> tiles = piece.getDeplacPossible(this.plateau, true);

        tiles.addAll(piece.getTilesManger(this.plateau, true));

        for (Tile tile : tiles) {
            int tx = (int) tile.getX();
            int ty = (int) tile.getY();

        }
    }

    private void removeMoveHighlights() {

        while (this.highlightedTiles.size > 0) {
            this.highlightedTiles.pop().isHighlighted = false;
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
         Actor target = event.getTarget();
        int tx = (int) target.getX(); // Tapped tile x.
        int ty = (int) target.getY(); // Tapped tile y.
        System.out.println("touch");
        System.out.println(tx+" "+ty);
        if (target.getClass().getSuperclass().equals(Piece.class
        )) {System.out.println("piece");
            Piece piece = (Piece) target;
            
            if ((((this.plateau.round % 2) == 0) && piece.estBlanc)
                    || (((this.plateau.round % 2) == 1) && !piece.estBlanc)) {
                this.selectPiece(piece);
            } else {
                this.movePiece(this.plateau.selectedPiece, tx, ty);
            }
        } else {
            this.movePiece(this.plateau.selectedPiece, tx, ty);
        }
        return true;
        
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
        
    }

}
