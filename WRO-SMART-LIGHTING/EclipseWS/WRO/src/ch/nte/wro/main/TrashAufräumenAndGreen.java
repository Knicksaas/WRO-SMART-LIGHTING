package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerForGreenPickup;
import ch.nte.wro.linefollower.LinefollowerUntilHalfJunction;
import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.linefollower.LinefollowerUntilLightAndTime;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.PickUpLight;
import ch.nte.wro.motion.SmallPickUpLight;
import ch.nte.wro.motion.SmallPickUpLightForRed;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.TurnWithOneMotor;
import ch.nte.wro.motion.UnloadLightAreaGreen;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeHinten;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import ch.nte.wro.status.RoboData;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TrashAufräumenAndGreen {
	
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private int speed;
	private boolean area1Blue;
	private boolean light1Green;
	private boolean light2Green;
	
	//erste gemessene zeit 3:05 mit main speed Variable 75
	public TrashAufräumenAndGreen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.speed = speed;
		
		uploadGreenLights();
		
		unloadInArea2Green();
		
		if(light1Green && light2Green) {
			unloadLightInA1GAndDriveToA1Y();
			driveFromA1YtoK2();
		} else if(light1Green || light2Green) {
			driveToA1G();
		}
		area1Blue = driveToArea1Blue();
		if(area1Blue) {
			//Auf A1B ist ein Licht
			LightArrangement.area1Blue = "black";
			driveFromA1BToK4();
			new Turn(speed, 90, mLeft, mRight);
			new ZangeDown();
			new motorsON(speed, mLeft, mRight, false);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			Delay.msDelay(2500);
			
		} else {
			//Auf A1R ist ein Licht
			LightArrangement.area1Red = "balck";
			driveFromA1BToK4();
			driveToArea1Red();
		}
		
		driveFromTrashToK3();
	}
	
	private void uploadGreenLights() {
   		// Uploads 2 Green Lights form Storage Area
		new motorsON(speed*2, mLeft, mRight, true);
		new LinefollowerUntilJunction(speed*3, mLeft, mRight, 60);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*200)/(speed*2));
		new LinefollowerUntilHalfJunction((int) (speed*1.5), mLeft, mRight, 60);
		new motorsOFF(mLeft, mRight);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*230)/(speed*2));
		new Turn(speed, 87, mLeft, mRight);
		LinefollowerForGreenPickup lffgpu = new LinefollowerForGreenPickup(speed*2, mLeft, mRight, 60);
		if(lffgpu.getCountedBlocks() > 1) {
			LightArrangement.light1Green = "green";
			LightArrangement.light2Green = "green";
			light1Green = true;
			light2Green = true;
			Sound.beep();
			Delay.msDelay(100);
			Sound.beep();
		} else {
			LightArrangement.light1Green = "green";
			light1Green = true;
			light2Green = false;
			Sound.beep();
		}
		new ZangeUp();
	}
	
	private void unloadInArea2Green() {
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*500)/(speed*2));
		new CountLines(speed*3, mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
		new Turn(speed, 70, mLeft, mRight);
		new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
		new Turn(speed, 10, mLeft, mRight);
		new LinefollowerWithTime((int) (speed*1.5), mLeft, mRight, 60, 750);
		new Turn(speed, -70, mLeft, mRight);
		new LinefollowerWithTime((int) (speed*1.5), mLeft, mRight, 10, 450);
		new motorsOFF(mLeft, mRight);
		if(light1Green && light2Green) {
			new UnloadLightAreaGreen(speed, mLeft, mRight);
		} else {
			new ZangeDown();
			Delay.msDelay(3000);
		}
	}
	
	private void unloadLightInA1GAndDriveToA1Y() {
		// Unload 1 Green Light in Area 1 Green and drive to Area 1 Yellow
		new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*400)/(speed*2));
		new Turn(speed, 75, mLeft, mRight);
		new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 70);
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*3100)/speed);
		new motorsOFF(mLeft, mRight);
		new ZangeDown();
		Delay.msDelay(3000);
		new motorsON(speed*2, mLeft, mRight, false);
		Delay.msDelay((50*3000)/(speed*2));
		new Turn(speed, -35, mLeft, mRight);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*700)/speed);
		new CountLines((int) (speed*2.5), mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
		new Turn(speed, -50, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 80, 200);
		new LinefollowerWithTime(speed*4, mLeft, mRight, 80, 400);
		new LinefollowerWithTime(speed*3, mLeft, mRight, 80, 100);
		new LinefollowerUntilLight(speed*2, mLeft, mRight, 80);
		new PickUpLight(speed, mLeft, mRight);
		LightArrangement.area1Yellow = "balck";
	}
	
	private void driveToA1G() {
		// Drive to Area 1 Green
		new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*60)/(speed*2));
		new Turn(speed, 75, mLeft, mRight);
		new LinefollowerUntilLight(speed, mLeft, mRight, 80);
		new PickUpLight(speed*2, mLeft, mRight);
		new Turn(speed, 180, mLeft, mRight);
		new ZangeDown();
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 80);
	}
	
	private void driveFromA1YtoK2() {
		//Drive form Area 1 Yellow to Kreuzung 2
		new Turn(speed, -175, mLeft, mRight);
		new LinefollowerUntilHalfJunction(speed*2, mLeft, mRight, 60);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*900)/(speed*2));
		new LinefollowerUntilHalfJunction(speed*2, mLeft, mRight, 60);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 60, (50*800)/speed);
		new Turn(speed, 30, mLeft, mRight);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*700)/speed*2);
		new CountLines(speed*2, mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
		new Turn(speed, 50, mLeft, mRight);
		new ZangeDown();
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 80);
		new motorsOFF(mLeft, mRight);
	}
	
	private boolean driveToArea1Blue() {
		// Drive to Area 1 Blue
		LinefollowerUntilLightAndTime lfunl = new LinefollowerUntilLightAndTime(speed, mLeft, mRight, 60, 800);
		if(lfunl.isThereALight()) {
			new SmallPickUpLight(speed, mLeft, mRight);
			return true;
		}
		new ZangeUp();
		Delay.msDelay(1000);
		return false;
	}
	
	private void driveFromA1BToK4() {
		// Drive to Area 1 Blue to Kreuzung 4
		new motorsOnUntilJunction((int) (speed*2.5), mLeft, mRight, false);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*500)/speed);
		new Turn(speed, 85, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 80, 400);
		new LinefollowerWithTime(speed*4, mLeft, mRight, 80, 2000);
		new LinefollowerUntilWhiteGround(speed*3, mLeft, mRight, 80);
	}
	
	private boolean driveToArea1Red() {
		// Drive from Kreuzung 4 to Area 1 Red
		new ZangeDown();
		new Turn(speed, -85, mLeft, mRight);
		new LinefollowerUntilLight(speed, mLeft, mRight, 60);
		new SmallPickUpLightForRed(speed, mLeft, mRight);
		new Turn(speed, -180, mLeft, mRight);
		new LinefollowerUntilHalfJunction((int) (speed*1.5), mLeft, mRight, 80);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*500)/(speed));
		new motorsOFF(mLeft, mRight);
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(1000);
		mZange.forward();
		Delay.msDelay(800);
		mZange.stop();
		mZange.close();
		Delay.msDelay(800);
		
		return true;
	}
	
	private void driveFromTrashToK3() {
		// Drive from Trash to Kreuzung 3
		new motorsON(speed*2, mLeft, mRight, false);
		Delay.msDelay((50*2100)/(speed*2));
		new motorsOFF(mLeft, mRight);
		new TurnWithOneMotor(speed*2, 90, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 90, 500);
		new LinefollowerWithTime(speed*4, mLeft, mRight, 90, 200);
		new LinefollowerWithTime(speed*5, mLeft, mRight, 90, 1000);
		new LinefollowerUntilWhiteGround(speed*4, mLeft, mRight, 80);
		
	}
	
	public void cleanUp1Yellow() {
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 60, 200);
		new LinefollowerWithTime(speed*3, mLeft, mRight, 60, 400);
		new LinefollowerUntilLight((int) (speed*2), mLeft, mRight, 60);
		new PickUpLight(speed, mLeft, mRight);
		new Turn(speed, -120, mLeft, mRight);
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*1000)/(speed*2));
		new motorsON(speed*3, mLeft, mRight, true);
		Delay.msDelay((50*1000)/(speed*3));
		new CountLines(speed*4, mLeft, mRight, GlobalSensors.colorSensorRight, 2);
		new Turn(speed, 30, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 60, 200);
		new LinefollowerUntilJunction(speed*3, mLeft, mRight, 60);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 60, 200);
		new Turn(speed, 90, mLeft, mRight);
		new ZangeDown();
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*1800)/(speed*2));
		new motorsOFF(mLeft, mRight);
		Sound.beep();
		Delay.msDelay(1000);
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay(2000);
		new motorsOFF(mLeft, mRight);
		new ZangeHinten();
	}
}
