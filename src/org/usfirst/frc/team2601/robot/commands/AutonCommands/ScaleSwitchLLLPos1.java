package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.UltraGyroMoveStraight;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLLLPos1 extends CommandGroup {

    public ScaleSwitchLLLPos1() {
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
		addSequential(new EncGyroPlease(4200,4200,1.0,true));//Forward towards switch
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new EncGyroPlease(950,950,1.0,true));//Go forward in low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, false));//Turn right in low
    	addSequential(new EncGyroPlease(500,500,1.0,true));//Travel inwards 
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(85, true));//Turn left towards the scale
    	addSequential(new EncGyroPlease(200,200,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(175, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Go towards the prism
    	addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism
    	addSequential(new AutonTurn(20,true));
    }
}
