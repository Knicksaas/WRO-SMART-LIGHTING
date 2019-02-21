package ch.nte.wro.status;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.SensorModes;

public class GlobalSensors {
	
	public static SensorModes colorSensorLeft =  new EV3ColorSensor(LocalEV3.get().getPort("S1"));
	public static SensorModes colorSensorRight = new EV3ColorSensor(LocalEV3.get().getPort("S2"));
	public static SensorModes gyroSensor =  new EV3GyroSensor(LocalEV3.get().getPort("S3"));
	public static SensorModes colorSensorFront = new EV3ColorSensor(LocalEV3.get().getPort("S4"));
}
