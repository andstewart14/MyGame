package mygame;
import jgame.JGObject;

class Corsair extends JGObject  implements MyGameValues
{
	int timer = 0;
	int life=3;
	Corsair(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("Bomber",true,x,y,enemyCollisionID,"corsair", 2,8, -1);
		
		xdir = 4;
		ydir = .5;
		
	}
	
	public void hit(JGObject obj)		
	{	
		
		if (life != 0)
		{
			life--;
			if(obj instanceof PlayerShot)
			{
			obj.remove();
			}
			else if(obj instanceof PlayerShot1)
			{
				obj.remove();
			}
			else if(obj instanceof PlayerShot2)
			{
				obj.remove();
			}
			else if(obj instanceof PlayerShot3)
			{
				obj.remove();
			}
			eng.playAudio("beep");
		}
		else
		{
			if(obj instanceof PlayerShot)
			{
			obj.remove();
			}
			else if(obj instanceof PlayerShot1)
			{
				obj.remove();
			}
			else if(obj instanceof PlayerShot2)
			{
				obj.remove();
			}
			else if(obj instanceof PlayerShot3)
			{
				obj.remove();
			}
			GenericGame.Score = GenericGame.Score + 300;
			GenericGame.DKP = GenericGame.DKP + 3;
			remove();// When someone hits me, I die	
			eng.playAudio("beep");		// When someone hits me, I beep
		}
	}

	public void move()
	{
		timer++;
		
		if(timer % 30 == 0)
		{
			new CorsairShot(x,y);
		}
		if(x>eng.pfWidth()-40)
		{
			xdir=-6;
		}
		if(x<0)
		{
			xdir=6;
		}
		// If I move off screen, put me randomly back at top of screen
		if(x < 0 || x > 36 * 32 || y < 0 || y > 25 * 32)
		{
			x = eng.random(0,36*32);
			y = 10;
		}
	}	
} 
