package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.JumpJacksDrop;
import frc.robot.commands.JumpJacksRaise;

public class ClimbSequence extends CommandGroup
{
    public ClimbSequence()
    {
        addParallel(new DriveTimeMove(0.75, 6));
        addParallel(new JumpJacksDrop());
        addSequential(new Wait(0.5));
        addSequential(new ArmDown(-0.65));
        addSequential(new Wait(1.25));
        addSequential(new ArmTimeMove(0.5, 0.5));
        addSequential(new Wait(2));
        
        addSequential(new JumpJacksRaise());
        addSequential(new Wait(2.5));
        addSequential(new DriveTimeMove(0.5, 1));


        // addSequential(new ArmTimeMove(1, 3.0));
        // addSequential(new DriveTimeMove(0.5, 0.5));
    }
}