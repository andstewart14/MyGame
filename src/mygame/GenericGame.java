package mygame;
import java.util.ArrayList;

import jgame.*;
import jgame.platform.*;



public class GenericGame extends JGEngine  implements MyGameValues
{
	//power up switching
	public static boolean SwitchPowerShot = false;
	public static boolean SwitchMultiShot = false;
	//powershot power up
	public static boolean powershot = false;
	public static boolean powershot2 = false;
	public static boolean powershot3 = false;
	//multishot power up
	public static boolean multishot = false;
	public static boolean multishot2 = false;
	public static boolean multishot3 = false;
	//boss booleans
	public static boolean bossOneDeath = false;
	public static boolean bossTwoDeath = false;
	public boolean bossTwoSpawn = false;
	private boolean bossOneSpawn = false;
	//timers and ints
	public static int numInterceptor = 0;
	public static int DKP = 0;
	public static int Score = 0;
	public static double level = 1;
	private Player p;
	private int timer = 0;
	private int BossOneTimer = 0;
	/********************************* SETUP **********************************/
	// These methods all create the window and set up the program.  You should not
	// need to modify them unless you want to change the size of the wnidow, though
	// that can be done using the variables above. 

	public static void main(String [] args)	
	{
		new GenericGame(new JGPoint(tilesX*tileSize,tilesY*tileSize));
	}
	public GenericGame(JGPoint size)
	{
		initEngine(0,0);
	} 
	public GenericGame()
	{
		initEngineApplet(); 
	}

	/********************************* INIT METHODS **********************************/
	// These methods will be called once at the start of the program.
	//    initCanvas corresponds to paintFrame (for text and drawing tools)
	//    initGame corresponds to doFrame (for objects)

	public void initCanvas()
	{
		setCanvasSettings(tilesX,tilesY,tileSize,tileSize,null,null,null); 
	}

	public void initGame() 
	{
		setFrameRate(fps,0);
		defineMedia("media.tbl");
		setGameState("Game");
		p = new Player(500,500); 
		setBGImage("spacebg");
		playAudio("bgmusic", "bgmusic", true);
	}
	
	/******************************** START METHODS **********************************/

	// These methods will be called each time when their gamestate starts
	public void startGame()
	{
		
	} 
	public void startPause()
	{
		playAudio("pause");
	}
	
	/********************************* CORE METHODS **********************************/
	// These methods will be called every frame.  They form the basis for your program.
	//    initCanvas corresponds to paintFrame (for text and drawing tools)
	//    initGame corresponds to doFrame (for objects)
	public void SpawnPowerUp()
	{
		if(timer % 900 == 0)
		{ 
			new ExtraLife(random(0,tilesX * tileSize), 8);  					
		}
		if(timer % 1300 == 0)
		{ 
			new ExtraDKP(random(0,tilesX * tileSize), 8);  					
		}
	}
	public void doFrameGame() 
	{
		
		moveObjects(null,0);		// moves all objects
		SpawnPowerUp();
		
		checkCollision(enemyCollisionID,playerCollisionID);
		checkCollision(playerShotCollisionID,enemyCollisionID);
		checkCollision(enemyShotCollisionID, playerCollisionID);
		checkCollision(playerCollisionID, enemyShotCollisionID);
		checkCollision(PowerUpCollisionID, playerCollisionID);
		
		// checks of objects with a cid of 2 hit cid of 4
		
		/** Create Green and Yellow Boxes  **/
		//in game get key statements
		if(getKey('1'))				
		{
			SwitchMultiShot=true;
			SwitchPowerShot=false;
			clearKey('1');
		}
		if(getKey('2'))				
		{
			SwitchPowerShot=true;
			SwitchMultiShot=false;
			clearKey('2');
		}
		
		
		//
		if(DKP<0)
		{
			DKP=0;
		}
		timer++;
		//level 5
		if(timer % 170 == 0 && level == 5)
		{ 
			new PhyStorm(random(0,pfWidth()), random(0,pfHeight()));
		}
		if(timer % 40 == 0 && level == 5 && numInterceptor <= 8)	
		{
			new Interceptor(random(0,tilesX * tileSize), 8); 
			numInterceptor++;
		}
		// level 3.5
		if(timer % 170 == 0 && level == 3.5)
		{ 
			new PhyStorm(random(0,pfWidth()), random(0,pfHeight()));
		}
		if(timer % 40 == 0 && level == 3.5 && numInterceptor <= 8)	
		{
			new Interceptor(random(0,tilesX * tileSize), 8); 
			numInterceptor++;
		}
		if(timer % 200 == 0 && level == 3.5 && level!=5)								// Every tenth frame, create an object
		{ 
			new Corsair(random(0,tilesX * tileSize), 8);    					
		}
		if(timer % 150 == 0 && level >= 4 && level!=5)								// Every tenth frame, create an object
		{ 
			new Corsair(random(0,tilesX * tileSize), 8);    					
		}
		if(timer % 30 == 0)								// Every tenth frame, create an object
		{ 
			if(level != 2.5 && level != 3.5 && level!=5)
			{
				new Scourge(random(0,tilesX * tileSize), 8); 
			}   					
		}
		if(timer % 150 == 0 && level == 2.5 && level!=5)								// Every tenth frame, create an object
		{ 
			new Wraith(random(0,tilesX * tileSize), 10);  
		}
		if(timer % 100 == 0 && level >= 2 && level!=5)								// Every tenth frame, create an object
		{ 
			if(level != 2.5 && level != 3.5)
			{
				new Wraith(random(0,tilesX * tileSize), 10);  
			}
		}
		if(timer % 120 == 0 && level >= 3 && level!=5)								// Every tenth frame, create an object
		{ 
			if(level != 2.5 && level != 3.5)
			{
			new Devourer(random(0,tilesX * tileSize), 20); 
			}
		}
		
		
		if(getKey('P'))				// recieves a keypress command to pause game
		{
			setGameState("Pause");		
			clearKey('P');				
		}
		if(Score == 1500)
		{
			level = 1;
		}
		if(Score >= 1500)
		{
			level = 2;
		}
		if(Score >= 3500) 
		{
			level = 2.5;
		}
		if(Score >= 13000)
		{
			level = 3.5;
		}
		if(Score >= 20000)
		{
			level = 5;
			bossOneSpawn = false;
			bossTwoSpawn = false;
		}
		if(level==5 && bossOneSpawn == false && bossTwoSpawn == false)
		{
			if(BossOneTimer==200)
			{
			new battlecruzer(random(0,tilesX * tileSize - 300), 100);
			new Carrier(random(0,tilesX * tileSize - 300), 100);
			bossOneSpawn = true;
			bossTwoSpawn = true;
			}
			else
			{
				BossOneTimer++;
			}
		}
		if(level==2.5 && bossOneSpawn == false)
		{
			if(BossOneTimer==200)
			{
			new battlecruzer(random(0,tilesX * tileSize - 300), 100);
			bossOneSpawn = true;
			}
			else
			{
				BossOneTimer++;
			}
		}
		if(level==2.5 && bossOneDeath == true)
		{
			level = 3;
		}
		if(level==3.5 && bossTwoSpawn == false)
		{
			new Carrier(random(0,tilesX * tileSize - 300), 100);
			bossTwoSpawn = true;
			
		}
		if(level==3.5 && bossTwoDeath == true)
		{
			level = 4;
		}
		
	}

	public void doFramePause() 
	{
		if(DKP<0)
		{
			DKP=0;
		}
		if(getKey('P'))				// recieves a keypress command to reset game
		{
			setGameState("Game");
			clearKey('P');
		}
		if(getKey('1'))				// recieves a keypress command to reset game
		{
			//if(DKP>=50)
			//{
				multishot=true;
				DKP=DKP-50;
			//}
			clearKey('1');
		}
		if(getKey('2'))				// recieves a keypress command to reset game
		{
			if(multishot==true)
			{
				if(DKP>=100)
				{
					multishot2=true;
					DKP=DKP-100;
				}
			clearKey('2');
		}
		}
		if(getKey('3'))				// recieves a keypress command to reset game
		{
			if(multishot==true && multishot2==true)
			{
				if(DKP>=150)
				{
					multishot3=true;
					DKP=DKP-150;
				}
			}
			clearKey('3');
		}
		if(getKey('4'))				// recieves a keypress command to reset game
		{
			if(DKP>=50)
			{
				powershot=true;
				DKP=DKP-50;
			}
		clearKey('4');
		}
		if(getKey('5'))				// recieves a keypress command to reset game
		{
			if(powershot==true)
			{
				if(DKP>=100)
				{
					powershot2=true;
					DKP=DKP-100;
				}
			}
			clearKey('5');
		}
		if(getKey('6'))				// recieves a keypress command to reset game
		{
			if(powershot==true && powershot2==true)
			{
				if(DKP>=150)
				{
					powershot3=true;
					DKP=DKP-150;
				}
			}
			clearKey('6');
		}
		if(getKey('7'))				// recieves a keypress command to reset game
		{
			if(DKP>=150)
			{
				Player.Lives++;
				DKP=DKP-150;
			}
			clearKey('6');
		}
	}
	public void paintFramePause() 
	{
		drawString("To un pause please press P",800, 50, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("PAUSED",780, 370, 1, new JGFont("Arial", 70, 70), new JGColor(150,255,150));
		drawString("menu",100, 50, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("use your DKP points to buy powerups",350, 90, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("1. multishot tier 1 - 50",200, 145, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("2. multishot tier 2 - 100",200, 170, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("3. multishot tier 3 - 150",200, 200, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("4. powershot tier 1 - 50",200, 245, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("5. powershot tier 2 - 100",200, 270, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("6. powershot tier 3 - 150",200, 300, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("7. Extra life - 150",200, 350, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
	}
	// Note that you can also have a generic doFrame or paintFrame method.
	// These are called no matter what the gameState is!
	public void paintFrame() 
	{
		drawString("Time: " + (int) timer, 1100, 40, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("Score: " + (int) Score, 1100, 70, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("level: " + (int) level, 1100, 100, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("DKP: " + (int) DKP, 1100, 150, 1, new JGFont("Arial", 20, 20), new JGColor(255,255,255));
		drawString("to switch between shots press 1 for multishot, 2 for powershot durring the game",1100,700,1,new JGFont("Arial", 15, 15),new JGColor(255,255,255));
		drawString("press P to pause and see the menu to buy power up's",1100,720,1,new JGFont("Arial", 15, 15),new JGColor(255,255,255));
		if(Player.Lives==0)
		{
			drawString("GAME OVER!",780, 370, 1, new JGFont("Arial", 70, 70), new JGColor(150,255,150));
		}
	}
}


