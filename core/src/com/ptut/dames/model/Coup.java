
package com.ptut.dames.model;

/**
 *
 * @author Jérémy Montrobert
 */
public class Coup {

	public int xOffset;
	public int yOffset;
	public boolean continuous;

	/**
	 *Créer un coup. Les coup sont créé en fonction des pièces Blanche,
         * pour une pièce noire le coup sera donc inversé automatiquement.
	 * 
	 * @param decalageX
	 *            Décalage tile horizontal
	 * @param decalageY
	 *            Décalge tile vertiale
	 * @param continu
	 *            Si continu à True alors le déplacement sera interprété par
         *            un vecteur Vector2() qui permettra de faire un déplacement continu 
         *            ex: pour les dames.
	 */
	public Coup(int decalageX, int decalageY, boolean continu) {
		this.xOffset = decalageX;
		this.yOffset = decalageY;
		this.continuous = continu;
	}
}
