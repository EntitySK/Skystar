/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//interactive gui-object superclass
public abstract class GuiObject
{
	public Graphics g;
	public Input i;
	
	public int x1, x2, y1, y2;
	public Image idle, over;
	public boolean activated;
	
	public abstract void render();
	
	public abstract void update();
	
	public abstract boolean activated();
	
	public abstract boolean subActivated(int n);
}
