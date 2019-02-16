package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Place extends CommandGroup
{
    public Place()
    {
        addSequential(new ElevatorTimeMove(1, 0.2));
        addSequential(new ArmTimeMove(-1, 0.05));
        addSequential(new ElevatorTimeMove(-1, 0.2));
        addSequential(new DriveTimeMove(-1, 0.3));
    }
}