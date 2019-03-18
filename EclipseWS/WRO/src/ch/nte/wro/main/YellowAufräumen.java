package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilHalfJunction;
import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.UnloadLight2;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class YellowAufräumen {
	
	
	public YellowAufräumen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		
		new CountLines(speed, mLeft, mRight, GlobalSensors.colorSensorLeft, 2);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*500)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerUntilJunction(speed, mLeft, mRight, 80);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerUntilWhiteGround(speed, mLeft, mRight, 80);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*6000)/speed);
		new motorsOFF(mLeft, mRight);
		new LinefollowerUntilWhiteGround(speed, mLeft, mRight, 80);
		new ZangeUp();
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1500)/speed);
		new motorsOFF(mLeft, mRight);		
		new Turn(speed, -90, mLeft, mRight);
		
		if(LightArrangement.area1Yellow == null) {
			new LinefollowerWithTime(speed, mLeft, mRight, 80, ((75*1000)/speed));
			new Turn(speed, 90, mLeft, mRight);
			new CountLines(speed, mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilLight(speed, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1000)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1000)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilLight(speed, mLeft, mRight, 80);
			new motorsON(speed, mLeft, mRight, false);
			Delay.msDelay((50*3500)/speed);
			new motorsOFF(mLeft, mRight);
			new UnloadLight2(speed, mRight, mLeft);
			new Turn(speed, -90, mLeft, mRight);
			new ZangeDown();
			new motorsOnUntilJunction(speed, mLeft, mRight, false);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerUntilLight(speed, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1000)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilJunction(speed, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*600)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerUntilWhiteGround(speed, mLeft, mRight, 60);
			
		} else {
			new LinefollowerUntilHalfJunction(speed, mLeft, mRight, 60); 
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilHalfJunction(speed, mLeft, mRight, 60); 
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*750)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilJunction(speed, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilJunction(speed, mLeft, mRight, 60); 
			new LinefollowerWithTime(speed, mLeft, mRight, 60, ((50*1000)/speed));
			new Turn(speed, 90, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*2500)/speed);
			new motorsOFF(mLeft, mRight);
			new ZangeDown();
			new motorsOnUntilJunction(speed, mLeft, mRight, false);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilWhiteGround(speed, mLeft, mRight, 60);
		}	
			
	}


}
