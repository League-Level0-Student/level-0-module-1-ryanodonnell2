/*
 *    Copyright (c) The League of Amazing Programmers 2013-2017
 *    Level 0
 */


	import java.awt.Color;
import java.awt.KeyEventDispatcher; 
	import java.awt.KeyboardFocusManager; 
	import java.awt.event.KeyEvent; 
	import java.io.File;
	import javax.sound.sampled.AudioInputStream; 
	import javax.sound.sampled.AudioSystem; 
	import javax.sound.sampled.Clip;

    import org.jointheleague.graphical.robot.Robot;
	
	public class RobotInSpace implements KeyEventDispatcher {
		
	private static final Color black = null;
	Robot rob = new Robot("mini");

	/*  Make the Robot move around the screen when the arrow keys are pressed... */
	private void moveRobot(int keyPressed) throws InterruptedException {
	 
		// 0. Print out the keyPressed variable and write down the numbers for each arrow key
System.out.println(keyPressed);
	    // 1. If the up arrow is pressed, move the Robot up the screen.
 if(keyPressed==38) {
	 rob.setAngle(0);
	 rob.microMove(2);
 }
	    // 2. If the down arrow is pressed, move the Robot down.
 if(keyPressed==40) {
	 rob.setAngle(180);
	 rob.microMove(2);
 }
 if(keyPressed==39) {
	 rob.setAngle(90);
	 rob.microMove(2);
 }
 if(keyPressed==37) { 
	 rob.setAngle(270);
	 rob.microMove(2);
 }
 if(keyPressed==32) { 
	 rob.penDown();
	 rob.setPenColor(black);
	 rob.microMove(20);
	 Thread.sleep(100);
	 rob.microMove(-20);
	 rob.penUp();
 }
	    // 5. Then move the Robot to RD-2D for a surprise! 
	}

	private void checkIfR2D2Found() throws Exception {
	    int robotLocationX = rob.getX();
	    int robotLocationY = rob.getY();

		if (robotLocationX <= 7300 && robotLocationX >= 720 && robotLocationY >= 150 && robotLocationY <= 160)
	        playEureka();
	}

	public static void main(String[] args) {
	    new RobotInSpace().controlTheRobot();
	}


	private void controlTheRobot() {
	    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	    rob.setWindowImage("planet.jpg");
	    rob.penUp();
	    rob.setSpeed(10);
	}

	public boolean dispatchKeyEvent(KeyEvent e) {
	    if (e.getID() == KeyEvent.KEY_PRESSED) {
	        try {
				moveRobot(e.getKeyCode());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	            checkIfR2D2Found();
	        } catch (Exception exception) {
	        }
	    }
	    return false;
	}

	public void playEureka() {
	    System.out.println("EUREKA!");
	    try {
	   		 Clip clip = AudioSystem.getClip();
	   		 clip.open(AudioSystem.getAudioInputStream(RobotInSpace.class.getResource("r2d2-eureka.wav")));
	   		 clip.start();
	   		 Thread.sleep(6000);
	   	} catch (Exception ex) {
	     	ex.printStackTrace();
	   	}
	}
	}


