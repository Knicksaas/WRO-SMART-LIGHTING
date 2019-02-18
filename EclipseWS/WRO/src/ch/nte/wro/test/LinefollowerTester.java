package ch.nte.wro.test;

import ch.nte.wro.linefollower.Linefollower2;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class LinefollowerTester {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);

		new Linefollower2(100, mLeft, mRight, 100);
	}

}