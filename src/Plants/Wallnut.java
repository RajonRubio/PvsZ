 package Plants;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zombies.Zombies;

public class Wallnut extends Plants{
	Image Wallnut_cracked1 ;
	Image Wallnut_cracked2 ;
	
	
	public Wallnut (int x , int y , int t) throws SlickException{
		image = new Image("Wallnut.png");
		Wallnut_cracked1 = new Image("Wallnut_cracked1.png");
		Wallnut_cracked2 = new Image("Wallnut_cracked2.png");
		
		HP = 3000 ;
		Locationx = x ;
		Locationy = y ;
		ID = 3 ;
		IsPlace = true ;
		PlaceTime = t ;
		NeedMoney = 50 ;
	}

	public void Update(int delta , ArrayList<Zombies> zombies){
		PassTime += delta ;
		Positionx = 40+Locationx*82 ;
		Positiony = 75+Locationy*98 ;
	}

	public void renderbody(Graphics g){
		if(HP>2100){
			g.drawImage( image , Positionx , Positiony );
		}
		else if(HP > 1200){
			g.drawImage( Wallnut_cracked1 , Positionx , Positiony );
		}
		else {
			g.drawImage( Wallnut_cracked2 , Positionx , Positiony );
		}
	}
	
	public void renderprojecttile(Graphics g){	
	}
}
