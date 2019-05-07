package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.JumpJacksDrop;
import frc.robot.commands.JumpJacksRaise;

public class Level2Climb extends CommandGroup {
    public Level2Climb() {
        addParallel(new DriveTimeMove(1, 4));
        addSequential(new ArmDown(-0.65));
        addSequential(new JumpJacksDrop());
        addSequential(new Wait(1.3));
        addSequential(new JumpJacksRaise());
        addSequential(new ArmTimeMove(0.5, 1));
        addSequential(new Wait(0.75));

        addSequential(new DriveTimeMove(0.5, 1));

    }
}