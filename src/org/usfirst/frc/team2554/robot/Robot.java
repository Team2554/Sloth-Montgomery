package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2554.robot.commands.*;
import org.usfirst.frc.team2554.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public RobotDrive myRobot;
	//SUBSYSTEMS
	public static final Climber climber = new Climber();
	public static final Gyro gyro = new Gyro();
	
	//OPERATOR INPUT
	public static OI oi;
	
	//CONSTANTS
	public static final double DEADZONE = 0.15;
	
	//VARIABLES
	double Xaxis, Yaxis, Zaxis;
	double sensitivity;
	
	//COMMANDS
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		myRobot = new RobotDrive(RobotMap.driveTrain[0], RobotMap.driveTrain[1], RobotMap.driveTrain[2], RobotMap.driveTrain[3]);
		oi = new OI();
		oi.climbSlowTrigger.whileActive(new ClimbSlowCommand());
		oi.climbFastTrigger.whileActive(new ClimbFastCommand());
		oi.noGyroButton.whileHeld(new GyroOff());
		oi.resetGyroButton.whileHeld(new ResetGyro());
		oi.climbViewButton.whileHeld(new ClimbView());
		oi.gearViewButton.whileHeld(new GearView());
		oi.climbReverseButton.whileHeld(new ClimbReverse());
		
		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);
		Robot.gyro.calibrate();
		
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		//Robot.gyro.calibrate();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		drive( 0.0, .4, 0, 1, 0.0 );
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		if(oi.joystick.getRawButton(oi.gearViewButtonNum) || oi.joystick.getRawButton(oi.sensitivityLowButtonNum) || oi.climbSlowTrigger.get() || oi.climbFastTrigger.get()){
			sensitivity = 0.2;
		}
		else
			sensitivity = 0.9;
		if (isNotDeadzone(oi.getRawAxis(0)))
			Xaxis = oi.getRawAxis(0);
		else
			Xaxis = 0.0;
		if (isNotDeadzone(oi.getRawAxis(1)))
			Yaxis = oi.getRawAxis(1);
		else
			Yaxis = 0.0;
		if (isNotDeadzone(oi.getRawAxis(2))) {
			if(oi.controller.getRawButton(oi.noTurnButtonNum))
				Zaxis = 0;
			else
				Zaxis = oi.getRawAxis(2);
		}
		else
			Zaxis = 0.0;
		
		LiftTracker.updateTable();
		
		//Vision :^)
		if(!oi.joystick.getRawButton(5))
			drive( Xaxis, Yaxis, Zaxis, sensitivity, Robot.gyro.get());
		else{
			if(LiftTracker.returnWeightedX() > LiftTracker.center + 20){
				myRobot.mecanumDrive_Cartesian(0, 0, 0.5,0);
				System.out.println("Turn Left");
			}
			else if(LiftTracker.returnWeightedX() < LiftTracker.center - 20){
				myRobot.mecanumDrive_Cartesian(0, 0, -0.5, 0);
				System.out.println("Turn Right");
			}
			else{
				myRobot.mecanumDrive_Cartesian(0, 0, 0, 0);
				System.out.println("Do Nothing");
			}
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	public boolean isNotDeadzone(double a){
		return Math.abs(a) > 0.15;
	}
	public void drive(double x, double y, double rotation, double multiplier, double gyroDeg) {
		myRobot.mecanumDrive_Cartesian(x * multiplier, y * multiplier, rotation * multiplier, gyroDeg);
	}
	
}
