/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar.entity;

import net.arsmachina.skystar.StatePlay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//the player's basic projectile
public class Bullet extends Entity {

	public Bullet(GameContainer gcan, StatePlay playin, int xin, int yin, String dirin) throws SlickException
	{
		super(gcan, playin, xin, yin);
		
		s1 = new Image("res/sprites/projectiles/bullet.png");
		s2 = new Image("res/sprites/projectiles/bullet_a.png");
		dir = dirin;
		
		speed = 12;
	}
	
	public void update() throws SlickException
	{
		if(dir.equals("right"))
			x += speed / 2;
		
		if(dir.equals("left"))
			x -= speed / 2;
		
		y -=speed;
		
		for(int i = 0;i < 5;i++)
		{
			for(int k = 0;k < 4;k++)
			{
				if(((City) play.tile[i][k]).isTower() && collision(play.tile[i][k]))
					play.removeProjectile(this);
			}
		}
		
		if(y <= -10)
			play.removeProjectile(this);
	}
}
