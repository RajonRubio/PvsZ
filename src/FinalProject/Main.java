package FinalProject;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		
		try {
			AppGameContainer app = new AppGameContainer(new Main("自己的後庭花，自己守護"));
			app.setDisplayMode(800 , 600 , false);
			app.setTargetFrameRate(60);
			app.setMouseGrabbed(false);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {

		
		this.addState(new Menu());
		this.addState(new Loading());
		this.addState(new Loading2());
		this.addState(new Menu2());
		this.addState(new Stage1());
	}
}
