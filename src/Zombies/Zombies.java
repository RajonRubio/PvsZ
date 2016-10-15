package Zombies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Plants.Plants;

public class Zombies {
	public Image image ;
	public int HP ;
	public float Locationx ;
	public int Locationy ;
	public float Displacement ;
	public float positionx ;
	public float positiony ;
	public int CoolHitplus ;
	public float CoolHitMultiply ;
	public boolean Cool ;
	public float DisplacementMultiply ;
	
	public Zombies(int y , int time) throws SlickException{
		
		if(time < 80000){
			HP = 500 ;	
			image = new Image("Zombie.png");
			DisplacementMultiply = 1 ;
		}
		else if(time < 160000){
			HP = 500 ;
			image = new Image("RobberZombie.png");
			DisplacementMultiply = 4 ;
		}
		else if(time < 240000){
			HP = 2000 ;
			image = new Image("BucketZombie.png");
			DisplacementMultiply = 2 ;
		}
		else{
			image = new Image("Zombie_football.png");
			HP = 4000 ;
			DisplacementMultiply = 3 ;
		}
		
		Locationy = y  ;
		CoolHitMultiply = 1 ;
		CoolHitplus = 0 ;
		Cool = false ;
	}
	
	public Zombies(int y , int time ,int kind) throws SlickException{
		switch (kind) {
		case 0:
			HP = 500 ;	
			image = new Image("Zombie.png");
			DisplacementMultiply = 1 ;
			break;
		case 1:
			HP = 500 ;
			image = new Image("RobberZombie.png");
			DisplacementMultiply = 4 ;
			break;
		case 2:
			HP = 2000 ;
			image = new Image("BucketZombie.png");
			DisplacementMultiply = 1 ;
			break;
		case 3:
			image = new Image("Zombie_football.png");
			HP = 4000 ;
			DisplacementMultiply = 2 ;
			break;
		

		default:
			break;
		}
		
		Locationy = y  ;
		CoolHitMultiply = 1 ;
		CoolHitplus = 0 ;
		Cool = false ;
		
		
	}
	
	public void update(int delta ,Plants[][] plants){
		Displacement += delta/30f ;
		
		if(Cool){
			CoolHitMultiply += delta/30f ;
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				if(plants[i][j]!=null){
					if(plants[i][j].Positionx <= positionx + 20 &&
					   plants[i][j].Positionx >= positionx && 
					   plants[i][j].Locationy == Locationy){
						Displacement -= delta/3f ;
						plants[i][j].HP -= 10 ;  
					}
				}
			}
		}
		if(!Cool){
			positionx = 850 - Displacement*DisplacementMultiply + 10*CoolHitplus ;
			positiony = -60+(Locationy+1)*98 ;
		}
		else {
			positionx = 850 - Displacement*DisplacementMultiply + 5*CoolHitplus + CoolHitMultiply*0.6f;
			positiony = -60+(Locationy+1)*98 ;
		}	
		
		
	}
	public void renderbody(Graphics g){
		
		g.drawImage( image , positionx , positiony );
	}
}
