package mygame;
import jgame.JGObject;

class BattlecruzerShot extends JGObject  implements MyGameValues
{
	
	BattlecruzerShot(double x, double y, double xspeed, double yspeed)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("EnemyShot",true,x,y,enemyShotCollisionID,"battlecruzershot", xspeed,yspeed, -1);
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
