package ch.nte.wro.linefollower;

import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.sensors.RGBColorSensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LinefollowerUntilLightAndTime {
	
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private boolean running = false;
	private boolean isThereABlock = false;
	private int kp; 
	private float ki = 0.0F;
	private float errI = 0;
	private int delay = 0;
	private int delayCounter = 0;

	public LinefollowerUntilLightAndTime(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, int kp, int delay) {
		this.kp = kp;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.delay = (50*delay)/speed;;
		followLine();
	}
	
	private void followLine() {
		running = true;
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		RGBColorSensorChecker cFront = new RGBColorSensorChecker(GlobalSensors.colorSensorFront);
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		} else {
			mLeft.forward();
			mRight.forward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		}
		
		while(running) {
			lLeft.checkSensor();
			lRight.checkSensor();
			cFront.checkSensor();
			
			float a = lLeft.getIntensity();
			float b = lRight.getIntensity();
			float errP = a-b;
			
			errI = errI + errP*ki;
			
			LCD.drawString(String.valueOf(errI), 0, 0);
			if (speed <= 200) {
				Delay.msDelay(5);
			}
			else {
				Delay.msDelay(2);
			}
			
			float err = errP + errI;
			
			if(err < 0) {
				mRight.setSpeed(Math.round(speed-(err*kp)));
				mLeft.setSpeed(speed);
				//Evtl delay
			} else {
				mLeft.setSpeed(Math.round(speed+(err*kp)));
				mRight.setSpeed(speed);
				//Evtl delay
			}
			
			if(cFront.isThereABlock()) {
				new motorsOFF(mLeft, mRight);
				running = false;
				isThereABlock = true;
			}
			
			if((lLeft.getIntensity() + lRight.getIntensity()) > 0.9) {
				new motorsOFF(mLeft, mRight);
				running = false;
				isThereABlock = false;
			}
			
			if(delayCounter < delay) {
				Delay.msDelay(1);
				delayCounter++;
			} else {
				new motorsOFF(mLeft, mRight);
				running = false;
				isThereABlock = false;
				Sound.beep();
			}
		}
	}
	
	public boolean isThereALight() {
		return isThereABlock;
	}
}
