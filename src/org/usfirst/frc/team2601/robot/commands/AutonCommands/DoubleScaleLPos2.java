package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Constants;
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
public class DoubleScaleLPos2 extends CommandGroup {

	Constants constants = Constants.getInstance();
	
    public DoubleScaleLPos2() {
    //Command for testing all the methods that are being used in autonomous 	
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
		
		addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(700,700,1.0,true));//Forward in high gear
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new ShiftGear());//Shift to low 
    	constants.gyroSlowSpeed = 0.25;
    	addSequential(new AutonTurn(85, true));//Turn left 85 in low
    	constants.gyroSlowSpeed = 0.2;
    	addSequential(new ShiftGear());//Shift to high
    	addSequential(new AutonWait(0.25));
    	addSequential(new EncGyroPlease(1750,1750,1.0,true));//Forward in high
    	addSequential(new ShiftGear());//Shift to low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	addSequential(new ShiftGear());//Shift to high 
    	addSequential(new EncGyroPlease(3500,3500,1.0,true));//Forward in high
    	addSequential(new ShiftGear());//Delay
    	constants.gyroSlowSpeed = 0.25;
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	constants.gyroSlowSpeed = 0.2;
    	addSequential(new EncGyroPlease(400,400,1.0,true));//Travel inwards 
    	addSequential(new AutonWait(0.25));//Delay
    	constants.gyroSlowSpeed = 0.3;
    	addSequential(new AutonTurn(85, true));//Turn left towards the scale
    	constants.gyroSlowSpeed = 0.2;
    	addSequential(new EncGyroPlease(300,300,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(180, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Go towards the prism
    	addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism 
    	addSequential(new AutonTurn(180, false));//Turn around back towards the scale
    	addSequential(new EncGyroPlease(1000,1000,1.0,true));//Go towards the scale 
   
    }
}
