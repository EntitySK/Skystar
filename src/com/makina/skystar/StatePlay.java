/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.makina.skystar.gui.*;
import com.makina.skystar.entity.*;
//the play screen
public class StatePlay extends BasicGameState
{
	Thread timer;
	Director d;
	Random r;
	
	GameContainer localgc;
	
	Music background;
	boolean musicstarted;
	
	private GuiObject a;
	
	public Entity[][] tile = new Entity[5][4];
	public Entity player;
	public ArrayList<Entity> projectiles;
	public ArrayList<Powerup> powerups;
	public ArrayList<Entity> enemies;
	public ArrayList<Entity> effects;
	
	private int cx, cy;
	
	public int score;
	
	public StatePlay(int sn)
	{
	}
	//for Slick2d, init serves as the constructor
	public void init(GameContainer gcan, StateBasedGame state) throws SlickException
	{
		timer = new Thread(new Timer());
		timer.start();
		
		localgc = gcan;
		r = new Random();
		
		a = new ButtonToggle(gcan, 0, 616, new Image("res/gui/abort.png"), new Image("res/gui/abort_a.png"));
		
		tile[0][0] = new City(gcan, this, 0, -170);
		tile[0][1] = new City(gcan, this, 170, -170);
		tile[0][2] = new City(gcan, this, 340, -170);
		tile[0][3] = new City(gcan, this, 510, -170);
		tile[1][0] = new City(gcan, this, 0, 0);
		tile[1][1] = new City(gcan, this, 170, 0);
		tile[1][2] = new City(gcan, this, 340, 0);
		tile[1][3] = new City(gcan, this, 510, 0);
		tile[2][0] = new City(gcan, this, 0, 170);
		tile[2][1] = new City(gcan, this, 170, 170);
		tile[2][2] = new City(gcan, this, 340, 170);
		tile[2][3] = new City(gcan, this, 510, 170);
		tile[3][0] = new City(gcan, this, 0, 340);
		tile[3][1] = new City(gcan, this, 170, 340);
		tile[3][2] = new City(gcan, this, 340, 340);
		tile[3][3] = new City(gcan, this, 510, 340);
		tile[4][0] = new City(gcan, this, 0, 510);
		tile[4][1] = new City(gcan, this, 170, 510);
		tile[4][2] = new City(gcan, this, 340, 510);
		tile[4][3] = new City(gcan, this, 510, 510);
		
		player = new Player(gcan, this, 316, 542, "");
		
		projectiles = new ArrayList<Entity>();
		powerups = new ArrayList<Powerup>();
		enemies = new ArrayList<Entity>();
		effects = new ArrayList<Entity>();
		
		d = new Director(gcan, this);
		
		background = new Music("res/sound/background.wav");
		musicstarted = false;
		
		score = 0;
	}
	//this handles anything being rendered on-screen
	public void render(GameContainer gcan, StateBasedGame state, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		
		for(int i = 0;i < 5;i++)
		{
			for(int k = 0;k < 4;k++)
				tile[i][k].render();
		}
		
		g.drawImage(new Image("res/clouds.png"), cx, cy);
		g.drawImage(new Image("res/clouds.png"), cx - 1360, cy);
		g.drawImage(new Image("res/clouds.png"), cx, cy - 1360);
		g.drawImage(new Image("res/clouds.png"), cx - 1360, cy - 1360);
		
		for(int i = 0;i < projectiles.size(); i++)
			if(!(projectiles.get(i) instanceof Missile))
				projectiles.get(i).render();
		
		if(player != null)
			player.render();
		
		for(int i = 0;i < 5;i++)
			for(int k = 0;k < 4;k++)
				((City) tile[i][k]).postRender();
		
		for(int i = 0;i < projectiles.size(); i++)
			if(projectiles.get(i) instanceof Missile)
				projectiles.get(i).render();
		
		for(int i = 0;i < enemies.size(); i++)
			enemies.get(i).render();
		
		for(int i = 0;i < powerups.size(); i++)
			powerups.get(i).render();
		
		for(int i = 0;i < effects.size(); i++)
			effects.get(i).render();
		
		g.drawImage(new Image("res/gui/hp.png"), 616, 16);
		g.drawImage(new Image("res/border.png"), 0, 0);
		
		a.render();
		
		if(player != null)
		{
			g.drawString("SCORE: " + score * 100, 20, 14);
			g.drawString("Missiles: ", 250, 14);
			
			for(int i = 0; i < player.missiles; i++)
			{
				if(i % 2 == 0)
					g.drawImage(new Image("res/sprites/projectiles/missile_a.png"), i * 10 + 336, 14);
				
				if(i % 2 == 1)
					g.drawImage(new Image("res/sprites/projectiles/missile_b.png"),  i * 10 + 336, 14);
			}
			
			g.drawString("x" + player.hp, 644, 14);
		}
		else
		{
			g.drawString("FINAL SCORE: " + score * 100, 20, 14);
			g.drawString("x0", 644, 14);
			g.drawImage(new Image("res/game_over.png"), 31, 31);
		}
	}
	//this handles all behind-the-scenes work for entities, losing, etc.
	public void update(GameContainer gcan, StateBasedGame state, int delta) throws SlickException
	{	
		if(score < 0)
			score = 0;
		
		d.direct();
		
		if(!musicstarted)
		{
			background.setVolume(0.5f);
			background.loop();
		}
		musicstarted = true;
		
		for(int i = 0;i < 5;i++)
		{
			for(int k = 0;k < 4;k++)
				tile[i][k].update();
		}
		
		for(int i = 0;i < powerups.size(); i++)
			powerups.get(i).update();
		
		for(int i = 0;i < projectiles.size(); i++)
			projectiles.get(i).update();
		
		for(int i = 0;i < enemies.size(); i++)
			enemies.get(i).update();
		
		for(int i = 0;i < effects.size(); i++)
			effects.get(i).update();
		
		if(player != null)
			player.update();
		
		if(a.activated())
		{
			Util.click.play();
			background.stop();
			state.enterState(0, new FadeOutTransition(Color.black, 250), new FadeInTransition(Color.black, 250));
		}
		
		a.update();
		
		cx += 2;
		cy -= 2;
		
		if(cx >= 680 && cy <= 0)
		{
			cx = 0;
			cy = 680;
		}
	}
	//various methods used by entities
	public void spawnPowerup(Powerup add) throws SlickException
	{
		powerups.add(add);
	}
	
	public void spawnProjectile(Entity add) throws SlickException
	{
		projectiles.add(add);
	}
	
	public void spawnEnemy(Entity add) throws SlickException
	{
		enemies.add(add);
	}
	
	public void spawnEffect(Entity eff) throws SlickException
	{
		effects.add(eff);
	}
	
	public void removePowerup(Powerup des) throws SlickException
	{
		for(int i = 0;i < powerups.size(); i++)
			if(powerups.get(i) == des)
				powerups.remove(i);
	}
	
	public void removeProjectile(Entity des) throws SlickException
	{
		for(int i = 0;i < projectiles.size(); i++)
			if(projectiles.get(i) == des)
			{
				if(projectiles.get(i) instanceof Missile)
				{
					((Missile) projectiles.get(i)).activate();
					projectiles.remove(i);
				}
				else
					projectiles.remove(i);
			}
	}
	
	public void damageEnemy(Entity des, int d) throws SlickException
	{
		for(int i = 0;i < enemies.size(); i++)
			if(enemies.get(i) == des)
			{
				enemies.get(i).hp -= d;
				
				if(enemies.get(i).hp <= 0)
				{
					if(r.nextBoolean() && enemies.get(i) instanceof Enemy)
						if(r.nextBoolean())
							spawnPowerup(new Powerup(localgc, this, des.x + 6, des.y, "triple"));
						else
							if(r.nextBoolean())
								spawnPowerup(new Powerup(localgc, this, des.x + 6, des.y, "missile"));
							else
								spawnPowerup(new Powerup(localgc, this, des.x + 6, des.y, "hp"));
							
					enemies.remove(i);
				}
			}
	}
	
	public void removeEffect(Entity eff) throws SlickException
	{
		for(int i = 0;i < effects.size(); i++)
				effects.remove(i);
	}
	
	public void killPlayer() throws SlickException
	{
		player.hp--;
		player.triplebullets = false;
		player.s2 = new Image("res/sprites/empty.png");
		
		if(player.hp <= 0)
		{
			Util.pk.play();
			player = null;
		}
	}
	//used by Slick2d to verify a state class versus it's ID in the Skystar class
	public int getID()
	{
		return 1;
	}
}
