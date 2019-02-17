package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup
{
    public Shoot()
    {
        addSequential(new Outtake(0.5));
        addSequential(new Intake(1));
    }
}