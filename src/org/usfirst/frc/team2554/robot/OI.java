package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

import org.usfirst.frc.team2554.robot.triggers.ClimbFastTrigger;
import org.usfirst.frc.team2554.robot.triggers.ClimbSlowTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick controller = new Joystick(0);
	public Joystick joystick = new Joystick(1);
	//Controller Button
	final public int[] climbSlowButtonNum = {1,2};
	final public int[] climbFastButtonNum = {3,4};
	//Joystick Button
	final public int noGyroButtonNum = 1;
	final public int noTurnButtonNum = 2;
	final public int halfSensButtonNum = 3;
	final public int resetGyroButtonNum  = 4;
	final public int climbViewButtonNum = 5;
	final public int gearViewButtonNum = 6;
	//Button
	public Button resetGyroButton = new JoystickButton(joystick, resetGyroButtonNum);
	public Button noGyroButton = new JoystickButton(joystick,noGyroButtonNum);
	public Button noTurnButton = new JoystickButton(joystick,noTurnButtonNum);
	public Button halfSensButton = new JoystickButton(joystick,halfSensButtonNum);
	public Button climbViewButton = new JoystickButton(joystick,climbViewButtonNum);
	public Button gearViewButton = new JoystickButton(joystick, gearViewButtonNum);
	//Triggers
	public Trigger climbSlowTrigger= new ClimbSlowTrigger();
	public Trigger climbFastTrigger = new ClimbFastTrigger();
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
