package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilHalfJunction;
import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.PickUpLight;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.TurnWithOneMotor;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.LightArrangement;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TrashAufräumenAndGreen {
	
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private int speed;
	private boolean area1Green;
	private boolean area1Blue;
	private boolean light1Green;
	private boolean light2Green;
	
	public TrashAufräumenAndGreen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.speed = speed;
		
		light1Green = driveToLight1Green();
		if(light1Green) {
			LightArrangement.light1Green = "green";
		}
		
		light2Green = driveToLight2Green();
		if(light2Green) {
			LightArrangement.light2Green = "green";
		}
		
		unloadInArea2Green();
		new ZangeDown();
	}
	
	private boolean driveToLight1Green() {
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay(400);
		new motorsOFF(mLeft, mRight);
		new LinefollowerUntilHalfJunction(speed*2, mLeft, mRight, 60);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(500);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		LinefollowerUntilLight lful = new LinefollowerUntilLight(speed, mLeft, mRight, 60);
		if(lful.isThereALight()) {
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay(1500);
			new motorsOFF(mLeft, mRight);
			return true;
		}
		return false;
	}
	
	private boolean driveToLight2Green() {
		LinefollowerUntilLight lful = new LinefollowerUntilLight(speed, mLeft, mRight, 10);
		if(lful.isThereALight()) {
			new PickUpLight(speed, mLeft, mRight);
			return true;
		}
		new PickUpLight(speed, mLeft, mRight);
		return false;
	}
	
	private void unloadInArea2Green() {
		new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
		new Turn(speed, -10, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 20);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(500);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 100, mLeft, mRight);
		new LinefollowerWithTime(speed, mLeft, mRight, 60, 900);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerWithTime(speed, mLeft, mRight, 60, 500);
		new ZangeDown();
	}
	
	private boolean driveToArea1Yellow() {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1500)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
		new PickUpLight(speed, mLeft, mRight);
		return true;
	}
	
	private void drivefromA1GtoK2() {
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
		new ZangeDown();
	}
	
	private void driveFromA1YtoK2() {
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		new LinefollowerUntilJunction(speed*3, mLeft, mRight, 60);
		new ZangeDown();
	}
	
	private boolean driveToArea1Blue() {
		LinefollowerUntilLight lfunl = new LinefollowerUntilLight((int) Math.round(speed*1.5), mLeft, mRight, 80);
		if(lfunl.isThereALight()) {
			new PickUpLight(speed, mLeft, mRight);
			return true;
		}
		new ZangeUp();
		return false;
	}
	
	private void driveFromA1BToK4() {
		new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*700)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
	}
	
	private boolean driveToArea1Red() {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*500)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new ZangeDown();
		new LinefollowerUntilLight(speed, mLeft, mRight, 60);
		new PickUpLight(speed, mLeft, mRight);
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new ZangeDown();
		return false;
	}
	
	private void driveFromTrashToK3() {
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*2200)/speed);
		new motorsOFF(mLeft, mRight);
		new TurnWithOneMotor(speed*2, 90, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 80);
		new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
	}
}
