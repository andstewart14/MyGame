package mygame;
import jgame.JGObject;

class PlayerShot2 extends JGObject  implements MyGameValues
{
	
	PlayerShot2(double x, double y, double xspeed, double yspeed)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("playershot",true,x,y,playerShotCollisionID,"mutashot2", xspeed,yspeed, -1);

	}
	
	public void hit(JGObject obj)		
	{	
		remove();				// When someone hits me, I die	d
		obj.remove();	
	}

	public void move()
	{
		// If I move off screen, remove me
		if(x < 0 || x > 36 * 32 || y < 0 || y > 25 * 32)
		{remove();
		}
			
	}	
} 
