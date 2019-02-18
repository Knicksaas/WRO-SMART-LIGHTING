package ch.nte.wro.sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class TouchSensorChecker {
	
	private Port port;
	private SampleProvider touch;
	private float[] status;
	
	public TouchSensorChecker(String port) {
		if(port.equalsIgnoreCase("s1")) {
			this.port = LocalEV3.get().getPort("S1");
		}
		if(port.equalsIgnoreCase("s2")) {
			this.port = LocalEV3.get().getPort("S2");
		}
		if(port.equalsIgnoreCase("s3")) {
			this.port = LocalEV3.get().getPort("S3");
		}
		if(port.equalsIgnoreCase("s4")) {
			this.port = LocalEV3.get().getPort("S4");
		}
		convertSensor();
	}
	public TouchSensorChecker(Port port) {
		this.port = port;
		convertSensor();
	}
	
	private void convertSensor() {
		
		SensorModes sensor = new EV3TouchSensor(port);
		SampleProvider touch = sensor.getMode("Touch");
		float[] status = new float[touch.sampleSize()];
		this.touch = touch;
		this.status = status;
	}
	
	public void checkSensor() {
		touch.fetchSample(status, 0);
	}
	
	public boolean isPressed() {
		if(status[0] == 0.0) {
			return false;
		} else if(status[0] == 1.0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isReleased() {
		if(status[0] == 0.0) {
			return true;
		} else if(status[0] == 1.0) {
			return false;
		} else {
			return true;
		}
	}

}
