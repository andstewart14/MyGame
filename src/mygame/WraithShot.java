package mygame;
import jgame.JGObject;

class WraithShot extends JGObject  implements MyGameValues
{
	
	WraithShot(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("EnemyShot",true,x,y,enemyShotCollisionID,"wraithshot", 5,2, -1);
		
		
		xdir = 0;
		ydir = 7;
	}

	public void hit(JGObject obj)
	{
		remove();
	}
	public void move()
	{
		// If I move off screen, remove me
		if(x < 0 || x > 36 * 32 || y < 0 || y > 25 * 32)
		{
			remove();
		}
	}	
} 
