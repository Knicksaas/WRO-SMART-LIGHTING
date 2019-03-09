package ch.nte.wro.motion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class UnloadLight2 {

	public UnloadLight2(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new ZangeDown();
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new ZangeUp();
	}
	
}
