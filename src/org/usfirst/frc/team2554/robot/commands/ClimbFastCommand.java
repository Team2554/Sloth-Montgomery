package org.usfirst.frc.team2554.robot.commands;

import org.usfirst.frc.team2554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbFastCommand extends Command {

    public ClimbFastCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }
    
    // Called when a button calls the Command
    public void start(){
    	Robot.climber.spinFast();
    	Robot.gyro.setClimbView(true);
    	Robot.gyro.setGearView(false);
    }
    
    // Called when a button stops calling the Command
    public void cancel(){
    	Robot.climber.stop();
    	Robot.gyro.setClimbView(false);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.stop();
    	Robot.gyro.setClimbView(false);
    }
}
