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
public class DoubleScaleRPos1 extends CommandGroup {

    public DoubleScaleRPos1() {
    	if(Robot.drivetrain.shiftSol.get() == DoubleSolenoid.Value.kForward){
    		addSequential(new ShiftGear());
    	}else {
    		addSequential(new ShiftGear());
    	}
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.backLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.backRightM.setSafetyEnabled(false);
		
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.backLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		Robot.drivetrain.backRightM.setExpiration(120);
		
    	addSequential(new EncGyroPlease(4150,4150,1.0,true));//Forward towards switch
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new EncGyroPlease(900,900,1.0,true));//Go forward in low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(200,200,1.0,true));//Travel inwards 
    	addSequential(new ShiftGear());
    	addSequential(new AutonTurn(85, true));//Turn left towards the scale
    	addSequential(new EncGyroPlease(300,300,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(180, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Go towards the prism
    	addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism 
    	addSequential(new AutonTurn(180, false));//Turn around back towards the scale
    	addSequential(new EncGyroPlease(1000,1000,1.0,true));//Go towards the scale 	
    }
}
