package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ZangeUp {
	
	public ZangeUp() {
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(500);
		mZange.backward();
		Delay.msDelay(4000);
		mZange.stop();
		mZange.close();
	}
}