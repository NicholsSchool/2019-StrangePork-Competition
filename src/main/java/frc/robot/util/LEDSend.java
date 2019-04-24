package frc.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LEDSend
{
    
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("leds");
    static NetworkTableEntry value = table.getEntry("value");
    public static void setValue(double val)
    {
        value.setDouble(val);
    }
}