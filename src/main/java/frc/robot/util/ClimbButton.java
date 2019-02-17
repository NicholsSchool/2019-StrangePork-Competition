package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class ClimbButton extends Button
{
    Button b1, b2;
    public ClimbButton(Button b1, Button b2)
    {
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public boolean get() {
        return b1.get() && b2.get();
    }

}