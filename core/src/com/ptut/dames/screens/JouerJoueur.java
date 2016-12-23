
package com.ptut.dames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.ptut.dames.Dames;
import static com.ptut.dames.screens.JouerIA.choixcouleur;

/**
 *
 * @author Maxime
 */
public class JouerJoueur implements Screen {
     private Dames app;
    Texture backg;
    SpriteBatch batch;
    private Stage stage;
    Skin skinneon;
    OrthographicCamera camera;
    static TextField nomJ1;
    static TextField nomJ2;
    Label difficulte;
    SelectBox diff;
    TextButton menu;
    TextButton play;
    static SelectBox choixcouleur;
    static SelectBox choixcouleur2;
    String[] choixTexture;
    String[] choixTexture2;
    
 
            
    public JouerJoueur(Dames app){
        this.app = app;
        backg=new Texture("backgmenu.jpg");
        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        skinneon = new Skin(Gdx.files.internal("neonui/neonui/neon-ui.json" ));
        creationBouton();
    }

    private void creationBouton(){
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        nomJ1 = new TextField("Joueur 1", skinneon);
        nomJ2= new TextField("Joueur 2", skinneon);
        difficulte = new Label("Difficulte :", skinneon);
        menu = new TextButton("<<", skinneon);
        play = new TextButton("Jouer", skinneon);
        
        choixTexture = new String[]{"Bleu", "Rouge", "Vert"};
        choixTexture2 = new String[]{"Rouge", "Bleu", "Vert"};
        
        choixcouleur = new SelectBox(skinneon);
        choixcouleur.setItems(choixTexture);
        choixcouleur2 = new SelectBox(skinneon);
        choixcouleur2.setItems(choixTexture2);
        
        table.add(nomJ1).padBottom(50);
        table.add(choixcouleur).padBottom(50);
        table.row();
        table.add(nomJ2).padBottom(50);
        table.add(choixcouleur2).padBottom(50);
        table.row();
        table.add(play).width(150).height(50).padLeft(50);
        table.row();
        
        menu.setPosition(800,500);
        stage.addActor(table);
        stage.addActor(menu);
        
         
        
         actionBouton();
    }
    
    private void actionBouton(){
       menu.addListener(new ClickListener(){
       @Override
       public void clicked(InputEvent event, float x, float y) {
            
            app.setScreen(app.firstpage);
            
       }    
  });
       
         play.addListener(new ClickListener(){
       @Override
       public void clicked(InputEvent event, float x, float y) {
           Dames.setJouercontrejoueur(true);
          app.setScreen(app.gamescreen);
           
           
         //   ScreenGame nouv = new ScreenGame(app,nomJ1.getText(),nomJ2.getText(),choixcouleur.getSelection(), choixcouleur2.getSelection());
       //     app.setScreen(nouv);
            
       }    
  });
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
            batch.draw(backg,0,0);
        batch.end();
        stage.act();
        stage.draw();
      
    }

    @Override
    public void resize(int width, int height) {
     camera.setToOrtho(false,width,height);
    }

    @Override
    public void pause() {
    
    }

    @Override
    public void resume() {
   
    }

    @Override
    public void hide() {
    
    }

    @Override
    public void dispose() {
        skinneon.dispose();
        stage.dispose();
    }
    
    public static ArraySelection getColor1(){
       return choixcouleur.getSelection();
    }
    
    public static ArraySelection getColor2(){
       return choixcouleur2.getSelection();
    }
    
    public static String getnomJ1(){
       return nomJ1.getText();
    }
    public static String getnomJ2(){
       return nomJ2.getText();
    }
}


