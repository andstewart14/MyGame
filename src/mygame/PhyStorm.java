package mygame;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

class PhyStorm extends JGObject  implements MyGameValues
{
	public static int PhyLife = 1;
	PhyStorm(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("Bomber",true,x,y,enemyCollisionID,"phystorm", 5,5, 200);
		
		xdir = 0;
		ydir = 0;
	}
	public void hit(JGObject obj)		
	{	
		
	}
	public void move()
	{
		
	}	
} 
