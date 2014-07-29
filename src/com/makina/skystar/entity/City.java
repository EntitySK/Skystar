/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.makina.skystar.StatePlay;
import com.makina.skystar.Timer;
//one tile in the moving background
public class City extends Entity
{
	double coef;
	int dt;
	
	boolean tower;
	
	public static Image[] tiles = new Image[8];
	{
			tiles[0] = new Image("res/tiles/buildings.png");
			tiles[1] = new Image("res/tiles/complex.png");
			tiles[2] = new Image("res/tiles/factory.png");
			tiles[3] = new Image("res/tiles/lab.png");
			tiles[4] = new Image("res/tiles/office.png");
			tiles[5] = new Image("res/tiles/park.png");
			tiles[6] = new Image("res/tiles/plaza.png");
			tiles[7] = new Image("res/tiles/shop.png");
	}
	
	public City(GameContainer gcan, StatePlay playin, int x, int y) throws SlickException
	{
		super(gcan, playin, x, y);
		r = new Random();
		coef = 1.009;
		dt = 0;
		tower = false;
		s1 = tiles[r.nextInt(8)];
	}
	
	public void render()
	{
		g.drawImage(s1, x, y);
	}
	
	public void update()
	{
		if(y > 680)
		{
			y = -170;
			
			int t = r.nextInt(8);
			if(t == 4)
				tower = true;
			else
				tower = false;
			s1 = tiles[t];
		}
		
		if(Timer.delta < 190)
			dt = Timer.delta;
		
		y+= (int) Math.pow(coef, dt) + 1;
	}
	
	public void postRender() throws SlickException
	{
		if(tower)
			g.drawImage(new Image("res/sprites/tower.png"), x + 36, y + 35);
	}
	
	public Image getColImg() throws SlickException
	{	
		return new Image("res/sprites/tower.png");
	}
	
	public boolean isTower()
	{
		return tower;
	}
}
