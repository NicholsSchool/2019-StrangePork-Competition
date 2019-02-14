package frc.robot.autonomous;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;


 public class ClimbSequence extends CommandGroup
{
   public ClimbSequence()
   {
       //The values in these methods are fillers, not the numbers needed.
       addSequential(new ArmDrop(50, 0.5));
       addSequential(new JumpJacksDrop());
       addSequential(new SigmoidMoveForward(0.5, 2));
       addSequential(new JumpJacksRaise());
       addSequential(new SigmoidMoveForward(0.5, 2));
    }     
}

