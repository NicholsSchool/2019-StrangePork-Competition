package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class Shoot extends CommandGroup
{
    public Shoot()
    {
        addSequential(new Outtake(true));
        Robot.ballWasIn = true;
    }
}