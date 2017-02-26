package com.bigbass.chordgen.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class CircleGen {
	
	private float x;
	private float y;
	private float radius;

	private ArrayList<Chord> chords;
	
	private float ticks;
	
	private Random rand;
	
	private final int iters = 1;
	private final int wait = 2;
	
	private final float thickness = 15;
	private final int maxChords = 100000;
	
	public CircleGen(float x, float y, float radius){
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		chords = new ArrayList<Chord>();
		chords.add(new Chord(0, radius, 0, radius));
		
		ticks = 0;
		
		rand = new Random();
	}
	
	public void render(ShapeRenderer sr){
		sr.set(ShapeType.Filled);
		for(Chord chord : chords){
			chord.render(sr, x, y);
		}
		
		sr.set(ShapeType.Line);
		for(int i = -1; i < 3; i++){
			sr.setColor(Color.WHITE);
			sr.circle(x, y, radius + i, 300);
		}
		for(int i = 3; i < 8; i++){
			sr.setColor(Color.BLACK);
			sr.circle(x, y, radius + i, 300);
		}
	}
	
	public void update(float delta){
		ticks += 60 * delta;
		
		if(ticks > wait){
			for(int i = 0; i < iters; i++){
				int tmpColor = Color.rgba8888(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1.0f);
				Chord prev = chords.get(chords.size() - 1);
				
				float rot = (float) (prev.rot + Math.abs(rand.nextFloat() * 2 * Math.PI));
				if(Math.floor(rot) > 2 * Math.PI){
					rot %= 2 * Math.PI;
				}
				
				chords.add(new Chord(
						prev.ep2.x,
						prev.ep2.y,
						(float) (Math.cos(rot) * radius),
						(float) (Math.sin(rot) * radius),
						thickness,
						Color.rgba8888(prev.ep2.c),
						tmpColor,
						rot
						));
				
				if(chords.size() > maxChords){
					chords.remove(0);
				}
			}
			ticks = 0;
		}
		
		for(int i = chords.size() - 1; i > 0; i--){
			Chord chord = chords.get(i);
			chord.update(delta);
			if(chord.thickness < 0){
				chords.remove(i);
			}
		}
	}
}
