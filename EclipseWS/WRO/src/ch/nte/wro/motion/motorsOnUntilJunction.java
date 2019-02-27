package ch.nte.wro.motion;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.robotics.RegulatedMotor;

public class motorsOnUntilJunction {
	
	public motorsOnUntilJunction(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, boolean forward) {
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			if(forward) {
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				mLeft.backward();
				mRight.backward();
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
			} else {
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				mLeft.forward();
				mRight.forward();
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
			}
		} else {
			if(forward) {
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				mLeft.forward();
				mRight.forward();
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
			} else {
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				mLeft.backward();
				mRight.backward();
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
			}
		}
	
		lLeft.checkSensor();
		lRight.checkSensor();
		boolean running = true;
		while(running) {
			lLeft.checkSensor();
			lRight.checkSensor();
			if((lLeft.getIntensity() + lRight.getIntensity())< 0.1) {
				new motorsOFF(mLeft, mRight);
				running = false;
			}
		}
		new motorsOFF(mLeft, mRight);
	}
}