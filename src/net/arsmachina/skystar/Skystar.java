/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar;
//"makina" (referring to my package names) was my previous pseudo-corporate name, now its "Entity" (maybe?)
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
//the main class to setup Slick2d, window, states, etc.
public class Skystar extends StateBasedGame
{
	private static final String gamename = "Skystar v1.1";
	private static AppGameContainer app;
	
	public Skystar()
	{	
		super(gamename);
		
		addState(new StateMenu(0));
		addState(new StatePlay(1));
	}
	
	public Skystar(String gamename)
	{	
		super(gamename);
		
		addState(new StateMenu(0));
		addState(new StatePlay(1));
	}
	
	public void initStatesList(GameContainer gcan) throws SlickException
	{
		getState(0).init(gcan, this);
		getState(1).init(gcan, this);
		
		enterState(0);
	}
	
	public static void init()
	{
		try
		{
			app = new AppGameContainer(new Skystar(gamename));
			
			app.setDisplayMode(680, 680, false);
			app.setTargetFrameRate(30);
			app.setVSync(true);
			app.setIcon("res/skystar.png");
			app.setShowFPS(false);
			
			app.start();
			
			Util.ingame = false;
			
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			app = new AppGameContainer(new Skystar(gamename));
			
			app.setDisplayMode(680, 680, false);
			app.setTargetFrameRate(30);
			app.setVSync(true);
			app.setIcon("res/skystar.png");
			app.setShowFPS(false);
			
			app.start();
			
			Util.ingame = false;
			
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static AppGameContainer getApp()
	{
		return app;
	}
}
