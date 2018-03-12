package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleScaleRPos2 extends CommandGroup {

    public DoubleScaleRPos2() {
    	addSequential(new EncGyroPlease(1000,1000,1.0,true));//Forward in high gear
    	addSequential(new ShiftGear());//Shift to low 
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, true));//Turn left 85 in low
    	addSequential(new ShiftGear());//Shift to high
    	addSequential(new EncGyroPlease(1700,1700,1.0,true));//Forward in high
    	addSequential(new ShiftGear());//Shift to low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	addSequential(new ShiftGear());//Shift to high 
    	addSequential(new EncGyroPlease(5000,5000,1.0,true));//Forward in high
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Travel inwards 
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, true));//Turn left towards the scale
    	addSequential(new EncGyroPlease(300,300,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(180, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(1200,1200,1.0,true));//Go towards the prism
    	addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism 
    	addSequential(new AutonTurn(180, false));//Turn around back towards the scale
    	addSequential(new EncGyroPlease(1000,1000,1.0,true));//Go towards the scale 
    }
}
