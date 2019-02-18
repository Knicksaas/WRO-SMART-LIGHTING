package ch.nte.wro.test;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/*import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
*/

public class Main {

	public static void main(String[] args) {
		
		SensorModes sensor = new EV3GyroSensor(LocalEV3.get().getPort("S3"));
		SampleProvider gyro = sensor.getMode("Angle");
		float[] sample = new float[gyro.sampleSize()];
		
		while(true) {
			gyro.fetchSample(sample, 0);
			
			LCD.clear();
			LCD.drawString(String.valueOf(sample[0]), 0, 1);
			Delay.msDelay(1000);
		}
		
		/*
		Sound.beep();
		
		Port port = LocalEV3.get().getPort("S1");
		SensorModes sensor = new EV3TouchSensor(port);
		SampleProvider touch = sensor.getMode("Touch");
		float[] sample = new float[touch.sampleSize()];
		
		touch.fetchSample(sample, 0);
		
		System.out.println(sample[0]);
		
		RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.C);
		
		m1.setSpeed(300);
		m2.setSpeed(300);
		
		
		m1.forward();
		m2.forward();
		
		Delay.msDelay(2000);
		
		m1.setSpeed(0);
		m2.setSpeed(0);
		
		m1.stop();
		m2.stop();
		
		m1.close();
		m2.close();
		*/
		
		
	}

}
