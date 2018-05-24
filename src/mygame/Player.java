package mygame;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

class Player extends JGObject implements MyGameValues
{
	int hitcounter = 0;
	int timer = 0;
	int maxHealth = 5;
	int invTimer = 0;
	int shotTimer = 10;
	public static int Lives = 3;
	public static int Health = 5;
	private boolean dot = false;
	private int dotTimer = 0;

	Player(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("player",true,x,y,playerCollisionID,"mutalisk", 8,8, -1);
	}
	public void hit(JGObject obj)		
	{	
		obj.hit(this);
		if(obj instanceof ExtraLife)
		{
			Lives++;
		}
		else if(obj instanceof ExtraDKP)
		{
			GenericGame.DKP=GenericGame.DKP+20;
		}
		else if(obj instanceof YamatoGunShot)
		{
			Health=0;
			eng.playAudio("beep");
		}
		else if(obj instanceof BattlecruzerShot)
		{
			Health--;
			Health--;
			eng.playAudio("beep");
		}
		else if(obj instanceof devourershot)
		{
			if(dot == true)
			{
				obj.remove();
			}
			else
			{
				obj.remove();
				setGraphic("mutaliskpoisened");
				dot = true;
				dotTimer = 449;
			}
		}
		else if(obj instanceof PhyStorm)
		{
			hitcounter++;
			if(hitcounter % 25 ==0)
			{
				Health--;
			}
		}
		else
		{
			if(Health <= 5)
			{
				Health--;
				eng.playAudio("beep");
			}
		}	
	}

	public void move()
	{
		if (Health <= -1)
		{
			Health = 0;
		}
		if(Health == 0)
		{
			GenericGame.DKP=GenericGame.DKP-10;
			eng.playAudio("beep");			// When someone hits me, I beep
			Lives--;
			Health = 5;
			x=550;
			y=700;
			colid = GenericGame.invCollisionID;
			invTimer = 60;
			setGraphic("mutaliskinv");
		}
		if(Lives == 0)
		{
			remove();	// When someone hits me, I die
			Health = 0;	
		}
		setDir(0,0);		// sets xdir to 0 and ydir to 0... stops you!

		if (invTimer > 0)
		{
			invTimer--;
		}
		if(invTimer == 0)
		{
			colid = playerCollisionID;
			setGraphic("mutalisk");
		}
		if(dotTimer > 0 && dot)
		{
			if(dotTimer %150 == 0)Health--;
			dotTimer--;
		}
		if(dotTimer == 0)
		{
			dot = false;
			setGraphic("mutalisk");
		}

		if(eng.getKey('W') && y >= yspeed)			//move up
		{			
			ydir = -1;
		}
		if(eng.getKey('S') && y < pfheight - 32)	//move down
		{			
			ydir = 1;
		}
		if(eng.getKey('A') && x >= xspeed)			//move left
		{	
			if(dotTimer>0)
			{
				setGraphic("mutaliskpoisenedl");
			}
			if (colid == playerCollisionID && dotTimer<=0)
				{
					setGraphic("mutaliskl");
				}
			
			if (colid == invCollisionID)
			{
				setGraphic("mutaliskinvl");
			}
			xdir = -1;
		}
		if(eng.getKey('D') && x < pfheight + 300)	//move right
		{		
			if(dotTimer>0)
			{
				setGraphic("mutaliskpoisened");
			}
			if (colid == invCollisionID)
			{
				setGraphic("mutaliskinv");
			}
			if (colid == playerCollisionID && dotTimer<=0)
			{
				setGraphic("mutalisk");
			}
			xdir = 1;
		}
		if(eng.getKey(' ')) //shoot 
		{	
			if(shotTimer > 0)
			{
				shotTimer--;
			}
			if(shotTimer == 0)
			{
				if(GenericGame.SwitchMultiShot==true)
				{
					multishot123();
				}
				else if(GenericGame.SwitchPowerShot==true)
				{
					powershot123();
				}
				else
				{
					new PlayerShot(x, y, 0, -10);
				}
				shotTimer = shotTimer + 5;
			}
		}
	}	
	public void multishot123()
	{
		if(GenericGame.multishot3==true)
		{
			new PlayerShot(x, y, 0, -10);
			new PlayerShot(x, y, 1, -10);
			new PlayerShot(x, y, -1, -10);
			new PlayerShot(x, y, 2, -10);
			new PlayerShot(x, y, -2, -10);
		}
		else if(GenericGame.multishot2==true)
		{
			new PlayerShot(x, y, 0, -10);
			new PlayerShot(x, y, 1, -10);
			new PlayerShot(x, y, -1, -10);
		}
		else if(GenericGame.multishot==true)
		{
			new PlayerShot(x, y, 1, -10);
			new PlayerShot(x, y, -1, -10);
		}
		else
		{
			new PlayerShot(x, y, 0, -10);
		}
	}
	public void powershot123()
	{

		if(GenericGame.powershot3==true)
		{
			new PlayerShot3(x, y, 0, -10);
			new PlayerShot3(x, y, 0, -10);
			new PlayerShot3(x, y, 0, -10);
			new PlayerShot3(x, y, 0, -10);
			new PlayerShot3(x, y, 0, -10);
		}
		else if(GenericGame.powershot2==true)
		{
			new PlayerShot2(x, y, 0, -10);
			new PlayerShot2(x, y, 0, -10);
			new PlayerShot2(x, y, 0, -10);
		}
		else if(GenericGame.powershot==true)
		{
			new PlayerShot1(x, y, 0, -10);
			new PlayerShot1(x, y, 0, -10);
		}
		else
		{
			new PlayerShot(x, y, 0, -10);
		}
	}
	public void paint()
	{
		eng.setColor(new JGColor(128,0,0)); 
		eng.drawRect(50, 770, maxHealth * 25, 15, true, false);

		eng.setColor(new JGColor(0,225,0)); 
		eng.drawRect(50, 770, Health * 25, 15, true, false);
		eng.drawString("Player Health: " + (int) Health, 150, 740, 5, new JGFont("Arial", 15, 15), new JGColor(0,225,0));

		eng.setColor(new JGColor(0,225,0)); 
		eng.drawRect(900, 770, Lives * 25, 15, true, false);
		eng.drawString("Player Lives: " + (int) Lives, 1000, 740, 5, new JGFont("Arial", 15, 15), new JGColor(225,225,225));
	}


} 
