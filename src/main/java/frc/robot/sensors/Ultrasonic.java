package frc.robot.sensors;

import frc.robot.RobotMap;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Ultrasonic {

    public double getRange(char side) {
        if (side == 'L')
            return RobotMap.leftFrontUltraSonic.getValue();
        if (side == 'R')
            return RobotMap.rightFrontUltraSonic.getValue();
        return 0;

    }

    public void reset(char side) {
        if (side == 'L')
            RobotMap.leftFrontUltraSonic.resetAccumulator();
     //   System.out.println("left ultrasonic reset");
        if (side == 'R')
            RobotMap.rightFrontUltraSonic.resetAccumulator();
    //    System.out.println("right ultrasonic reset");
    }

    public double getInches(char side) {
        if (side == 'L') {
            double x = RobotMap.rightFrontUltraSonic.getValue();
            x = x * .048;
            return x;
        }
        if (side == 'R') {
            double x = RobotMap.leftFrontUltraSonic.getValue();
            x = x * .048;
            return x;
        }
        return 0;
    }
}