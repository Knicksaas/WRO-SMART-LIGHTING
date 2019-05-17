package ch.nte.wro.motion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class PickUpLight {
	
	public PickUpLight(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((100*1500)/(speed));
		new motorsOFF(mLeft, mRight);
		new ZangeUp();
		Delay.msDelay(800);
	}
}
