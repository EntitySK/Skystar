/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.makina.skystar.StatePlay;
//encompassing class for powerups
public class Powerup extends Entity {

	private String type;
	
	public Powerup(GameContainer gcanin, StatePlay playin, int xin, int yin, String typein) throws SlickException
	{
		super(gcanin, playin, xin, yin);
		type = typein;
		
		if(type.equals("hp"))
			s1 = new Image("res/sprites/upgrades/upgrade_hp.png");
		
		if(type.equals("triple"))
			s1 = new Image("res/sprites/upgrades/upgrade_triple.png");
		
		if(type.equals("missile"))
			s1 = new Image("res/sprites/upgrades/upgrade_missile.png");
		
		s2 = s1;
		
		speed = 8;
	}
	
	public void update() throws SlickException
	{
		y += speed;
		
		if(y >= 710)
			play.removePowerup(this);
	}
	
	public String getType()
	{
		return type;
	}
}
