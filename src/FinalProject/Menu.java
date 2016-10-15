package FinalProject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.RotateTransition;

public class Menu extends BasicGameState{
	
	Image titlescreen ;
	Image VS ;
	int time;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		titlescreen = new Image("titlescreen.jpg");
		VS = new Image("VS.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
			g.drawImage( titlescreen , 0 , 0 ); //µe­I´º
			g.drawImage( VS , 250 , 200 );
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
			time += delta;
			if(time>3000){
				sbg.enterState(1 , new EmptyTransition(), new RotateTransition());	
			}
	}

	@Override
	public int getID() {
		return 0;
	}
}
