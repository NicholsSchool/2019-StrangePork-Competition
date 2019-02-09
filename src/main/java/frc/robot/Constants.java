package frc.robot;

public class Constants
{
    public static final int
    
    FRONT_LEFT_DRIVE_ID = 1,
    MID_LEFT_DRIVE_ID = 2,
    BACK_LEFT_DRIVE_ID = 3,
    FRONT_RIGHT_DRIVE_ID = 4,
    MID_RIGHT_DRIVE_ID = 5,
    BACK_RIGHT_DRIVE_ID = 6,
    LEFT_DART_ID = 7,
    RIGHT_DART_ID = 8,
    LEFT_GRIPPER_ID = 9,
    RIGHT_GRIPPER_ID = 10,
    ARM_EXTEND_MOTOR_ID = 11,

    HATCH_LOCK_LIMIT_SWITCH = 0,
    BOTTOM_ARM_LIMIT_SWITCH = 1,
    TOP_ARM_LIMIT_SWITCH = 2,
    RETRACTED_JJ_LIMIT_SWITCH = 3,

    ARM_POT_HIGH_EXTREME_VALUE = 90,
    ARM_POT_LOW_EXTREME_VALUE = 0,
    ELEVATOR_POT_HIGH_EXTREME_VALUE = 20,
    ELEVATOR_POT_LOW_EXTREME_VALUE = 0,

    ELEVATOR_ARM_POT = 4,
    ARM_POT = 5;

    public static final double SIGMOID_A = 25;

    //Need to be changed
    public static final double 
    WHEEL_DIAMETER_IN_FEET = 0.5,
    TICKS_PER_ROTATION = 1128;

    public static double INTAKE_SPEED=0.5;
    public static final boolean
        DUSTPAN_RAISED = true,
        DUSTPAN_DROPPED = !DUSTPAN_RAISED,
        JUMPJACKS_RAISED = true,
        JUMPJACKS_DROPPED = !JUMPJACKS_RAISED;

}