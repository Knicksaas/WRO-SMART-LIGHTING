package ch.nte.wro.main;

import ch.nte.wro.linefollower.Linefollower;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class Main {
	
	private static int speed = 50;

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		new Linefollower(speed, mLeft, mRight);
	}
}