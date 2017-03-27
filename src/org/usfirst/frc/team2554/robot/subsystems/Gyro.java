package org.usfirst.frc.team2554.robot.subsystems;

import org.usfirst.frc.team2554.robot.commands.InitGyroCommand;
import org.usfirst.frc.team2554.robot.commands.ResetGyro;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyro extends Subsystem {
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	boolean isGyro = true, isClimbView = false, isGearView = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new  InitGyroCommand());
    }
    public void calibrate(){
    	gyro.calibrate();
    }
    public void reset(){
    	gyro.reset();
    }
    public void setGyroEnabled(boolean isEnable){
    	isGyro = isEnable;
    }
    public void setClimbViewEnabled(boolean isEnable){
    	isClimbView = isEnable;
    }
    public void setGearView(boolean isEnable){
    	isGearView = isEnable;
    }
    public double get(){
    	if(isGearView)
    		return 90; //Change Angle
    	if(isClimbView)
    		return 270; //Change Angle
    	if(isGyro)
    		return gyro.getAngle();
    	return 0;
    }
}

