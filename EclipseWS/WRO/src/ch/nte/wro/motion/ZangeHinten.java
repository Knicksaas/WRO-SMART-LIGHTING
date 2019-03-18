package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ZangeHinten {
	
	public ZangeHinten() {
		if(RoboStatus.zangeUp) {
			RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
			mZange.setSpeed(1000);
			mZange.forward();
			Delay.msDelay(2500);
			mZange.stop();
			mZange.close();
			RoboStatus.zangeUp = false;
		} else {
			RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
			mZange.setSpeed(1000);
			mZange.forward();
			Delay.msDelay(500);
			mZange.stop();
			mZange.close();
			RoboStatus.zangeUp = false;
		}
		
	}

}
