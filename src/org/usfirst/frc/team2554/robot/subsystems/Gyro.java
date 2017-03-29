package org.usfirst.frc.team2554.robot.subsystems;

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
    	//System.out.println(isGearView+"|"+isClimbView+"|"+isGyro);
    	if(isGearView)
    		return 180; //Change Angle
    		
    	else{
    		if(isClimbView)
    			return 0; //Change Angle
    		else
    			if(isGyro)
    				return (gyro.getAngle()+180)%360;
    	}
    	return 0;
    }
}

