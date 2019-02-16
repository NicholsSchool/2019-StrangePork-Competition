/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Vision {

    private static NetworkTable table;

    public static double angleToLine;
    public static double distanceToLine;
    public static double angleToWall;
    public static int camera;

    public static void init() {
        table = NetworkTableInstance.getDefault().getTable("vision");
        int flags = EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate
                | EntryListenerFlags.kLocal;

        table.getEntry("angleToLine").addListener(event -> {
            angleToLine = event.value.getDouble();
        }, flags);
        table.getEntry("distanceToLine").addListener(event -> {
            distanceToLine = event.value.getDouble();
        }, flags);
        table.getEntry("angleToWall").addListener(event -> {
            angleToWall = event.value.getDouble();
        }, flags);
        table.getEntry("camera").addListener(event -> {
            camera = (int) event.value.getDouble();
        }, flags);
    }

    public static void switchCamera(int camera) {
        table.getEntry("camera").setDouble(camera);
    }

}
