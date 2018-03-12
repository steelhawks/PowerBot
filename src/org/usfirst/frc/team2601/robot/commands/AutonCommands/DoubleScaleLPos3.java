package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleScaleLPos3 extends CommandGroup {

    public DoubleScaleLPos3() {
    	addSequential(new EncGyroPlease(4300,4300,1.0,true));//Forward towards switch
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new EncGyroPlease(1700,1700,1.0,true));//Go forward in low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, true));//Turn left in low
    	addSequential(new EncGyroPlease(450,500,1.0,true));//Travel inwards 
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right towards the scale
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(2.0));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(165, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(850,850,0.8,true));//Go towards the prism
    	addSequential(new AutonWait(1));//Wait for the mechanism to grab the prism 
    	addSequential(new AutonTurn(190, true));//Turn around back towards the scale
    	addSequential(new EncGyroPlease(790,790,1.0,true));//Go towards the scale 
    }
}
