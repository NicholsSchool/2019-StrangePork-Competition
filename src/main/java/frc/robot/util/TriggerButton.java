package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class TriggerButton extends Button
{
    private int axis;
    private Controller controller;
    public TriggerButton(Controller controller, int axis)
    {
        this.controller = controller; 
        this.axis = axis;
    }

    @Override
    public boolean get() {
        return controller.getRawAxis(axis) > 0.6;
    }
    
}