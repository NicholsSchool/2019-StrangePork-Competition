package frc.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.ArmMoveToLevel;

public class AutoPaths extends CommandGroup
{
    private int dialPos;

    public AutoPaths()
    {
        dialPos = Robot.appDial.getPosition();
        runPath(dialPos);

    }

    private void runPath(int pathNum)
    {
        switch(pathNum)
        {
            case 10: 
                    twoHatchRocket();
                    break;
            default:
                    driveForward();
        }

    }

    private void twoHatchRocket()
    {
        addSequential(new ElevatorTimeMove(1.0, 1));
        addSequential(new SigmoidMoveForward(0.6, 6.5));
        addSequential(new AngleTurn(45, 0.7));
        addSequential(new ArmMoveToLevel(1, 0.5));
        addSequential(new SigmoidMoveForward(0.6, 8));
        // addSequential(new Place());
        // addSequential(new AngleTurn(90, 0.7));
        // addSequential(new SigmoidMoveForward(0.6, 5));
        // //grab somehow
        // addSequential(new AngleTurn(180, 0.7));
        // addSequential(new SigmoidMoveForward(0.6, 5));

    }

    private void driveForward()
    {
        addSequential(new SigmoidMoveForward(0.6, 6.5));
    }
}