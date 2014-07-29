/*
 * (c) Muhammad Ali Rizvi, 2013
 */
package com.makina.skystar;
//supposed to handle timing in its own Thread (prone to unevenness but implemented elsewhere rarely)
public class Timer implements Runnable{

	public static int delta;
	public static boolean running = false;
	
	public static boolean used;
	
	public Timer()
	{
		running = true;
		used = false;
	}

	public void run()
	{
		while(running)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delta++;
			used = false;
			
			if(delta >= Integer.MAX_VALUE-1) delta = 0;
		}
	}

}
