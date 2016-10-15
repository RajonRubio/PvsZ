package FinalProject;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Loading2 extends BasicGameState{
	Image ZombiesWon ;
	Image Quit ;
	Image Retry ;
	Image mouse ;
	float Mx;
	float My;
	
	int time ;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		ZombiesWon = new Image("ZombiesWon.jpg");
		Quit = new Image("Quit.png");
		Retry = new Image("Retry.png");
		mouse = new Image("mouse.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(ZombiesWon, 118, 66);
		if(time>2000){
			g.drawImage(Quit, 124, 122);
			g.drawImage(Retry, 420, 122);
		}
		g.drawImage(mouse, Mx - 9, My-6);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Mx = gc.getInput().getMouseX();
		My = gc.getInput().getMouseY();
		time += delta ;
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(Mx>124&&Mx<380&&My>122&&My<378){
				System.exit(0);		
			}
			if(Mx>420&&Mx<576&&My>122&&My<378){
				sbg.enterState(4,new FadeOutTransition(), new BlobbyTransition());		
			}
		}
		
	}
	
	@Override
	public int getID() {
		return 2;
	}
}
