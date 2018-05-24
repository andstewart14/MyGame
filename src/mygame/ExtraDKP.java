package mygame;
import jgame.JGObject;

class ExtraDKP extends JGObject  implements MyGameValues
{
	
	ExtraDKP(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("PowerUp",true,x,y,PowerUpCollisionID,"moardkp", 5,2, -1);
		
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
