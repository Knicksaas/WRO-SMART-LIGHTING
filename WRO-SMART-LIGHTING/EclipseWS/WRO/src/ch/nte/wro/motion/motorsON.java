package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import lejos.robotics.RegulatedMotor;

public class motorsON {
	
	public motorsON(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, boolean forward) {
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
	}

}
