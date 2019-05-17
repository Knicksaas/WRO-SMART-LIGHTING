package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class UnloadLight2 {

	public UnloadLight2(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
	
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*1100)/speed);
		new motorsOFF(mLeft, mRight);
		
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(1000);
		mZange.backward();
		Delay.msDelay(500);
		mZange.stop();
		
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*1800)/speed);
		new motorsOFF(mLeft, mRight);
		
		mZange.setSpeed(1000);
		mZange.backward();
		Delay.msDelay(2500);
		mZange.stop();
		mZange.close();
		RoboStatus.zangeUp = true;
	}
	
}
