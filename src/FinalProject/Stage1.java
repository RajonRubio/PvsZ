package FinalProject;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

import Zombies.Zombies;
import Plants.CoolPeaShooter;
import Plants.PeaShooter;
import Plants.Plants;
import Plants.Snap;
import Plants.Sunflower;
import Plants.Wallnut;

public class Stage1 extends BasicGameState{
	Image grass;
	Image ProjectilePea ;
	Image ProjectileSnowPea ;
	Image SeedBank ;
	Image Sun ;
	Image[] PP ;
	Image mouse ;
	
	Image Back ;
	Image Back2 ;
	Image Quit ;
	Image Quit2 ;
	Image PauseBack ;
	Animation xxx ;
	static boolean Run;
	
	Plants[][] plants;
	ArrayList<Zombies> zombies = new ArrayList<Zombies>();
	ArrayList<Zombies> rzombies = new ArrayList<Zombies>();
	
	int Vx ;
	float x;
	float y;
	int Cycle ;
	boolean Produced ;
	static final float G = 9.8f ;
	static final float V = 2f ;
	int[] PlantsMoney ;
	int SunMoney ;
	int b ;
	int time ;
	static int Ox = 50 ;
	static int Oy = 80 ;
	static int dx = 82 ;
	static int dy = 98 ;
	boolean[][] map ;
	boolean pick ;
	boolean ChangeColor = false ;
	int kinds;
	int[][] Kind ;
	float[] CD ;
	float[] CDing ;
	float CDtime ;
	boolean[] CanItPick ;
	boolean Pause ;
	Music music ;
	Random random ;
	Color BBlack ;
	boolean MusicStart ;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		mouse = new Image("mouse.png");
		grass = new Image("background1.jpg");
		ProjectilePea = new Image("ProjectilePea.png");
		ProjectileSnowPea = new Image("ProjectileSnowPea.png");
		SeedBank = new Image("SeedBank.png");
		Sun = new Image("Sun.png");
		
		Back = new Image("quickplay_back_button.png");
		Back2 = new Image("quickplay_back_button_highlight.png");
		Quit = new Image("SelectorScreen_Quit1.png");
		Quit2 = new Image("SelectorScreen_Quit2.png");
		PauseBack = new Image("pauseback.png");
		
		gc.setMouseGrabbed(true);
		Run = false ;
		INIT();
		
		random = new Random();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage( grass, -200 , 0 ); //畫背景
		g.drawImage( SeedBank, 10 , 0); 
		g.setColor(Color.black);
		
		g.drawString("50", 105 , 59);
		g.drawString("100", 153 , 59);
		g.drawString("50", 215 , 59);
		g.drawString("175", 265 , 59);
		
		
		//顯示現在有的太陽錢數//
		if(ChangeColor){
			g.setColor(Color.red);
		}
		if(SunMoney<10){
			g.setColor(Color.red);
			g.drawString("" + SunMoney, 43 , 64 );
			g.drawString("" + SunMoney, 42 , 64 );
			g.drawString("" + SunMoney, 44 , 64 );
		}
		else if(SunMoney<100){
			g.drawString("" + SunMoney, 39 , 64 );
		}
		else if(SunMoney<1000){
			g.drawString("" + SunMoney, 35 , 64 );
		}
		else if(SunMoney<10000){
			g.drawString("" + SunMoney, 31 , 64 );
		}
		else{
			g.drawString("" + SunMoney, 26 , 64 );			
		}
		g.setColor(Color.black);
		
		//畫植物的身體
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				if(plants[i][j]!=null){
					plants[i][j].renderbody(g);
				}
			}
		}
		
		//畫植物的投出物
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				if(plants[i][j]!=null){
					plants[i][j].renderprojecttile(g);
				}
			}
		}
		
		//畫殭屍
		for (int i = 0; i < 5; i++) {
			for(Zombies z:zombies){
				if(z.Locationy == i){
					z.renderbody(g);
				}
			}
		}
		
		
		//選取時，植物跟著滑鼠走
		if (pick){
			g.drawImage( PP[kinds] , x-50 , y-50 );
		}
		
		//顯示CD時間
		if(!CanItPick[1]){
			g.setColor(BBlack);
			g.fillRect(94, 9 , 47 , 67);
			g.fillRect(94, 9 , 47 , 67-CDing[1]);
		}
		if(!CanItPick[2]){
			g.setColor(BBlack);
			g.fillRect(149, 9 , 47 , 67);
			g.fillRect(149, 9 , 47 , 67-CDing[2]);
		}
		if(!CanItPick[3]){
			g.setColor(BBlack);
			g.fillRect(205, 9 , 47 , 67);
			g.fillRect(205, 9 , 47 , 67-CDing[3]);
		}
		if(!CanItPick[4]){
			g.setColor(BBlack);
			g.fillRect(261, 9 , 47 , 67);
			g.fillRect(261, 9 , 47 , 67-CDing[4]);
		}
		if(!CanItPick[5]){
			g.setColor(BBlack);
			g.fillRect(395, 9 , 47 , 67);
			g.fillRect(395, 9 , 47 , 67-CDing[5]);
		}
		if(Pause){
			g.setColor(BBlack);
			g.fillRect(0, 0, 800, 600);
			g.drawImage(PauseBack, 300, 270);
			g.drawImage(Back, 312, 288);
			g.drawImage(Quit, 426, 288);
			if(x>=300&&x<=400&&y>=270&&y<=330){
				g.drawImage(Back2, 312, 288);
			}
			if(x>=400&&x<=500&&y>=270&&y<=330){
				g.drawImage(Quit2, 426, 288);
			}
		}
		
		
		g.drawImage(mouse, x - 9, y-6);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(!Run){
			INIT();
			Run = true;
		}
		
		
		x = gc.getInput().getMouseX();
		y = gc.getInput().getMouseY();
		
		if( gc.getInput().isKeyDown(Input.KEY_1)){
			SunMoney += 10000 ;
		}
		if( gc.getInput().isKeyPressed(Input.KEY_2) && !Pause ){
			Pause = true ;
		}
		if( gc.getInput().isKeyPressed(Input.KEY_3) && Pause){
			Pause = false ;
		}
		if( gc.getInput().isKeyPressed(Input.KEY_4)){
			INIT();
			sbg.enterState(3 , new EmptyTransition(),  new VerticalSplitTransition());
		}
		
		if(!Pause){
			UnPause(gc, sbg, delta);
		}
		if(Pause&&gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(x>=300&&x<=400&&y>=270&&y<=330){
				Pause = false ;
			}
			if(x>=400&&x<=500&&y>=270&&y<=330){
				System.exit(0);
			}
		}
		

	}

	@Override
	public int getID() {
		return 4;
	}
	
	int Judge (float x , float y){
		int gg = (int)(x-Ox)/dx ;
		gg += 9*(int)((y-Oy)/dy) ;
		return gg;
	}
	int Getplants(float x ,float y){
		if ( x>=94f && x<=140f && y>=8 && y<=75){
			return 1 ;
		}
		if (x>=149f && x<=195f && y>=8 && y<=75){
			return 2 ;
		}
		if (x>=205f && x<=250f && y>=8 && y<=75){
			return 3 ;
		}
		if (x>=261f && x<=307f && y>=8 && y<=75){
			return 4 ;
		}
		if (x>=395f && x<=443f && y>=8 && y<=75){
			return 5 ;
		}
		
		
		return 0 ;
	}
	void UnPause(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		time += delta ;
		CDing[1] += 0.067f/CD[1] * delta ;
		CDing[2] += 0.067f/CD[2] * delta ;
		CDing[3] += 0.067f/CD[3] * delta ;
		CDing[4] += 0.067f/CD[4] * delta ;
		CDing[5] += 0.067f/CD[5] * delta ;
		
		for(int i=1 ; i<6 ; i++){
			if(CDing[i]>=67){
				CDing[i]=0 ;
				CanItPick[i] = true ;
			}
		}
		
		
		
		//壓左鍵的事
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if (pick)
			{
				b = Judge (x , y) ;
				if (plants[b%9][b/9]==null)
				{
					if(x>=59&&x<=787&&y>=83&&y<=559){
						switch (kinds) {
						case 1:
							plants[b%9][b/9] = new Sunflower( b%9, b/9 , time );
							SunMoney -= PlantsMoney[1];
							CDing[1] = 0 ;
							CanItPick[1] = false ;
							break;
						case 2:
							plants[b%9][b/9] = new PeaShooter( b%9, b/9 , time );
							SunMoney -= PlantsMoney[2];
							CDing[2] = 0 ;
							CanItPick[2] = false ;
							break;
						case 3:
							plants[b%9][b/9] = new Wallnut( b%9, b/9 , time );
							SunMoney -= PlantsMoney[3];
							CDing[3] = 0 ;
							CanItPick[3] = false ;
							break;
						case 4:
							plants[b%9][b/9] = new CoolPeaShooter( b%9, b/9 , time );
							SunMoney -= PlantsMoney[4];
							CDing[4] = 0 ;
							CanItPick[4] = false ;
							break;
						case 5:
							plants[b%9][b/9] = new Snap( b%9, b/9 , time );
							SunMoney -= PlantsMoney[5];
							CDing[5] = 0 ;
							CanItPick[5] = false ;
							break;

						default:
							break;
						}
					}
					pick = false ;
				}
				
			}
			else{
				kinds = Getplants(x,y) ;
				if (kinds!=0 && SunMoney >= PlantsMoney[kinds] && CanItPick[kinds]){
					pick = true ;
					
				}
				if( SunMoney < PlantsMoney[kinds] && 
						gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
					ChangeColor = true ;
				}
			}
		}
		
		
		
		if(!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			ChangeColor = false ;
		}
		
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0; j < 5; j++) {
				if (plants[i][j]!=null){
					plants[i][j].Update(delta , zombies);
					if (plants[i][j].ID == 1){
						if(plants[i][j].GetMoney()){
							SunMoney += 50 ;
						}
					}
				}
			}
		}
		
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0; j < 5; j++) {
				if (plants[i][j]!=null){
					if (plants[i][j].HP <= 0){
						plants[i][j] = null ;
					}
				}
			}
		}
		
		if(time<100000){
			if( time > Cycle && Produced == false){
				zombies.add(new Zombies(random.nextInt(5000)/1000,time));
				Produced = true ;
				if(!MusicStart){
					music.loop();
					MusicStart = true ;
				}
			}
			if(Produced == true){
				Cycle += (random.nextInt(9)+5)*800 ;
				Produced = false ;
			}
		}
		else if(time<200000){
			if( time > Cycle && Produced == false){
				zombies.add(new Zombies(random.nextInt(5000)/1000,time));
				Produced = true ;
			}
			if(Produced == true){
				Cycle += (random.nextInt(5))*500 ;
				Produced = false ;
			}
		}
		else if(time<300000){
			if( time > Cycle && Produced == false){
				zombies.add(new Zombies(random.nextInt(5000)/1000,time));
				Produced = true ;
			}
			if(Produced == true){
				Cycle += (random.nextInt(5))*300 ;
				Produced = false ;
			}
		}
		else if(time<400000){
			if( time > Cycle && Produced == false){
				zombies.add(new Zombies(random.nextInt(5000)/1000,time));
				Produced = true ;
			}
			if(Produced == true){
				Cycle += (random.nextInt(5))*200 ;
				Produced = false ;
			}
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_5)){
			zombies.add(new Zombies(random.nextInt(5000)/1000,time,random.nextInt(4000)/1000));
		}
		
		for(Zombies z:zombies){
			z.update(delta , plants);
			if(z.positionx<-50){
				INIT();
				sbg.enterState(2 , new EmptyTransition(),new FadeInTransition());
				break ;
			}
		}
		zombies.removeAll(rzombies);
		rzombies.removeAll(rzombies);
		
		for(Zombies z:zombies){
			if(z.HP <= 0){
				rzombies.add(z);
			}
		}
		zombies.removeAll(rzombies);
		rzombies.removeAll(rzombies);
		
		//取消選取、放置
		if( gc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			if(x>=59&&x<=787&&y>=83&&y<=559 && !pick && plants[b%9][b/9]!=null){
				b = Judge (x , y) ;
				map[b%9][b/9] = false ;
				SunMoney += 0.25 * plants[b%9][b/9].NeedMoney ;
				plants[b%9][b/9] = null ;
			}
			pick = false ;
		}
	}
	
	public void INIT() throws SlickException{
		time = 0 ;
		PlantsMoney = new int[6];
		PlantsMoney[1] = 50 ;
		PlantsMoney[2] = 100 ;
		PlantsMoney[3] = 50 ;
		PlantsMoney[4] = 175 ;
		PlantsMoney[5] = 20000 ;
		
		PP = new Image[6];
		PP[1] = new Image("Sunflower.png");
		PP[2] = new Image("PeaShooter.png");
		PP[3] = new Image("Wallnut.png");
		PP[4] = new Image("CoolPeaShooter.png");
		PP[5] = new Image("snap.png");
		
		plants = new Plants[9][5];
		
		//播放背景音樂
		MusicStart = false ;
		music = new Music("rrr.ogg");
		music.setVolume(1.0f);
		
		
		
		Cycle = 20000 ;
		Produced = false;
		
		CD = new float[6];
		CD[1] = 7 ;
		CD[2] = 8 ;
		CD[3] = 20 ;
		CD[4] = 7 ;
		CD[5] = 1 ;
	 	
		CDing = new float[6];
		CDing[1] = 0 ;
		CDing[2] = 0 ;
		CDing[3] = 0 ;
		CDing[4] = 0 ;
		CDing[5] = 0 ;
		
		CanItPick = new boolean[6];
		CanItPick[1] = true ;
		CanItPick[2] = true ;
		CanItPick[3] = true ;
		CanItPick[4] = true ;
		CanItPick[5] = true ;

		Pause = false ;
		
		pick = false ;
		map = new boolean[9][5];
		Kind = new int[9][5];
		time = 0 ;
		kinds = 0 ;
		SunMoney = 250 ;
		
		for(Zombies z:zombies){
			rzombies.add(z);
		}
		zombies.removeAll(rzombies);
		rzombies.removeAll(rzombies);
		
		BBlack = new Color(0,0,0,0.5f);
		
		if(Menu2.stage==2){
			Cycle = 0 ;	
		}
		if(Menu2.stage==3){
			for (int i = 0; i < 20; i++) {
				zombies.add(new Zombies(random.nextInt(5000)/1000,time,random.nextInt(4000)/1000));
			}
			Cycle = 0 ;
			SunMoney = 0 ;
		}
		if(Menu2.stage==4){
			for (int i = 0; i < 20; i++) {
				zombies.add(new Zombies(random.nextInt(5000)/1000,time,random.nextInt(4000)/1000));
			}
			Cycle = 0 ;
			SunMoney = 90000 ;
		}
		
	}
	
}
