package org.usfirst.frc.team2554.robot.triggers;

import org.usfirst.frc.team2554.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ClimbFastTrigger extends Trigger {

    public boolean get() {
        return Robot.oi.controller.getRawButton(Robot.oi.climbFastButtonNum[0]) || Robot.oi.controller.getRawButton(Robot.oi.climbFastButtonNum[1]);
    }
}
