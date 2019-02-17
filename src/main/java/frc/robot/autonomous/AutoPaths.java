package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ArmMoveToLevel;

public class AutoPaths extends CommandGroup
{
    public AutoPaths()
    {
        addSequential(new ElevatorTimeMove(1.0, 1));
        addSequential(new SigmoidMoveForward(0.6, 6.5));
        addSequential(new AngleTurn(45, 0.7));
        addSequential(new ArmMoveToLevel(1, 0.5));
        addSequential(new SigmoidMoveForward(0.6, 8));
      //  addSequential(new Place());
    }
}