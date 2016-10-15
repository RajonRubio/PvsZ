package FinalProject;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu2 extends BasicGameState{

	
	static public int stage ;
	Image BackGround ;
	Image nature ;
	Image mouse ;
	Image StartAdventure ;
	Image StartAdventure2 ;
	Image Survival ;
	Image Survival2 ;
	Image Vasebreaker ;
	Image Vasebreaker2 ;
	Image Challenges ;
	Image Challenges2 ;
	Polygon AdventureButtom ;
	Polygon MiniGameButtom ;
	Polygon PuzzleButtom ;
	Polygon SurvivalButtom ;
	Rectangle Mouse ;
	
	
	
	float Mx ;
	float My ;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		BackGround = new Image("BackGround.png");
		nature = new Image("nature.jpg");
		mouse = new Image("mouse.png");
		StartAdventure = new Image("SelectorScreen_StartAdventure_Button1.png");
		StartAdventure2 = new Image("SelectorScreen_StartAdventure_Highlight.png");
		Survival = new Image("SelectorScreen_Survival_button.png");
		Survival2 = new Image("SelectorScreen_Survival_highlight.png");
		Vasebreaker = new Image("SelectorScreen_Vasebreaker_button.png");
		Vasebreaker2 = new Image("SelectorScreen_vasebreaker_highlight.png");
		Challenges = new Image("SelectorScreen_Challenges_button.png");
		Challenges2 = new Image("SelectorScreen_Challenges_highlight.png");
		
		Mouse = new Rectangle(0, 0, 1, 1);
		
		
		AdventureButtom = new Polygon();
		MiniGameButtom = new Polygon();
		PuzzleButtom = new Polygon();
		SurvivalButtom = new Polygon();
		
		
		
		AdventureButtom.addPoint(424,115);
		AdventureButtom.addPoint(418,182);
		AdventureButtom.addPoint(722,226);
		AdventureButtom.addPoint(739,143);
		AdventureButtom.addPoint(661,128);
		AdventureButtom.addPoint(635,91);
		AdventureButtom.addPoint(513,87);
		AdventureButtom.addPoint(478,114);
		
		MiniGameButtom.addPoint(418,193);
		MiniGameButtom.addPoint(422,262);
		MiniGameButtom.addPoint(705,316);
		MiniGameButtom.addPoint(721,242);
		
		PuzzleButtom.addPoint(429,277);
		PuzzleButtom.addPoint(429,334);
		PuzzleButtom.addPoint(690,393);
		PuzzleButtom.addPoint(707,326);
		
		SurvivalButtom.addPoint(431,349);
		SurvivalButtom.addPoint(433,401);
		SurvivalButtom.addPoint(675,466);
		SurvivalButtom.addPoint(689,405);
		
		stage = 1 ;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(nature, 0, 0);
		g.drawImage(BackGround,0, 0);
		 
		g.drawImage(StartAdventure,413,85);
		g.drawImage(Survival, 410, 185);
		g.drawImage(Challenges, 421, 270);
		g.drawImage(Vasebreaker, 421, 341);
		if(AdventureButtom.contains(Mouse)){
			g.drawImage(StartAdventure2,413,85);
		}
		if(MiniGameButtom.contains(Mouse)){
			g.drawImage(Survival2, 410, 185);
		}
		if(PuzzleButtom.contains(Mouse)){
			g.drawImage(Challenges2, 421, 270);
		}
		if(SurvivalButtom.contains(Mouse)){
			g.drawImage(Vasebreaker2, 421, 341);
		}
		
		g.drawImage(mouse, Mx - 9, My-6);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		Mx = gc.getInput().getMouseX();
		My = gc.getInput().getMouseY();
		Mouse.setX(Mx);
		Mouse.setY(My);
		
		
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			
			if(AdventureButtom.contains(Mouse)){
				stage = 1 ;
				Stage1.Run = false;
				sbg.enterState(4,new FadeOutTransition(), new BlobbyTransition());
			}
			if(MiniGameButtom.contains(Mouse)){
				stage = 2 ;
				Stage1.Run = false;
				sbg.enterState(4,new FadeOutTransition(), new BlobbyTransition());
			}
			if(PuzzleButtom.contains(Mouse)){
				stage = 3 ;
				Stage1.Run = false;
				sbg.enterState(4,new FadeOutTransition(), new BlobbyTransition());
			}
			if(SurvivalButtom.contains(Mouse)){
				stage = 4 ;
				Stage1.Run = false;
				sbg.enterState(4,new FadeOutTransition(), new BlobbyTransition());
			}
			
		}
	}

	@Override
	public int getID() {
		return 3;
	}

}
