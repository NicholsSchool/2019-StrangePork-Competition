package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Used for special commands that requires two buttons to start
 */
public class DoubleButton extends Button
{
    private Button b1, b2;
    /**
     * Constructor for DoubleButton
     * @param b1 - One of the two buttons to be pressed
     * @param b2 - The other button to be pressed
     */
    public DoubleButton(Button b1, Button b2)
    {
        this.b1 = b1;
        this.b2 = b2;
    }
    
    /**
     * Used to check if the buttons are pressed
     * @return - True if both buttons are pressed
     */
    @Override
    public boolean get() {
        return b1.get() && b2.get();
    }

}