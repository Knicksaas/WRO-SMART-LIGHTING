package ch.nte.wro.linefollower;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

//Noch nicht auf neusten Stand gebracht! Noch nicht verwenden!

public class TurnUntilLine {
	private int speed;
	private float angel;
	private float lineIntensity;
	
	public TurnUntilLine(int speed, String side, float lineIntensity) {
		if(side.equalsIgnoreCase("left")) {
			this.angel = 1;
		} else if(side.equalsIgnoreCase("right")) {
			this.angel = -1;
		} else {
			return;
		}
		this.speed = speed;
		this.lineIntensity = lineIntensity;
		exec();
	}

	private void exec() {
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		LightIntensitySensorChecker l1 = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		
		if(angel<0) {
			if(RoboData.invertMotorDirection) {
				mRight.forward();
				mLeft.backward();
			} else {
				mRight.backward();
				mLeft.forward();
			}
		} else {
			if(RoboData.invertMotorDirection) {
				mRight.backward();
				mLeft.forward();
			} else {
				mRight.forward();
				mLeft.backward();
			}
		}
		l1.checkSensor();
		while(l1.getIntensity() < (lineIntensity - 0.03)) {
			l1.checkSensor();
		}
		
		mRight.setSpeed(0);
		mLeft.setSpeed(0);
		mRight.stop();
		mLeft.stop();
		mRight.close();
		mLeft.close();
	}

}
