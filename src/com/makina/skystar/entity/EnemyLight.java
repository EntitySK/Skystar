/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.makina.skystar.StatePlay;
import com.makina.skystar.Util;
//alien probe
public class EnemyLight extends Entity {

	public EnemyLight(GameContainer gcanin, StatePlay playin, int xin, int yin) throws SlickException
	{
		super(gcanin, playin, xin, yin);
		
		s1 = new Image("res/sprites/enemy/qorb_a.png");
		s2 = new Image("res/sprites/enemy/qorb_b.png");
		
		speed = 8;
		worth = 2;
		nworth = 1;
		hp = 1;
	}
	
	public void update() throws SlickException
	{
		y += speed;
		
		if(play.player != null && collision(play.player))
		{
			play.spawnEffect(new Explosion(gcan, play, x, y));
			play.killPlayer();
			Util.exp.play();
			play.damageEnemy(this, hp);
		}
		
		for(int i = 0;i < play.projectiles.size();i++)
		{
			if((play.projectiles.get(i) instanceof Bullet || play.projectiles.get(i) instanceof Missile) && collision(play.projectiles.get(i)))
			{
				play.spawnEffect(new Explosion(gcan, play, x, y));
				play.score += worth;
				play.removeProjectile(play.projectiles.get(i));
				Util.exp.play();
				play.damageEnemy(this, 1);
			}
		}
		
		if(y >= 680)
		{
			if(play.player != null)
				play.score -= nworth;
			play.damageEnemy(this, hp);
		}
	}
}
