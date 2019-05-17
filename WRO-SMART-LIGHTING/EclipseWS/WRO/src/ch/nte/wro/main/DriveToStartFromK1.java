package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.ZangeHinten;
import lejos.robotics.RegulatedMotor;

public class DriveToStartFromK1 {

	public DriveToStartFromK1(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new Turn(speed, -33, mLeft, mRight);
		new ZangeHinten();
		new LinefollowerWithTime(speed*2, mLeft, mRight, 1, 250);
		new LinefollowerWithTime(speed*3, mLeft, mRight, 1, 250);
		new LinefollowerWithTime(speed*4, mLeft, mRight, 1, 500);
		new LinefollowerWithTime(speed*4, mLeft, mRight, 1, 1350);
	}
	
}
