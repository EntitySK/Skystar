/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.makina.skystar.gui.*;
//the menu screen
public class StateMenu extends BasicGameState
{
	Random r;
	
	int dx1, dy1, dx2, dy2;
	
	boolean top, right, bottom;
	
	private GuiObject a;
	private GuiObject b;
	
	public StateMenu(int sn)
	{
	}
	
	public void init(GameContainer gcan, StateBasedGame state) throws SlickException
	{
		r = new Random();
		Util.initSound();
		
		top = false;
		right = false;
		bottom = false;
		dy2 = 680;
		
		a = new ButtonToggle(gcan, 616, 0, new Image("res/gui/credits.png"), new Image("res/gui/credits_a.png"));
		
		b = new Button(gcan, 10, 130, new Image("res/gui/play.png"), new Image("res/gui/play_a.png"));
	}

	public void render(GameContainer gcan, StateBasedGame state, Graphics g) throws SlickException
	{
		g.drawImage(new Image("res/landscape.png"), dx1, dy1);
		
		g.drawImage(new Image("res/clouds.png"), dx2, dy2);
		g.drawImage(new Image("res/clouds.png"), dx2 - 1360, dy2);
		g.drawImage(new Image("res/clouds.png"), dx2, dy2 - 1360);
		g.drawImage(new Image("res/clouds.png"), dx2 - 1360, dy2 - 1360);
		
		g.drawImage(new Image("res/rulez.png"), 340, 170);
		g.drawImage(new Image("res/border.png"), 0, 0);
		g.drawImage(new Image("res/logo.png"), 157, 10);
		
		a.render();
		b.render();
		
		if(a.activated())
		{
			g.setColor(Color.red);
			g.drawString("Programming - Muhammad Rizvi", 410, 70);
			g.drawString("Art - Muhammad Rizvi", 410, 90);
			g.drawString("Sound - Muhammad Rizvi", 410, 110);
			g.drawString("Sound Effects - SFB Games", 410, 130);
			g.drawString("Libraries - Slick2d, LWJGL", 410, 150);
			g.drawString("Typography - FlamingText.com", 410, 170);
			g.drawString("Made with Eclipse, GIMP,", 410, 190);
			g.drawString("and Caustic 2", 410, 210);
		}
		
		g.setColor(Color.black);
		g.drawString("(c) Muhammad Rizvi (AKA Entity), 2013 - www.ArsMachina.net", 10, 654);
	}

	public void update(GameContainer gcan, StateBasedGame state, int delta) throws SlickException
	{
		if(b.activated())
		{
			Util.click.play();
			Timer.delta = 0;
			state.getState(1).init(gcan, state);
			state.enterState(1, new FadeOutTransition(Color.black, 250), new FadeInTransition(Color.black, 250));
		}
		
		if(!top) dx1-=2;
		if(!top && dx1 == -680) top = true;
		
		if(!right && top) dy1-=2;
		if(!right && dy1 == -680) right = true;
		
		if(!bottom && right) dx1+=2;
		if(!bottom && dx1 == 0) bottom = true;
		
		if(bottom) dy1+=2;
		if(dy1 == 0)
			{
			top = false;
			right = false;
			bottom = false;
			}
		
		dx2+=3;
		dy2-=3;
		
		if(dx2 >= 680 && dy2 <= 0)
		{
			dx2 = 0;
			dy2 = 680;
		}
		
		a.update();
		b.update();
	}
	
	public int getID()
	{
		return 0;
	}
}
