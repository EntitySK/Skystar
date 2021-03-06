/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package net.arsmachina.skystar;

import java.util.Random;

import net.arsmachina.skystar.entity.Enemy;
import net.arsmachina.skystar.entity.EnemyLight;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
//Director is the simple enemy spawner AI
public class Director
{
	Random r;
	GameContainer gcan;
	StatePlay play;
	
	int i;
	int mod;
	
	public Director(GameContainer gcanin, StatePlay statein)
	{
		gcan = gcanin;
		play = statein;
		r = new Random();
		
		mod = 2;
	}

	public void direct() throws SlickException
	{
		if(i >= 7)
			i = 0;
		
		if(r.nextBoolean() && !Timer.used && Timer.delta % mod == 0)
		{
			Timer.used = true;
			play.spawnEnemy(new EnemyLight(gcan, play, i * 85 + 85 - 24, -70));
		}
		
		if(r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && !Timer.used && Timer.delta % mod == 0)
		{
			Timer.used = true;
			play.spawnEnemy(new Enemy(gcan, play, i * 85 + 85 - 24, -70));
		}
		
		i++;
	}
}
