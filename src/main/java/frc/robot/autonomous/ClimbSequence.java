package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.JumpJacksDrop;
import frc.robot.commands.JumpJacksRaise;

public class ClimbSequence extends CommandGroup
{
    public ClimbSequence()
    {
        addParallel(new DriveTimeMove(0.7, 3.75));
        addParallel(new JumpJacksDrop());
        addSequential(new Wait(0.5));
        addSequential(new ArmDown(-0.6));
        addSequential(new Wait(1.25));
        addSequential(new ArmTimeMove(0.5, 0.5));
        addSequential(new Wait(2));
        
        addSequential(new JumpJacksRaise());
        addSequential(new Wait(2.5));
    //    addSequential(new DriveTimeMove(0.5, 1));
    }
}