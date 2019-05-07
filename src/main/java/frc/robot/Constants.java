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

    ARM_POT_HIGH_BUFFER = 20,
    ARM_POT_LOW_BUFFER = 1,
    ARM_POT_HIGH_EXTREME_VALUE = 403 - ARM_POT_HIGH_BUFFER ,
    ARM_POT_LOW_EXTREME_VALUE = 100 + ARM_POT_LOW_BUFFER ,
    ELEVATOR_POT_HIGH_BUFFER = 3, 
    ELEVATOR_POT_LOW_BUFFER = 1,
    ELEVATOR_POT_HIGH_EXTREME_VALUE = 1023 - ELEVATOR_POT_HIGH_BUFFER,
    ELEVATOR_POT_LOW_EXTREME_VALUE = 982 + ELEVATOR_POT_LOW_BUFFER,

    ELEVATOR_ARM_POT = 4,
    ARM_POT = 5,
    WHEELS_AGAINST_WALL_LS_CHNL = 1;

    public static final double SIGMOID_A = 25;

    //Need to be changed
    public static final double 
    WHEEL_DIAMETER_IN_FEET = 0.5,
    TICKS_PER_ROTATION = 12000;

    public static final double 
    START_ARM_VALUE = 2414;

    //Need to be changed   
    public static final double
    LEVEL_1_HATCH_VALUE = 136,
    LEVEL_2_HATCH_VALUE = 292,
    LEVEL_3_HATCH_VALUE = 393;

    public static final double
    LEVEL_1_BALL_VALUE = 230,
    LEVEL_2_BALL_VALUE = 350,
    LEVEL_3_BALL_VALUE = 350;

    public static final double
    ARM_DOWN = 130;

    public static final double
    LEVEL_1_OUTTAKE_SPD = -0.22,
    LEVEL_2_OUTTAKE_SPD = -0.7,
    LEVEL_3_OUTTAKE_SPD = -1.0;

    public static double INTAKE_SPEED=1;
    public static final boolean
        DUSTPAN_RAISED = false,
        DUSTPAN_DROPPED = !DUSTPAN_RAISED,
        JUMPJACKS_RAISED = false,
        JUMPJACKS_DROPPED = !JUMPJACKS_RAISED;

    public static final int
    
        CONTROLLER_A_BUTTON_ID = 1,
        CONTROLLER_B_BUTTON_ID = 2,
        CONTROLLER_X_BUTTON_ID = 3,
        CONTROLLER_Y_BUTTON_ID = 4,
        CONTROLLER_LEFT_BUMPER_ID = 5,
        CONTROLLER_RIGHT_BUMPER_ID = 6,
        CONTROLLER_SELECT_BUTTON_ID = 7,
        CONTROLLER_START_BUTTON_ID = 8,
        CONTROLLER_LEFTSTICK_BUTTON_ID = 9,
        CONTROLLER_RIGHTSTICK_BUTTON_ID = 10,
        CONTROLLER_LEFT_TRIGGER_BUTTON_ID = 11,
        CONTROLLER_RIGHT_TRIGGER_BUTTON_ID = 12,
        CONTROLLER_LEFTSTICK_X_AXIS_ID = 0,
        CONTROLLER_LEFTSTICK_Y_AXIS_ID = 1,
        CONTROLLER_RIGHTSTICK_X_AXIS_ID = 4,
        CONTROLLER_RIGHTSTICK_Y_AXIS_ID = 5,
        CONTROLLER_LEFT_TRIGGER_AXIS_ID = 2,
        CONTROLLER_RIGHT_TRIGGER_AXIS_ID = 3;

        public static final int
        CONTROLLER_1_BUTTON_ID = 1,
        CONTROLLER_2_BUTTON_ID = 2,
        CONTROLLER_3_BUTTON_ID = 3,
        CONTROLLER_4_BUTTON_ID = 4,
        CONTROLLER_5_BUTTON_ID = 5,
        CONTROLLER_6_BUTTON_ID = 6,
        CONTROLLER_7_BUTTON_ID = 7,
        CONTROLLER_8_BUTTON_ID = 8,
        CONTROLLER_9_BUTTON_ID = 9,
        CONTROLLER_10_BUTTON_ID = 10,
        CONTROLLER_11_BUTTON_ID = 11,
        CONTROLLER_12_BUTTON_ID = 12;

    public static final double THIRTY_INCH_RULE_THRESH = 0.5;
    public static final double THIRTY_INCH_RULE_RATIO = 2;
    public static final int ARM_INTERFERENCE_WITH_DUSTPAN = 250;
}