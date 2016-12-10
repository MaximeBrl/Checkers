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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.model.Plateau;
import com.ptut.dames.model.Piece;
import com.ptut.dames.model.Tile;

public class ControlPlateau extends ActorGestureListener {

    private final Plateau plateau;
    private final Array<Tile> highlightedTiles = new Array<Tile>();

    public ControlPlateau(Plateau plateau) {

        this.plateau = plateau;
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        Actor target = event.getTarget(); // Tapped actor.
        int tx = (int) target.getX(); // Tapped tile x.
        int ty = (int) target.getY(); // Tapped tile y.

        if (target.getClass().getSuperclass().equals(Piece.class
        )) {
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
}
