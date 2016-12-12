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

    private float Cx = 0;
    private float Cy = 0;
    private final Plateau plateau;
    private final Array<Tile> tileEnSurbrillance = new Array<Tile>();
    private GameRenderer view = null;

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

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean touchDown(float Cx, float Cy, int pointer, int button) {
        this.Cx = Cx;
        this.Cy = Cy;

        System.out.println(Cx + "    " + Cy);
        this.ConvertCoord();
        return true;
    }

    public float ConvertCoord() {
        int i = 0;
        int x = 0;
        int y = 0;
        int Ox = 150;
        int Oy = 599;
        while (i <= 9) {
            if (Cx > Ox + i * 59) {
                x = i;
            }
            if (Cy < Oy - i * 59) {
                y = i;
            }
            i++;
        }
        System.out.println(x + "    " + y);
        return Cx+Cy;
        
    }

    /*Actor cible = event.getTarget(); // recupere la cible qui à recu l'evenement: celle cliquée
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

    }*/

    @Override
    public boolean longPress(float x, float y) {
        return false;

    }

    /* -on recupere des coordonnées px, getter
       -transformer les coordonées px en coordonée entiere pour chaque case
       --> largeur d'une case = hauteur d'une case
            clic(cx,cy)--> coordonées en px cliquée; o(ox,oy) 
            (cx-ox, cy-oy)
            (cx-ox) / largeur (division entière)
    
       
       -detecter case cliquée, récupérer coordonées
       -faire le déplacementsur sur la case cliquée   
     */
    public void ConvertCoord(float x, float y) {

    }

}
