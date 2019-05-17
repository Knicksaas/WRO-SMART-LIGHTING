package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.linefollower.LinefollowerUntilYellowGround;
import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.UnloadLight2;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeHinten;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class YellowAufräumen {
	
	//Erste gemssenene Zeit 2:07 Variante lang
		//Erste gemssenene Zeit 1:12 Variante kurz
	//Zweite gemessene Zeit 1:40 Variante lang
	// drite zeit lang 1:22 Variante lang
	
	
	
	public YellowAufräumen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		
		
		new CountLines((int) (speed*2.5), mLeft, mRight, GlobalSensors.colorSensorLeft, 2);
		new Turn(speed, -85, mLeft, mRight);
		new LinefollowerUntilJunction((int) (speed*2), mLeft, mRight, 80);
		new Turn((int) (speed*0.75), -90, mLeft, mRight);
		new LinefollowerWithTime(speed*2, mLeft, mRight, 90, ((4000)/(speed*2)));
		new LinefollowerUntilYellowGround((int) (speed*0.9), mLeft, mRight, 60);
		new motorsON((int) (speed*1.4), mLeft, mRight, true);
		Delay.msDelay((long) ((50*5500)/(speed*1.4)));
		new LinefollowerUntilYellowGround((int) (speed*0.9), mLeft, mRight, 60);
		new ZangeUp();
		new motorsON((int) (speed*1.5), mLeft, mRight, true);
		Delay.msDelay((long) ((50*500)/(speed*1.5)));
		
		
		
		if(LightArrangement.area1Yellow == null) {
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerWithTime((speed*2), mLeft, mRight, 60, ((100*750)/(speed*2)));
			new LinefollowerWithTime((speed*3), mLeft, mRight, 60, ((100*450)/(speed*3)));
			new Turn(speed, 90, mLeft, mRight);
			new motorsON(speed*2, mLeft, mRight, true);
			Delay.msDelay((50*750)/(speed*2));
			new CountLines((int) (speed*2), mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
			Delay.msDelay(500);
			new Turn(speed, 35, mLeft, mRight);
			new CountLines((int) (speed*2), mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
			new Turn(speed, -35, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*750)/speed);
			new ZangeDown();
			new LinefollowerUntilWhiteGround((int) (speed*2), mLeft, mRight, 80);
			RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
			mZange.setSpeed(1000);
			mZange.backward();
			Delay.msDelay(500);
			mZange.stop();
			RoboStatus.zangeUp = false;
			new motorsON((speed*2), mLeft, mRight, false);
			Delay.msDelay((50*3900)/(speed*2));
			new motorsOFF(mLeft, mRight);
			mZange.setSpeed(1000);
			mZange.forward();
			Delay.msDelay(500);
			mZange.stop();
			mZange.close();
			RoboStatus.zangeUp = false;
			new UnloadLight2(speed, mRight, mLeft);
			new motorsON(speed, mLeft, mRight, false);
			Delay.msDelay((6000)/speed);
			new Turn(speed, -90, mLeft, mRight);
			new ZangeDown();
			Delay.msDelay(3000);
			new motorsOnUntilJunction(speed, mLeft, mRight, false);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/(speed));
			new motorsOFF(mLeft, mRight); 
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerWithTime(speed*3, mLeft, mRight, 80, ((100*4500)/(speed*3)));
			new Turn(speed, 20, mLeft, mRight);
			new motorsON(speed*3 , mLeft, mRight, true);
			Delay.msDelay((50*2000)/(speed*3));
			new CountLines(speed*4, mLeft, mRight, GlobalSensors.colorSensorRight, 2);
			new Turn(speed, -56, mLeft, mRight);
			new motorsON(speed*2, mLeft, mRight, true);
			Delay.msDelay((50*750)/(speed*2));
			new CountLines(speed*4, mLeft, mRight, GlobalSensors.colorSensorRight, 3);
			new ZangeHinten();
			new motorsON(speed*3, mLeft, mRight, true);
			Delay.msDelay((50*2000)/(speed*3));
			new motorsOFF(mLeft, mRight); 
			
			
			
		} else {
			new Turn(speed, -60, mLeft, mRight);
			new motorsON(speed*2, mLeft, mRight, true);
			Delay.msDelay((50*750)/(speed*2));
			new motorsON(speed*3, mLeft, mRight, true);
			Delay.msDelay((50*750)/(speed*3));
			new CountLines(speed*3, mLeft, mRight, GlobalSensors.colorSensorRight, 1);
			Delay.msDelay((50*750)/speed);
			new Turn(speed, 60,mLeft, mRight);
			new LinefollowerWithTime((int) (speed*1.5), mLeft, mRight, 60, (int) ((50*2250)/(speed*1.5)));
			new LinefollowerWithTime((int) (speed*2.5), mLeft, mRight, 60, (int) ((50*2250)/(speed*2.5)));
			new LinefollowerWithTime((int) (speed*4.5), mLeft, mRight, 60, (int) ((50*4500)/(speed*4.5)));
			new Turn(speed, 90, mLeft, mRight);
			new motorsON(speed*2, mLeft, mRight, true);
			Delay.msDelay((50*2500)/(speed*2));
			new motorsOFF(mLeft, mRight);
			new ZangeDown();
            Delay.msDelay(3000);
			new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*750)/speed);
			new Turn(speed, 80, mLeft, mRight);
			new LinefollowerUntilJunction(speed*2, mLeft, mRight, 70);
			new LinefollowerWithTime((int) (speed*2.5), mLeft, mRight, 80, 100);
			new LinefollowerUntilJunction(speed*3, mLeft, mRight, 80);
			new Turn(speed, -27, mLeft, mRight);
			new motorsON(speed*3, mLeft, mRight, true);
			Delay.msDelay(200000/(speed*3));
			new motorsON(speed*3, mLeft, mRight, true);
			Delay.msDelay(240000/(speed*3));
			new CountLines(speed*4, mLeft, mRight, GlobalSensors.colorSensorRight, 4);
			new ZangeHinten();
			new motorsON(speed*2, mLeft, mRight, true);
			Delay.msDelay(200000/(speed*2));
			new motorsOFF(mLeft, mRight);
		}	
			
	}


}
