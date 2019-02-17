package ch.nte.wro.test;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class LightIntensityTester {

	public static void main(String[] args) {
		LightIntensitySensorChecker l1 = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker l2 = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		
		while(true) {
			l1.checkSensor();
			l2.checkSensor();
			LCD.clear();
			LCD.drawString(String.valueOf(l1.getIntensity()), 0, 0);
			LCD.drawString(String.valueOf(l2.getIntensity()), 1, 1);
			Delay.msDelay(100);
		}
	}

}
