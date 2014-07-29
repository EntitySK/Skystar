/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import com.makina.skystar.StatePlay;
//the player's missile
public class Missile extends Entity {
	
	public Missile(GameContainer gcan, StatePlay playin, int xin, int yin) throws SlickException
	{
		super(gcan, playin, xin, yin);
		
		s1 = new Image("res/sprites/projectiles/missile_a.png");
		s2 = new Image("res/sprites/projectiles/missile_b.png");
		
		speed = 16;
	}
	
	public void update() throws SlickException
	{
		y -= speed;
		
		if(y <= -20)
			play.removeProjectile(this);
	}
	
	public void activate() throws SlickException
	{
		for(int i = 0; i < play.enemies.size();i++)
			if(collision(play.enemies.get(i)))
				play.damageEnemy(play.enemies.get(i), 50);
	}
}