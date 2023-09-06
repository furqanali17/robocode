package teamcrobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.HitWallEvent; 
import robocode.WinEvent; 
import static robocode.util.Utils.normalRelativeAngleDegrees; 

import java.awt.*;

public class TheCrow_n extends Robot {

	int turnDirection = 1; // Clockwise or counterclockwise
	
	/**
	 * run: Spin around looking for a target
	 */
	public void run() {
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.yellow);
		setBulletColor(Color.red); 		
		
		while (true) {
			turnGunRight(360); // Scans the 
				
		}
		
	} // end of run
	
/*
 * In this method the robot will scan the arena and look for a robot to fire and ram
 */
	public void onScannedRobot(ScannedRobotEvent e) {
		
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());


		if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
		}


		if ((getEnergy() > 25)|| (e.getDistance() < 50)) {
		fire (3);		
		} else if ((getEnergy() > 50)|| (e.getDistance() < 70)) {
		fire (2); 
		}
		else {
		fire (1);
		}


		if (getEnergy() < e.getEnergy()){
			if (e.getBearing() >= 0) {
			turnDirection = -1;
			turnRight(e.getBearing());
			ahead(90);
			scan();
			fire(2); 
			} else {
			turnDirection = 1;	
			} 

		}
	
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;	
		}
	
		turnRight(e.getBearing());
		ahead(90);	
		scan(); 
	 
	} // end of onScannedRobot
	

/*
* This method will make the robot turn to face the robot it collided with, fire hard, and ram it again!
*/
	public void onHitRobot(HitRobotEvent e) {

		if (e.getBearing() >= 0) { // bearing is where the other robot is facing
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());
		

		if (getEnergy() > 30) {
			fire(3);
		}
		 else if (getEnergy() > 15) {
			fire(2);
		}
		 else if (getEnergy() > 0) {
			fire(1);
		}
		
		ahead(30); // Ram him again!
	} // end of onHitRobot
	
/*
 * this method will turn the robot to the opposite direction when it hits a wall
 */
	public void onHitWall(HitWallEvent e) {
						
		int corner = 0;
		turnRight (normalRelativeAngleDegrees (corner+getHeading()));	
		ahead(50);	
	
	} // end of onHitWall
	
	

/*
 * this method will make a winning dance
 */
	public void onWin(WinEvent e) {
			turnRight(90);
			turnLeft(90);
			turnRight(135);
			turnLeft(135);		

	} // end of onWin
	
} //end of class
