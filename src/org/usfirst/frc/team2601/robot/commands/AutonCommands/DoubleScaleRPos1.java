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
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		//Robot.drivetrain.backLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		//Robot.drivetrain.backRightM.setSafetyEnabled(false);
		
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		//Robot.drivetrain.backLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		//Robot.drivetrain.backRightM.setExpiration(120);
		
    	addSequential(new EncGyroPlease(3800,3800,1.0,true));//Forward towards switch
    	addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new EncGyroPlease(1500,1500,1.0,true));//Go forward in low
    	addSequential(new AutonWait(0.25));//Delay
    	addSequential(new AutonTurn(88, false));//Turn right in low
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(3250,3250,1.0,true));//Travel inwards 
    	addSequential(new ShiftGear());	
    	addSequential(new AutonTurn(88, true));
    	addSequential(new EncGyroPlease(1000,1000,0.85,true));
    	addSequential(new EncGyroPlease(600,600,1.0,false));
    	addSequential(new AutonTurn(180, true));
    	addSequential(new EncGyroPlease(300,300,0.9,true));
    }
}
