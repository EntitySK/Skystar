/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar.gui;

import org.lwjgl.input.Mouse;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
//basic 2-state tactile button
public class Button extends GuiObject
{
	public Button(GameContainer gcan, int x, int y, Image image1, Image image2)
	{
		g = gcan.getGraphics();
		i = gcan.getInput();
		activated = false;
		x1 = x;
		x2 = x + image1.getWidth();
		y1 = y;
		y2 = y + image1.getHeight();
		idle = image1;
		over = image2;
	}
	
	public void render()
	{	
		if(Mouse.getX() > x1 && Mouse.getX() < x2 && 680 - Mouse.getY() > y1 && 680 - Mouse.getY() < y2)
		{
			g.drawImage(over, x1, y1);
			return;
		}
		
		g.drawImage(idle, x1, y1);
	}
	
	public void update()
	{
		if(Mouse.getX() > x1 && Mouse.getX() < x2 && 680 - Mouse.getY() > y1 && 680 - Mouse.getY() < y2)
		{
			if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				activated = true;
			else
				activated = false;
		}
	}
	
	public boolean activated()
	{
		return activated;
	}
	
	//ONLY FOR OTHER GUI OBJECTS
	public Button(GameContainer gcan, int x, int y)
	{
		g = gcan.getGraphics();
		i = gcan.getInput();
		activated = false;
		x1 = x;
		y1 = y;
	}
	//not meant to be used
	public boolean subActivated(int x)
	{
		return false;
	}
}