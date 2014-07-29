/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.makina.skystar.StatePlay;
//basic inheritable entity for all other entities
public abstract class Entity {
	
	Graphics g;
	GameContainer gcan;
	StatePlay play;
	Input i;
	Random r;
	
	Image s1;
	public Image s2;
	boolean flip;
	
	int armedcount;
	int modarmed;
	boolean armed;
	int armedstate;
	public int missiles;
	public boolean triplebullets;
	
	public int x, y;
	int speed;
	String dir;
	int worth;
	int nworth;
	int maxhp;
	public int hp;
	
	public Entity(GameContainer gcanin, StatePlay playin, int xin, int yin) throws SlickException
	{
		g = gcanin.getGraphics();
		gcan = gcanin;
		i = gcan.getInput();
		play = playin;
		r = new Random();
		
		x = xin;
		y = yin;
		flip = false;
		armed = false;
		armedstate = 0;
		speed = 4;
		worth = 0;
		nworth = 0;
		hp = 1;
	}
	
	public void render() throws SlickException
	{
		if(flip)
		{
			g.drawImage(s1, x, y);
		}else{
			g.drawImage(s2, x, y);
		}
		
		flip = !flip;
	}
	
	public void update() throws SlickException
	{
	}
	
	public Image getColImg() throws SlickException
	{
		return s1;
	}
	
	public boolean collision(Entity ent) throws SlickException
	{
		boolean collide = false;;
		
		Rectangle me = new Rectangle(x, y, s1.getWidth(), s1.getHeight());
		Rectangle him;
		
		if(!(ent instanceof City))
			him = new Rectangle(ent.x, ent.y, ent.getColImg().getWidth(), ent.getColImg().getHeight());
		else
			him = new Rectangle(ent.x + 36, ent.y + 35, ent.getColImg().getWidth(), ent.getColImg().getHeight());
		
		if(me.intersects(him))
			collide = true;
		
		return collide;
	}
}
