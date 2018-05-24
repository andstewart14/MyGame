package mygame;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

class battlecruzer extends JGObject  implements MyGameValues
{
	int enrageDeathTimer = 0;
	int enrageTimer = 1400;
	int timerdeath = 35;
	int timer = 0;
	int life = 150;
	int maxHealth = 150;

	battlecruzer(double x, double y)
	{
		// This is the call to the JG OBject constructor.  There are many constructors
		// you can use for JG objects.  This one is as follows:
		// Name, Unique ID?, xPos, yPos, cid, graphic, xspeed, yspeed, expiry)
		super("Bomber",true,x,y-100,enemyCollisionID,"battlecruzer", 2,-1, -1);

		xdir = 1;
		ydir = 0;

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

		if (life != 0)			// Alive
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
			eng.playAudio("beep");
		}
		else				// Dead
		{
			setGraphic("battlecruzerdead");


		}
		if (life == 50)
		{
			setGraphic("battlecruzerdamaged");
		}

	}

	public void move()
	{
		if(enrageTimer>0)
		{
			enrageTimer--;
		}
		timer++;

		if(enrageDeathTimer % 3 == 0 && enrageTimer==0)
		{
			new BattlecruzerShot(x,y+240,-1,1);
			new BattlecruzerShot(x,y+240,1,1);
			new BattlecruzerShot(x,y+240,-1,2);
			new BattlecruzerShot(x,y+240,1,2);
			new BattlecruzerShot(x,y+240,-1,0);
			new BattlecruzerShot(x,y+240,1,0);
			new BattlecruzerShot(x,y+240,0,7);
			new BattlecruzerShot(x,y+240,1,7);
			new BattlecruzerShot(x,y+240,2,7);
			new BattlecruzerShot(x,y+240,3,7);
			new BattlecruzerShot(x,y+240,4,7);
			new BattlecruzerShot(x,y+240,5,7);
			new BattlecruzerShot(x,y+240,6,7);
			new BattlecruzerShot(x,y+240,7,7);
			new BattlecruzerShot(x,y+240,8,7);
			new BattlecruzerShot(x,y+240,9,7);
			new BattlecruzerShot(x,y+240,10,7);
			new BattlecruzerShot(x,y+240,-1,7);
			new BattlecruzerShot(x,y+240,-2,7);
			new BattlecruzerShot(x,y+240,-3,7);
			new BattlecruzerShot(x,y+240,-4,7);
			new BattlecruzerShot(x,y+240,-5,7);
			new BattlecruzerShot(x,y+240,-6,7);
			new BattlecruzerShot(x,y+240,-7,7);
			new BattlecruzerShot(x,y+240,-8,7);
			new BattlecruzerShot(x,y+240,-9,7);
			new BattlecruzerShot(x,y+240,-10,7);
		}
		if(enrageTimer==0)
		{
			enrageDeathTimer++;
		}
		if(life == 0)
		{
			timerdeath--;
		}


		if (timerdeath == 0)
		{
			GenericGame.bossOneDeath = true;
			GenericGame.level = 3;
			eng.playAudio("beep");		// When someone hits me, I beep
			GenericGame.Score = GenericGame.Score + 300;
			GenericGame.DKP = GenericGame.DKP + 20;
			remove();	// When someone hits me, I die	
		}		

		if(timer % 30 == 0)
		{
			new BattlecruzerShot(x+100,y+240,0,7);
			new BattlecruzerShot(x+240,y+140,0,7);
			new YamatoGunShot(x+250,y+250);
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
		if(GenericGame.level==5)
		{
			eng.setColor(new JGColor(128,0,0)); 
			eng.drawRect(0, 700, maxHealth * 5, 15, true, false);

			eng.setColor(new JGColor(225,0,0)); 
			eng.drawRect(0, 700, life * 5, 15, true, false);
			eng.drawString("Battlecruzer Health: " + (int) life, 150, 680, 5, new JGFont("Arial", 20, 20), new JGColor(180,0,0));
		}
		else
		{
			eng.setColor(new JGColor(128,0,0)); 
			eng.drawRect(0, 5, maxHealth * 5, 15, true, false);

			eng.setColor(new JGColor(225,0,0)); 
			eng.drawRect(0, 5, life * 5, 15, true, false);
			eng.drawString("Battlecruzer Health: " + (int) life, 150, 25, 5, new JGFont("Arial", 20, 20), new JGColor(180,0,0));
		}
	}



} 
