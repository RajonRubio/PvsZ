package FinalProject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;


public class Loading  extends BasicGameState{
	static int x = 0 ; 
	static float rotation = 0 ;
	float Mx ;
	float My ;
	Image PvZ_Logo ;
	Image LoadBar_dirt ;
	Image LoadBar_grass ;
	Image SodRollCap ;
	Image Store_FirstAidWallnutIcon ;
	Image mouse ;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		mouse = new Image("mouse.png");
		PvZ_Logo = new Image("Loading/PvZ_Logo.jpg");     
		LoadBar_dirt = new Image("Loading/LoadBar_dirt.png");
		LoadBar_grass = new Image("Loading/LoadBar_grass.png");
		SodRollCap = new Image("Loading/SodRollCap.png");
		Store_FirstAidWallnutIcon = new Image("Loading/Store_FirstAidWallnutIcon.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(PvZ_Logo, 50, 125);
		g.drawImage(Store_FirstAidWallnutIcon, 275, 300);
		g.drawImage(Store_FirstAidWallnutIcon, 475, 300);
		g.setColor (Color.white);
		g.drawString("Loading......", 340, 325);
		g.drawImage(LoadBar_dirt, 243, 450);
		g.drawImage(LoadBar_grass, 243, 437, 243+rotation*0.16f, 470, 0, 0, rotation*0.16f, 33);
		
		
		if(230 + rotation*0.16f < 540){
			g.drawImage(SodRollCap, 215 + rotation*0.16f, 400);
		}
		if(230 + rotation*0.16f < 540){
			if( x % 240 >= 0 && x % 240 < 60){
				g.drawString("LOADING", 355, 465);
			}
			else if(x % 240 >= 60 && x % 240 < 120){
				g.drawString("LOADING.", 355, 465);
			}
			else if(x % 240 >= 120 && x % 240 < 180){
				g.drawString("LOADING..", 355, 465);
			}
			else{
				g.drawString("LOADING...", 355, 465);
			}
		}
		
		
		if(230 + rotation*0.16f >= 540){
			g.drawString("S T A R T", 355, 468);
			if(Mx>=246&&Mx<=545&&My>=460&&My<=487){
				g.setColor(Color.green);
				g.drawString("S T A R T", 355, 468);
				g.setColor(Color.white);
			}
		}
		g.drawImage(mouse, Mx - 9, My-6);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Mx = gc.getInput().getMouseX();
		My = gc.getInput().getMouseY();
		
		x += delta ;
		rotation += delta * 0.5f ;
		if (230 + rotation*0.16f >= 540){
			rotation = 1937.5f ;
		}
			
		/*if (230 + rotation*0.16f > 540){
			sbg.enterState(1 , new EmptyTransition(), new BlobbyTransition());
		}*/
		SodRollCap.setRotation(rotation);
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(Mx>=246&&Mx<=545&&My>=460&&My<=487&&rotation == 1937.5f){
				sbg.enterState(3 , new EmptyTransition(),new EmptyTransition());
			}
		}
	}
	
	@Override
	public int getID() {
		return 1;
	}
}
