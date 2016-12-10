/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.dames.model.pieces;

/**
 *
 * @author Jérémy Montrobert
 */
import com.ptut.dames.model.Plateau;
import com.ptut.dames.model.Piece;
import com.ptut.dames.model.Coup;

public class Pion extends Piece {

    public Pion(int x, int y, boolean isWhite) {
        super(x, y, isWhite, isWhite ? "white-pawn" : "black-pawn");
        /* Add valid moves. */
		this.deplacPossible.add(new Coup(1, 1, false));
		this.deplacPossible.add(new Coup(-1, 1, false));
                this.deplacPossible.add(new Coup(-1, -1, false));
                this.deplacPossible.add(new Coup(1, -1, false));

		
	}

	@Override
	public void moved() {
		Plateau plateau = (Plateau) this.getParent();
		int x = (int) this.getX();
		int y = (int) this.getY();

    }
}
