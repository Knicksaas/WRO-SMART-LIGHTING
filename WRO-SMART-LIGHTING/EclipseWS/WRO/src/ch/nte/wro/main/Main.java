package ch.nte.wro.main;

import ch.nte.wro.motion.ZangeVorne;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import ch.nte.wro.status.RoboData;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;

public class Main {
	
	private static int speed = 100;
	
	public static void main(String[] args) {
		SensorModes sensortest = GlobalSensors.gyroSensor;
		sensortest.getMode("Angle");
		Sound.beep();
		Button.waitForAnyPress();
		new ZangeVorne();
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		TrashAufräumenAndGreen trash = new TrashAufräumenAndGreen(mLeft, mRight, speed);
		
		if((LightArrangement.light1Green == "green") && (LightArrangement.area1Green == "green")) {
			new DriveToStartFromK1(speed, mLeft, mRight);
		} else {
			trash.cleanUp1Yellow();
		}
	}
}
