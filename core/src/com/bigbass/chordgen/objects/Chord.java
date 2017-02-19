package com.bigbass.chordgen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Chord {
	
	public Endpoint ep1;
	public Endpoint ep2;
	
	public float thickness;
	
	public float rot;
	
	public Chord(float x1, float y1, float x2, float y2, float thickness, int c1, int c2, float rot){
		ep1 = new Endpoint(x1, y1, new Color(c1));
		ep2 = new Endpoint(x2, y2, new Color(c2));
		
		this.thickness = thickness;
		
		this.rot = rot;
	}
	
	public Chord(float x1, float y1, float x2, float y2){
		this(x1, y1, x2, y2, 1f, 0xFFFFFFFF, 0xFFFFFFFF, 0);
	}
	
	public Chord(){
		this(0, 0, 0, 0, 1, 0xFFFFFFFF, 0xFFFFFFFF, 0);
	}
	
	public void render(ShapeRenderer sr, float offX, float offY){
		sr.rectLine(ep1.x + offX, ep1.y + offY, ep2.x + offX, ep2.y + offY, thickness, ep1.c, ep2.c);
	}
}
