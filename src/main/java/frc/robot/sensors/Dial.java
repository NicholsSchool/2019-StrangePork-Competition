package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Dial 
{
    	AnalogPotentiometer dial;
	
	public Dial(AnalogPotentiometer pot) {
		dial = pot;
	}
	
	public int getPosition() {
	    double autoSwitch = dial.get();
	    int change;
	    if(autoSwitch > 265)
	    	change = -7; 
	    else 
	    	change = 5;
	    
	    
	    double dialNum = (autoSwitch + change) / (360 / 10);
	    double floor = Math.floor(dialNum);
	    if ((dialNum - floor) >= 0.5) 
	      dialNum = Math.ceil(dialNum);
	    else 
	      dialNum = floor;
	    return (int) dialNum;
	}
}