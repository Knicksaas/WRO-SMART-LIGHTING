package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ZangeDown {
	
	public ZangeDown() {
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(500);
		mZange.forward();
		Delay.msDelay(4000);
		mZange.stop();
		mZange.close();
	}
}