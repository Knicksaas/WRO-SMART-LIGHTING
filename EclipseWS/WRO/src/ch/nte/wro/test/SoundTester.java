package ch.nte.wro.test;

import java.io.File;

import lejos.hardware.Sound;

public class SoundTester {
	
	public static void main(String[] args) {
		Sound.beep();
		File file = new File("/sounds/Super Mario Bros. - Game Over.wav");
		if(file.exists()) {
			Sound.playSample(file, Sound.VOL_MAX);
		}
	}
}
