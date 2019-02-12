package frc.robot;

public class Constants
{
    public static final int
    
    LEFT_FRONT_DRIVE_ID = 31,
    LEFT_MID_DRIVE_ID = 33,
    LEFT_BACK_DRIVE_ID = 35,
    RIGHT_FRONT_DRIVE_ID = 30,
    RIGHT_MID_DRIVE_ID = 32,
    RIGHT_BACK_DRIVE_ID = 34,
    LEFT_DART_ID = 38,
    RIGHT_DART_ID = 36,
    LEFT_GRIPPER_ID = 39,
    RIGHT_GRIPPER_ID = 37,
    ARM_EXTEND_MOTOR_ID = 41,

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