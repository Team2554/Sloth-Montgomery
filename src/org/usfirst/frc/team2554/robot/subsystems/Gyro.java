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
	boolean isGyro, isClimbView, isGearView;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Gyro(){
		isGyro = true;
		isClimbView = false;
		isGearView = false;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new InitGyroCommand());
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
    public boolean toggleGyro()
    {
    	setGyroEnabled(!isGyro);
    	return isGyro;
    }
    public void setClimbView(boolean isEnable){
    	isClimbView = isEnable;
    }
    public boolean toggleClimbView(){
    	setClimbView(!isClimbView);
    	return isClimbView;
    }
    public void setGearView(boolean isEnable){
    	isGearView = isEnable;
    }
    public boolean toggleGearView(){
    	setGearView(!isGearView);
    	return isGearView;
    }
    public double get(){
    	if(isGearView)
    		return 90; //Change Angle
    	else
    		if(isClimbView)
    			return 270; //Change Angle
    		else
    			if(isGyro)
    				return gyro.getAngle();
    	return 0;
    }
}

