
package com.ptut.dames.model.pieces;

/**
 *
 * @author Jérémy Montrobert
 */
import com.ptut.dames.model.Plateau;
import com.ptut.dames.model.Piece;
import com.ptut.dames.model.Coup;

public class Pion extends Piece {

    public Pion(int x, int y, boolean estBlanc, String couleur) {
       
        super(x, y, estBlanc, couleur);
        
        /* ajout des déplacements possibles */
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
