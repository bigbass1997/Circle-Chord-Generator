package com.bigbass.chordgen.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bigbass.chordgen.objects.CircleGen;
import com.bigbass.chordgen.skins.SkinManager;

public class PanelPrimary extends Panel {
	
	private Camera cam;
	private Stage stage;
	private ShapeRenderer sr;
	
	private Label infoLabel;

	private float scalar = 1f;
	
	private CircleGen circ;
	
	public PanelPrimary() {
		super();
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth() * scalar, Gdx.graphics.getHeight() * scalar);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		infoLabel = new Label("", SkinManager.getSkin("fonts/computer.ttf", 24));
		stage.addActor(infoLabel);
		
		sr = new ShapeRenderer(50000);
		sr.setAutoShapeType(true);
		sr.setProjectionMatrix(cam.combined);
		
		circ = new CircleGen(cam.viewportWidth / 2, cam.viewportHeight / 2, (Math.min(cam.viewportWidth, cam.viewportHeight) / 2) - 5);
	}
	
	public void render() {
		stage.draw();
		
		panelGroup.render();
		
		sr.begin(ShapeType.Line);
		/*sr.setColor(Color.RED);
		for(Panel panel : panelGroup.panels){
			if(panel.isVisible()){
				sr.rect(panel.pos.x, panel.pos.y, panel.dim.x, panel.dim.y);
			}
		}*/
		circ.render(sr);
		sr.end();
	}
	
	public void update(float delta) {
		String n = "\n";
		String info = 
				"Data:" + n +
				"  FPS: " + Gdx.graphics.getFramesPerSecond();
		
		infoLabel.setText(info);
		infoLabel.setPosition(10, Gdx.graphics.getHeight() - (infoLabel.getPrefHeight() / 2) - 5);
		
		stage.act(delta);
		
		panelGroup.update(delta);
		
		circ.update(delta);
	}
	
	public boolean isActive() {
		return true; // Always active
	}
	
	public void dispose(){
		stage.dispose();
		sr.dispose();
		panelGroup.dispose();
	}
}