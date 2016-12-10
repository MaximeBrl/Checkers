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
public class Coup {

	public int xOffset;
	public int yOffset;
	public boolean continuous;

	/**
	 * Creates a move. When creating a new <code>Move</code> instance, keep in
	 * mind that they are created according to white chess pieces. Y coordinate
	 * inversion for black pieces is done automatically.
	 * 
	 * @param xOffset
	 *            Horizontal tile offset.
	 * @param yOffset
	 *            Vertical tile offset.
	 * @param continuous
	 *            If true, than xOffset and yOffset are interpreted as unit
	 *            vector components which show a direction that can be moved on
	 *            continuously.
	 */
	public Coup(int xOffset, int yOffset, boolean continuous) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.continuous = continuous;
	}
}
