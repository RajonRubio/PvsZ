package Plants;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zombies.Zombies;

public class Snap extends Plants{
	
	public Snap (int x , int y , int t) throws SlickException{
		image = new Image("snap.png");
		HP = 1000000 ;
		Locationx = x ;
		Locationy = y ;
		ID = 5 ;
		IsPlace = true ;
		PlaceTime = t ;
		NeedMoney = 20000 ;
	}

	public void Update(int delta , ArrayList<Zombies> zombies){
		PassTime += delta ;
		Positionx = 45+Locationx*82 ;
		Positiony = 75+Locationy*98 ;
		for(Zombies z:zombies){
			if(Locationy==z.Locationy&&Positionx>=z.positionx){
				z.HP -=200000 ;
			}
		}
	}

	public void renderbody(Graphics g){
			g.drawImage( image , Positionx , Positiony );
	}
	public void renderprojecttile(Graphics g){
		
	}
}