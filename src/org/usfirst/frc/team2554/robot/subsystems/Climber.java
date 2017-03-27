package org.usfirst.frc.team2554.robot.subsystems;

import org.usfirst.frc.team2554.robot.Robot;
import org.usfirst.frc.team2554.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor victorA = new Victor(RobotMap.climber[0]);
	Victor victorB = new Victor(RobotMap.climber[1]);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void spinSlow(){
    	victorA.set(0.5);
    	victorB.set(0.5);
    }
    public void spinFast(){
    	victorA.set(0.95);
    	victorB.set(0.95);
    }
    public void stop(){
    	victorA.set(0);
    	victorB.set(0);
    }
}

