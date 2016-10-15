package Plants;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zombies.Zombies;

public class PeaShooter extends Plants{
	public float Displacement ;
	public boolean To0 ;
	public boolean Fight ;
	public boolean Hit ;
	
	public PeaShooter (int x , int y , int t) throws SlickException{
		image = new Image("PeaShooter.png");
		HP = 300 ;
		Locationx = x ;
		Locationy = y ;
		ID = 2 ;
		IsPlace = true ;
		PlaceTime = t ;
		NeedMoney = 100 ;
		pro = new Image("ProjectilePea.png");
		To0 = true ;
		Fight = false ;
		Hit = false ;
	}
	
	public void Update(int delta , ArrayList<Zombies> zombies){
		PassTime += delta ;
		Displacement += delta ;
		Fight = false ;

		for(Zombies z:zombies){
			if(z.Locationy == Locationy){
				Fight = true ;
			}
		}
		
		if( PassTime % 1000 < 20 && To0 == true && Fight == true ) {
			Displacement = 0 ;
			To0 = false ;
		}	
		if( PassTime % 1000 > 20 && To0 == false ) {
			To0 = true ;
			Hit = false ;
		}
		PeaPositionx = 90+82*Locationx + Displacement ;
		PeaPositiony = 90+Locationy*98 ;
		Positionx = 40+Locationx*82 ;
		Positiony = 75+Locationy*98 ;
		
		for(Zombies z:zombies){
			if(z.Locationy == Locationy){
				if (PeaPositionx - 50 > z.positionx && !Hit ) {
					Hit = true ;
					z.CoolHitplus += 1 ;
					z.HP -= 100 ;
				}
			}
		}
		
	
	}
	public void renderbody(Graphics g){
		g.drawImage( image , Positionx , Positiony );
	}
	public void renderprojecttile(Graphics g){
		if( Fight && !Hit  ){
			g.drawImage(pro , PeaPositionx , PeaPositiony );
		}
	}
	
}
