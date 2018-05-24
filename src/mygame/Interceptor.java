package mygame;
import jgame.JGObject;

class Interceptor extends JGObject  implements MyGameValues
{
	int timer = 0;
	int Health = 3;
	Interceptor(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("Bomber",true,x,y+350,enemyCollisionID,"interceptor", 5,5, -1);
		
		xdir = 4;
		ydir = 2;
	}
	
	public void hit(JGObject obj)		
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
		
		if(Health!=0)
		{
			Health--;
			eng.playAudio("beep");
		}
		else 
		{
			remove();	
			eng.playAudio("beep");
			GenericGame.numInterceptor=GenericGame.numInterceptor-1;
		}
	}

	public void move()
	{
		timer++;
		if(timer % 30 == 0)
		{
			new InterceptorShot(x,y);
		}
		if(x>eng.pfWidth()-20)
		{
			xdir=-4;
		}
		if(x<0)
		{
			xdir=4;
		}
		if(y<200)
		{
			ydir=2;
		}
		if(y>450)
		{
			ydir=-2;
		}
	}	
} 
