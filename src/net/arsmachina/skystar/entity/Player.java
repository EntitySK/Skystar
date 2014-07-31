/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar.entity;

import net.arsmachina.skystar.StatePlay;
import net.arsmachina.skystar.Util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
//the player's ship
public class Player extends Entity {
	
	private boolean missileflip;
	
	public Player(GameContainer gcan, StatePlay playin, int x, int y, String jet) throws SlickException
	{
		super(gcan, playin, x, y);
		
		s1 = new Image("res/sprites/player/a6_a.png");
		s2 = new Image("res/sprites/player/a6_b.png");
		
		triplebullets = false;;
		missiles = 0;
		missileflip = false;
		modarmed = 5;
		speed = 12;
		maxhp = 6;
		hp = maxhp;
	}
	
	public void update() throws SlickException
	{
		s2 = new Image("res/sprites/player/a6_b.png");
		
		for(int i = 0;i < 5;i++)
		{
			for(int k = 0;k < 4;k++)
			{
				if(((City) play.tile[i][k]).isTower() && collision(play.tile[i][k]))
				{
					play.spawnEffect(new Explosion(gcan, play, x, y));
					play.killPlayer();
				}
			}
		}
		
		for(int i = 0;i < play.projectiles.size(); i++)
		{
			if(play.projectiles.get(i) instanceof Orb && collision(play.projectiles.get(i)))
			{
				play.spawnEffect(new Explosion(gcan, play, x, y));
				play.removeProjectile(play.projectiles.get(i));
				play.killPlayer();
			}
		}
		
		for(int i = 0;i < play.powerups.size(); i++)
		{
			if(play.powerups.get(i).getType().equals("hp") && collision(play.powerups.get(i)))
			{
				Util.powerup.play();
				if(hp < maxhp)
					hp = maxhp;
				else
					hp = 2 * maxhp;
				play.removePowerup(play.powerups.get(i));
			}
		}
		
		for(int i = 0;i < play.powerups.size(); i++)
		{
			if(play.powerups.get(i).getType().equals("missile") && collision(play.powerups.get(i)))
			{
				missiles++;
				Util.powerup.play();
				play.removePowerup(play.powerups.get(i));
			}
		}
		
		for(int i = 0;i < play.powerups.size(); i++)
		{
			if(play.powerups.get(i).getType().equals("triple") && collision(play.powerups.get(i)))
			{
				Util.powerup.play();
				triplebullets = true;
				play.removePowerup(play.powerups.get(i));
			}
		}
		
		if(armedcount % modarmed == 1)
			armed = true;
		else
			armed = false;
		armedcount++;
		if(armedcount >= Integer.MAX_VALUE -1)
			armedcount = 0;
		
		if(i.isKeyDown(Input.KEY_D) && x < 632)
			x += speed;
		if(i.isKeyDown(Input.KEY_A) && x > 0)
			x -= speed;
		if(i.isKeyDown(Input.KEY_S) && y < 632)
			y += speed;
		if(i.isKeyDown(Input.KEY_W) && y > 0)
			y -= speed;
		
		if(!triplebullets && i.isKeyDown(Input.KEY_SPACE) && armed)
		{
			Util.shoot.play();
			play.spawnProjectile(new Bullet(gcan, play, x + 23, y, ""));
		}
		
		if(triplebullets && i.isKeyDown(Input.KEY_SPACE) && armed)
		{
			Util.shoot.play();
			play.spawnProjectile(new Bullet(gcan, play, x + 23, y, "left"));
			Util.shoot.play();
			play.spawnProjectile(new Bullet(gcan, play, x + 23, y, ""));
			Util.shoot.play();
			play.spawnProjectile(new Bullet(gcan, play, x + 23, y, "right"));
		}
		
		if(i.isKeyPressed(Input.KEY_E) && missiles > 0)
			{
				missiles--;
				Util.missile.play();
				if(missileflip)
					play.spawnProjectile(new Missile(gcan, play, x + 7, y + 20));
				else
					play.spawnProjectile(new Missile(gcan, play, x + 36, y + 20));
				
				missileflip = !missileflip;
			}
	}
}
