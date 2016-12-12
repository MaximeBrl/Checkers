package com.ptut.dames.model;

/**
 *
 * @author Jérémy Montrobert
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.ptut.dames.Assets;

public class Piece extends Actor {

    public boolean estBlanc;
    public boolean peutManger = true; // est ce que cette piece peut manger avec un coup?

    /**
     * Tableau deplacements valide
     */
    protected Array<Coup> deplacPossible = new Array<Coup>();

    /**
     * Tableau de coups valide pour manger
     */
    protected Array<Coup> coupManger = new Array<Coup>();
    private final TextureRegion textureRegion;

    public Piece(int y, int x, boolean estBlanc, String regionName) {

        this.setBounds(x, y, 1, 1);
        this.estBlanc = estBlanc;
        this.textureRegion = Assets.gameAtlas.findRegion(regionName);

        /*addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Piece piece = (Piece) event.getListenerActor();
                System.out.println("tes");
                int tx = (int) piece.getX(); // pos X de la pièce tapée
                int ty = (int) piece.getY(); // pos y de la pièce tapée
                System.out.println(tx +" "+ ty);
        
    


                return true;
            }

        });*/
    }

    public Array<Tile> getDeplacPossible(Plateau plateau, boolean checkFriendly) {
        Array<Tile> tiles = new Array<Tile>();
        int x = (int) this.getX();
        int y = (int) this.getY();

        for (Coup coup : this.deplacPossible) {
            boolean boucle = true;

            for (int i = 1; boucle; i++) {
                int tx = x + (coup.xOffset * i); // pos x tile .
                int ty = y + ((this.estBlanc ? coup.yOffset : -coup.yOffset) * i); // pos y tile

                if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
                    Tile tile = plateau.getTilePos(tx, ty);
                    Piece autrepiece = plateau.getPiecePos(tx, ty);

                    if (autrepiece != null) {

                        if ((!checkFriendly || (autrepiece.estBlanc != this.estBlanc))
                                && this.peutManger) {
                            tiles.add(tile);
                        }
                        boucle = false;
                    } else {
                        tiles.add(tile);
                    }
                } else {
                    boucle = false;
                }

                if (!coup.continuous) {
                    boucle = false;
                }
            }
        }
        return tiles;
    }

    /**
     * Retourne les tile sur la Plateau qui sont accessible par cette
	 * <code>Piece<code>  en fonction de son tableau <code>coupManger</code>
     *
     *
     * @param plateau instance du plateau
     * @param verif verifie la validitée du coup pour manger avant d'ajouter la
     * tile à la liste
     * @return tableau des tile
     */
    public Array<Tile> getTilesManger(Plateau plateau, boolean verif) {
        Array<Tile> tiles = new Array<Tile>();
        int x = (int) this.getX();
        int y = (int) this.getY();

        for (Coup coup : this.coupManger) {
            int tx = x + coup.xOffset; // pos x tile
            int ty = y + (this.estBlanc ? coup.yOffset : -coup.yOffset); // pos y tile
            if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
                Tile tile = plateau.getTilePos(tx, ty);
                Piece autrepiece = plateau.getPiecePos(tx, ty);

                if (!verif
                        || ((autrepiece != null) && (autrepiece.estBlanc != this.estBlanc))) {
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }

    public void moved() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(this.textureRegion, this.getX(), this.getY(), 1, 1);
    }

}
