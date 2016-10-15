package Plants;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zombies.Zombies;

public class CoolPeaShooter extends Plants{
	public float Displacement ;
	public boolean To0 ;
	public boolean Fight ;
	public boolean Hit ;
	
	public CoolPeaShooter (int x , int y , int t) throws SlickException{
		image = new Image("CoolPeaShooter.png");
		HP = 300 ;
		Locationx = x ;
		Locationy = y ;
		ID = 4 ;
		IsPlace = true ;
		PlaceTime = t ;
		NeedMoney = 175 ;
		PassTime = 0 ;
		To0 = true ;
		pro = new Image("ProjectileSnowPea.png");
		Fight = false ;
		Hit = false ;
	}

	public void Update(int delta , ArrayList<Zombies> zombies){
		PassTime += delta ;
		Displacement += delta *2 /3 ;
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
					z.Cool = true ;
					z.image.setImageColor(0.5f, 0.5f, 0.7f);
					
					z.HP -= 25 ;
				}
			}
		}
	}
	
	public boolean HaveZombies(ArrayList<Zombies> zombies){
		return false;	
	}

	public void renderbody(Graphics g){
		g.drawImage( image , Positionx , Positiony );
	}
	public void renderprojecttile(Graphics g){
		if( Fight && !Hit){
			g.drawImage(pro , PeaPositionx , PeaPositiony );
		}
	}
}
