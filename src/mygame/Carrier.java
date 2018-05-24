package mygame;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

class Carrier extends JGObject  implements MyGameValues
{
	int regenTimer = 0;
	int powerShield = 200;
	int maxPowerShield = 200;
	int maxHealth = 150;
	int timer = 0;
	int life = 150;
	

	Carrier(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("carrier",true,x,y-100,enemyCollisionID,"carrier", 2,-1, -1);
		
		xdir = 1;
		ydir = 0;
		
	}
	
	public void hit(JGObject obj)		
	{	
		regenTimer=0;
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
		
		if(powerShield!=0)
		{
			if(obj instanceof Player)
			{
				powerShield = powerShield - 10;
				obj.x=550;
				obj.y=700;
			}
			else
			{
				powerShield--;
			}
			}
			else if (life != 0)			// Alive
			{
				
				if(obj instanceof Player)
				{
					life = life - 10;
					obj.x=550;
					obj.y=700;
				}
				else
				{
					life--;
				}
			}
			eng.playAudio("beep");
	//	else				// Dead
	//	{
	//		setGraphic("battlecruzerdead");
	//	
	//
	//	}
	//	if (life == 50)
	//	{
	//		setGraphic("battlecruzerdamaged");
	//	}
	//	
	//}
	}

	public void move()
	{
		regenTimer++;
		if(powerShield>200)
		{
			powerShield=200;
		}
		if(regenTimer<0)
		{
			regenTimer = 0;
		}
		timer++;
		if(powerShield!=0)
		{
			setGraphic("carriershield");
		}
		if(powerShield==0)
		{
			setGraphic("carrier");
		}
		
		if (life == 0)
		{
			GenericGame.bossTwoDeath = true;
			GenericGame.level = 4;
			eng.playAudio("beep");		// When someone hits me, I beep
			GenericGame.Score = GenericGame.Score + 300;
			GenericGame.DKP = GenericGame.DKP + 30;
			remove();	// When someone hits me, I die	
		}		
		if(regenTimer % 45 == 0)
		{
			powerShield=powerShield+5;
		}
		if(timer % 50 == 0)
		{
			new CorsairShot(x+300,y+300);
		}
		if(x>eng.pfWidth()-350)
		{
			xdir=-1;
		}
		if(x<0)
		{
			xdir=1;
		}
	
		// If I move off screen, put me randomly back at top of screen
		//if(x < 0 || x > 36 * 32 || y < 0 || y > 25 * 32)
		//{
		//	x = eng.random(0,36*32);
		//	y = 10;
		//}
	}	
	public void paint()
	{
		eng.setColor(new JGColor(128,0,0)); 
		eng.drawRect(0, 5, maxHealth * 5, 15, true, false);
		
		eng.setColor(new JGColor(225,0,0)); 
		eng.drawRect(0, 5, life * 5, 15, true, false);
		eng.drawString("Carrier Health: " + (int) life, 150, 25, 5, new JGFont("Arial", 20, 20), new JGColor(180,0,0));
		
		eng.setColor(new JGColor(0,0,128)); 
		eng.drawRect(0, 40, maxPowerShield * 5, 15, true, false);
		
		eng.setColor(new JGColor(0,0,225)); 
		eng.drawRect(0, 40, powerShield * 5, 15, true, false);
		eng.drawString("Carrier Power Shield: " + (int) powerShield,150, 60, 5, new JGFont("Arial", 20, 20), new JGColor(0,0,180));

	}
} 
