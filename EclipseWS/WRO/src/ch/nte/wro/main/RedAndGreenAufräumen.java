package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.motion.PickUpLight;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.sensors.RGBColorSensorChecker;
import ch.nte.wro.status.GlobalSensors;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class RedAndGreenAufräumen {

	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private boolean lightA;
	private boolean lightB;
	private boolean lightC;
	private boolean lightD;
	
	public RedAndGreenAufräumen(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		
		lightA = driveToLightA();
		lightB = driveToLightB();
	
	}
	
	private boolean driveToLightA() {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		LinefollowerUntilLight lul = new LinefollowerUntilLight(speed, mLeft, mRight, 60);
		if(lul.isThereALight()) {
			RGBColorSensorChecker rgb = new RGBColorSensorChecker(GlobalSensors.colorSensorFront);
			rgb.checkSensor();
			new PickUpLight(speed, mLeft, mRight);
			return true;
		}
		return false;
	}
	
	private boolean driveToLightB() {
		
		return false;
	}
}
