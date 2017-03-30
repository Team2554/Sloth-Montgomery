package org.usfirst.frc.team2554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class LiftTracker extends Subsystem {
    private static double[] x, y, area, defaultArray;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public LiftTracker(){
		initNetTables();
		x = new double[0];
		y = new double[0];
		area = new double[0];
		defaultArray = new double[0];
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	//Check if 0 or 320
	public static double center = 320;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public static void updateTable() {
		x = NetworkTable.getTable("/GRIP/myContoursReport").getNumberArray("centerX", defaultArray);
		y = NetworkTable.getTable("/GRIP/myContoursReport").getNumberArray("centerY", defaultArray);
		area = NetworkTable.getTable("/GRIP/myContoursReport").getNumberArray("area", defaultArray);
	}

	public static double[] returnX() {
		return x;
	}

	public static double[] returnY() {
		return y;
	}

	public static double[] returnArea() {
		return area;
	}

	public static double returnWeightedX() {
		double areaSum = 0, weightedX = 0;
		for(int counter = 0; counter<area.length;counter++)
			areaSum += area[counter];
		for (int counter = 0; counter < x.length; counter++)
			weightedX += x[counter] * area[counter] / areaSum;
		return weightedX;
	}

	public static double returnWeightedY() {
		double areaSum = 0, weightedY = 0;
		for (int counter = 0; counter<area.length;counter++)
			areaSum += area[counter];
		for (int counter = 0; counter < y.length; counter++)
			weightedY += y[counter] * area[counter] / areaSum;
		return weightedY;
	}

	public static double returnAverageX() {
		double averageX = 0;
		for (int counter = 0; counter<area.length;counter++)
			averageX += area[counter];
		return averageX / x.length;
	}

	public static double returnAverageY() {
		double averageY = 0;
		for (int counter = 0; counter<area.length;counter++)
			averageY += area[counter];
		return averageY / y.length;
	}
	public boolean isCentered(){
		//Change deadzone
		return returnWeightedX() < (center+10) && returnWeightedX() > (center - 10);
	}
	public void initNetTables(){
		NetworkTable.setClientMode();
		NetworkTable.setTeam(2554);
		NetworkTable.initialize();
	}
}

