package ch.nte.wro.motion;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.RoboData;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;

public class StopAtNextLine {
	
	private boolean foundALine = false;
	
	public StopAtNextLine(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, SensorModes sensorModes) {
		LightIntensitySensorChecker lic = new LightIntensitySensorChecker(sensorModes);
		
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
		} else {
			mLeft.forward();
			mRight.backward();
		}
		
		while(!foundALine) {
			lic.checkSensor();
			if(lic.getIntensity() < 0.10) {
				foundALine = true;
				new motorsOFF(mLeft, mRight);
			}
		}
	}
}
