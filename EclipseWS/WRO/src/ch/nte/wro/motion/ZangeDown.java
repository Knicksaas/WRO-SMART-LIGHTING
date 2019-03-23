package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ZangeDown {
	
	public ZangeDown() {
		if(!RoboStatus.zangeUp) {
			return;
		}
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(1000);
		mZange.forward();
		Delay.msDelay(3000);
		mZange.stop();
		mZange.close();
		RoboStatus.zangeUp = false;
	}
}
