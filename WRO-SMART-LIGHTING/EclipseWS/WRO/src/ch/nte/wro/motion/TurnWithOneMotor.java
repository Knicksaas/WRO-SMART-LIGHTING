package ch.nte.wro.motion;

import ch.nte.wro.sensors.GyroSensorChecker;
import ch.nte.wro.status.RoboData;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;

public class TurnWithOneMotor {
	
	private float angle;
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private float anfangswertSensor = 0;
	
	public TurnWithOneMotor(int speed, float angle, RegulatedMotor mLeft, RegulatedMotor mRight) {
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		this.angle = angle;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		exec();
	}

	private void exec() {
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		GyroSensorChecker gs = new GyroSensorChecker();
		gs.checkSensor();
		anfangswertSensor = gs.getAngle();
		
		angle = anfangswertSensor + angle;
		
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		
		if(angle<anfangswertSensor) {
			if(RoboData.invertMotorDirection) {
				//mRight.forward();
				mLeft.backward();
			} else {
				//mRight.backward();
				mLeft.forward();
			}
		} else {
			if(RoboData.invertMotorDirection) {
				mRight.backward();
				//mLeft.forward();
			} else {
				mRight.forward();
				//mLeft.backward();
			}
		}
		gs.checkSensor();
		LCD.clear();
		LCD.drawString(String.valueOf(gs.getAngle()), 0, 0);
		
		if(angle < anfangswertSensor) {
			while(gs.getAngle() > angle) {
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		} else {
			while(gs.getAngle() < angle) {
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		}
		
		new motorsOFF(mLeft, mRight);
	}
}
