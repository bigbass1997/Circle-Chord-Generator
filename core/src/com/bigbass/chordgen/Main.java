package com.bigbass.chordgen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bigbass.chordgen.fonts.FontManager;
import com.bigbass.chordgen.panel.PanelGroup;
import com.bigbass.chordgen.panel.PanelPrimary;

public class Main extends ApplicationAdapter {

	private PanelPrimary primaryPanel;
	private PanelGroup panels;
	
	@Override
	public void create () {
		FontManager.addFont("fonts/computer.ttf", new int[]{16,24}); //Added font to be used with Debug Text
		
		panels = new PanelGroup();
		
		primaryPanel = new PanelPrimary();
		panels.panels.add(primaryPanel);
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		panels.render();
		
		//UPDATE
		update();
	}
	
	private void update(){
		float delta = Gdx.graphics.getDeltaTime();
		
		panels.update(delta);
	}
	
	@Override
	public void dispose(){
		panels.dispose();
	}
}
