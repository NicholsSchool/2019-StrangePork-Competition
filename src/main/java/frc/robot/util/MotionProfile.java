/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Add your docs here.
 */
public class MotionProfile {

    public static double threeSpeed(double x) {
        x = Math.abs(x);

        if(x < 0.33 || x > 0.33) {
            return 0.5;
        }
        return 1;
    }

    public static double sigmoidProfile(double x) {
        x = Math.abs(x);
        x += 0.05;

        if (x < 0 || x > 1) {
            return 0;
        }

        final double a = 5;

        if (x < 0.5) {
            return 2 / (1 + Math.pow(Math.E, -a * x)) - 1;
        } else {
            return 2 / (1 + Math.pow(Math.E, a * x - a)) - 1;
        }
    }
}
