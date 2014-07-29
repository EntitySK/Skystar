/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.makina.skystar.StatePlay;
import com.makina.skystar.Timer;
//effects entity for after-unit explosions
public class Explosion extends Entity
{
	private Image s3;
	private int dt;
	
	public Explosion(GameContainer gcanin, StatePlay playin, int xin, int yin) throws SlickException
	{
		super(gcanin, playin, xin, yin);
		
		dt = Timer.delta;;
		
		s1 = new Image("res/sprites/exp1.png");
		s2 = new Image("res/sprites/exp2.png");
		s3 = new Image("res/sprites/exp3.png");
	}
	
	public void render() throws SlickException
	{
		if(Timer.delta == dt + 1)
		{
			g.drawImage(s1, x, y);
		}
		if(Timer.delta == dt + 2)
			g.drawImage(s2, x, y);
		if(Timer.delta == dt + 3)
			g.drawImage(s3, x, y);
	}
	
	public void update() throws SlickException
	{
		if(Timer.delta == dt + 4)
			play.removeEffect(this);
	}
}
