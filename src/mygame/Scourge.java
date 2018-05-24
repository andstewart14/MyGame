package mygame;
import jgame.JGObject;

class Scourge extends JGObject  implements MyGameValues
{
	Scourge(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("Bomber",true,x,y,enemyCollisionID,"scourge", 5,5, -1);
		
		xdir = 0;
		ydir = 1;
	}
	
	public void hit(JGObject obj)		
	{	
		GenericGame.DKP = GenericGame.DKP + 1;
		GenericGame.Score = GenericGame.Score + 100;
		remove();	
		//obj.remove();		// When someone hits me, I die	
		eng.playAudio("beep");		// When someone hits me, I beep
	}

	public void move()
	{
		// If I move off screen, put me randomly back at top of screen
		if(x < 0 || x > 36 * 32 || y < 0 || y > 25 * 32)
		{
			x = eng.random(0,36*32);
			y = 10;
		}
	}	
} 
