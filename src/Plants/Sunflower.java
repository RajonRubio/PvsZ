package Plants;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zombies.Zombies;

public class Sunflower extends Plants{
	public int Displacement ;
	public int To0 ;
	public float SunX ;
	public float SunY ;
	int pp ;
	public float Randomx ;
	public boolean Fight ;
	public Random random = new Random() ;
	
	
	public Sunflower(int x , int y , int t) throws SlickException{
		image = new Image("Sunflower.png");
		HP = 300 ;
		Locationx = x ;
		Locationy = y ;
		ID = 1 ;
		IsPlace = true ;
		PlaceTime = t ;
		NeedMoney = 50 ;
		To0 = 0;
		Randomx = random.nextFloat()*2-1 ;
		pro = new Image("Sun.png");
		pro.setAlpha(0.8f);
	}
	public void Update(int delta , ArrayList<Zombies> zombies){
		PassTime += delta ;
		Displacement +=  delta ;
		Fight = false ;
		
		
		if( PassTime % 24000 < 24000 && PassTime % 24000 >= 18600 && To0 == 0) {
			Displacement = 0 ;
			To0 = 1 ;
		}
		if( -Displacement / 8 + Displacement*Displacement /8000 > 40 && To0 == 1 ){
			SunX = 45+82*Locationx+(float)Displacement*Randomx/50;
			SunY = 50+Locationy*98-Displacement / 8 + Displacement*Displacement /8000;
			To0 = 2 ;
		}
		if( PassTime % 24000 < 24000 && PassTime % 24000 >= 23980 && To0 == 2) {
			To0 = 3 ;
			pp = PassTime ;
		}
		if( To0 == 3 && pp != PassTime){
			To0 = 0 ;
		}
		if( PassTime % 24000 < 24000 && PassTime % 24000 >= 18600 ){
			Fight = true ;
		}
		
		if ( -Displacement / 8 + Displacement*Displacement /8000 > 40 ){
			PeaPositionx = SunX ;
			PeaPositiony = SunY ;
		}
		else {
			PeaPositionx = 45+82*Locationx + Displacement*Randomx/50 ;
			PeaPositiony = 50+Locationy*98-Displacement / 8 
					+ Displacement*Displacement /8000 ;
		}
		Positionx = 40+Locationx*82 ;
		Positiony = 75+Locationy*98 ;
		
	}
	
	public void MoneyUpdate(int SunMoney){
		
	}
	public boolean GetMoney(){
		if(To0 == 3){
			return true ;
		}
		return false ;
	}
	
	public void renderbody(Graphics g){
		g.drawImage( image , 40+Locationx*82 , 75+Locationy*98 );
	}
	
	public void renderprojecttile(Graphics g){
		if( Fight ){
				g.drawImage( pro , PeaPositionx , PeaPositiony );
		}
	}
}
