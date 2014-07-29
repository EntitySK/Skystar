/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.makina.skystar.StatePlay;
//the alien projectile
public class Orb extends Entity {

	public Orb(GameContainer gcan, StatePlay playin, int xin, int yin, String dirin) throws SlickException
	{
		super(gcan, playin, xin, yin);
		
		s1 = new Image("res/sprites/projectiles/orb.png");
		s2 = new Image("res/sprites/projectiles/orb.png");
		dir = dirin;
		
		speed = 12;
	}
	
	public void update() throws SlickException
	{
		if(dir.equals("right"))
			x += speed / 2;
		
		if(dir.equals("left"))
			x -= speed / 2;
		
		y += speed;
		
		for(int i = 0;i < 5;i++)
		{
			for(int k = 0;k < 4;k++)
			{
				if(((City) play.tile[i][k]).isTower() && collision(play.tile[i][k]))
					play.removeProjectile(this);
			}
		}
		
		if(y >= 690)
			play.removeProjectile(this);
	}
}
