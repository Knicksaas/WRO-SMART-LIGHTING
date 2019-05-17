package ch.nte.wro.linefollower;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineIntensityChecker {
	
	private int speed;
	private float lowestIntensity = 10;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private int zyklus1;
	private int zyklus2;
	
	public LineIntensityChecker(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
	}
	
	public float checkLineIntensity(SensorModes lightIntensitySensorLeft, SensorModes lightIntensitySensorRight) {
		
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		
		if(!RoboData.invertMotorDirection) {
			mLeft.forward();
			mRight.forward();
		} else {
			mLeft.backward();
			mRight.backward();
		}
		
		while(zyklus1 < speed*40) {
			zyklus1++;
			Delay.msDelay(1);
			lLeft.checkSensor();
			lRight.checkSensor();
			if(lLeft.getIntensity() < lowestIntensity) {
				lowestIntensity = lLeft.getIntensity();
			}
			if(lRight.getIntensity() < lowestIntensity) {
				lowestIntensity = lRight.getIntensity();
			}
		}
		
		mLeft.setSpeed(0);
		mRight.setSpeed(0);
		mLeft.stop();
		mRight.stop();
		
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		
		if(!RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
		} else {
			mLeft.forward();
			mRight.forward();
		}
		
		while(zyklus2 < speed*40) {
			zyklus2++;
			Delay.msDelay(1);
			lLeft.checkSensor();
			lRight.checkSensor();
			if(lLeft.getIntensity() < lowestIntensity) {
				lowestIntensity = lLeft.getIntensity();
			}
			if(lRight.getIntensity() < lowestIntensity) {
				lowestIntensity = lRight.getIntensity();
			}
		}
		
		zyklus1 = 0;
		zyklus2 = 0;
		
		mLeft.setSpeed(0);
		mRight.setSpeed(0);
		mLeft.stop();
		mRight.stop();
	
		return lowestIntensity;
	}
}
