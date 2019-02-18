package ch.nte.wro.test;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Ev3MiddleRegulatedMotortester {

	public static void main(String[] args) {
		RegulatedMotor m3 = new EV3MediumRegulatedMotor(MotorPort.C);
		m3.setSpeed(500);
		m3.forward();
		Delay.msDelay(4000);
		m3.stop();
		m3.close();
	}

}