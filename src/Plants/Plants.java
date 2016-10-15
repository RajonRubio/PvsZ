package Plants;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Zombies.Zombies;

public class Plants {
	public int HP ;
	public int Locationx ;
	public int Locationy ;
	public int ID ;
	public boolean IsPlace ;
	public boolean To0 ;
	public int PlaceTime ;
	public Image image ;
	public int NeedMoney ;
	public int PassTime ;
	public Image pro;
	public float PeaPositionx ;
	public float PeaPositiony ;
	public float Positionx ;
	public float Positiony ;
	
	
	public Plants(){
		HP = 500 ;
		Locationx = 0 ;
		Locationy = 0 ;
		ID = 0 ;
		IsPlace = false ;
		To0 = false ;
		PassTime = 0;
	}
	
	public void Update(int delta , ArrayList<Zombies> zombies){
	}
	public void renderbody(Graphics g){
	}
	public void renderprojecttile(Graphics g){	
	}

	public boolean GetMoney() {
		return false;
	}
}
