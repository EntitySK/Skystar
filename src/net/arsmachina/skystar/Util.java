/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
//handles all the global or odd parts of the program
public class Util
{
	public static boolean ingame = false;
	
	public static Sound click;
	public static Sound exp;
	public static Sound pk;
	public static Sound alien;
	public static Sound missile;
	public static Sound powerup;
	public static Sound shoot;
	
	public static void terminate()
	{
		//System.exit(0);
	}
	
	public static void initSound() throws SlickException
	{
		click = new Sound("res/sound/click.wav");
		exp = new Sound("res/sound/exp.wav");
		pk = new Sound("res/sound/exp2.wav");
		alien = new Sound("res/sound/laser.wav");
		missile = new Sound("res/sound/launch.wav");
		powerup = new Sound("res/sound/powerup.wav");
		shoot = new Sound("res/sound/shoot.wav");
	}
}
