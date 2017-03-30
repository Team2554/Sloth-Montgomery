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
	public double getRawAxis(int axis) {
		return joystick.getRawAxis(axis);
	}
	//Controller Button
	final public int[] climbSlowButtonNum = {1,2};
	final public int[] climbFastButtonNum = {3,4};
	final public int climbReverseButtonNum = 5;
	//Joystick Button
	final public int noGyroButtonNum = 7;
	final public int noTurnButtonNum = 8;
	final public int resetGyroButtonNum  = 12;
	final public int climbViewButtonNum = 11;
	final public int gearViewButtonNum = 1;
	final public int sensitivityLowButtonNum = 2;
	//Button Objects
	public Button resetGyroButton = new JoystickButton(joystick, resetGyroButtonNum);
	public Button noGyroButton = new JoystickButton(joystick,noGyroButtonNum);
	public Button noTurnButton = new JoystickButton(joystick,noTurnButtonNum);
	public Button climbViewButton = new JoystickButton(joystick,climbViewButtonNum);
	public Button gearViewButton = new JoystickButton(joystick, gearViewButtonNum);
	public Button climbReverseButton = new JoystickButton(controller, climbReverseButtonNum);
	//Triggers
	public Trigger climbSlowTrigger= new ClimbSlowTrigger();
	public Trigger climbFastTrigger = new ClimbFastTrigger();
}
