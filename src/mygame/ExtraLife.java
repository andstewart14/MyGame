package mygame;
import jgame.JGObject;

class ExtraLife extends JGObject  implements MyGameValues
{
	
	ExtraLife(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("PowerUp",true,x,y,PowerUpCollisionID,"extralife", 5,2, -1);
		
		xdir = 0;
		ydir = 3;
	}
	
	public void hit(JGObject obj)		
	{	
		remove();
	}

	public void move()
	{	
	}	
} 
