package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DustpanDrop extends Command
{

    public DustpanDrop()
    {
        requires(Robot.dustpan);
        requires(Robot.arm);
    }

    @Override
    public void initialize()
    {

    }

    /**
     * Drops the Dustpan
     */
    @Override
    protected void execute() 
    {
        System.out.println("Dropping");

        if(Robot.armPot.getPosition() < Constants.ARM_INTERFERENCE_WITH_DUSTPAN && !Robot.sensorOverride)
            Robot.arm.move(1.0);
        else 
            Robot.dustpan.drop();
    }

    /**
     * Finishes automatically
     */
	@Override
    protected boolean isFinished()
    {
        return Robot.armPot.getPosition() > Constants.ARM_INTERFERENCE_WITH_DUSTPAN || Robot.sensorOverride;
    }

    /**
     * Calls end
     */
    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    protected void end() 
    {
        Robot.arm.stop();
    }


}