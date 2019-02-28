/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class OneClickCargo extends CommandGroup {
    /**
     * Add your docs here.
     */
    public OneClickCargo(int level) {
        double distance = 0;
        switch (level) {
        case 1:
            distance = 1;
            break;
        case 2:
            distance = 0;
            break;
        case 3:
            distance = -1;
            break;
        default:
            System.err.println("Invalid level for OneClickCargo!");
            return;
        }

        addSequential(new AlignWithLineBB(0.7));
        addSequential(new EncoderMove(0.7, distance));
        addSequential(new ArmMoveToLevel(level, 1));
        addSequential(new ChangeOuttake(level));
        addSequential(new Shoot());
    }
}
