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
public class DoubleScaleLPos1 extends CommandGroup {

    public DoubleScaleLPos1() {
    //Command for testing all the methods that are being used in autonommous 	
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
    	addSequential(new EncGyroPlease(300,300,1.0,true));//Travel towards the scale
    	addSequential(new AutonWait(0.75));//Wait for the mechanism to place the prism
    	addSequential(new AutonTurn(180, false));//Turn around back towards another prism
    	addSequential(new EncGyroPlease(800,800,1.0,true));//Go towards the prism
    	addSequential(new AutonWait(0.5));//Wait for the mechanism to grab the prism 
    	addSequential(new AutonTurn(180, false));//Turn around back towards the scale
    	addSequential(new EncGyroPlease(1000,1000,1.0,true));//Go towards the scale 
    	/* Auton for switch then scale that is unused
    	addSequential(new AutonWait(0.25));
    	addSequential(new EncGyroPlease(4200,4200,1.0,true));
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(1700,1700,1.0,true));
    	addSequential(new AutonWait(0.25));
    	addSequential(new AutonTurn(85, false));
    	addSequential(new EncGyroPlease(700,700,1.0,true));
    	addSequential(new AutonWait(0.25));
    	addSequential(new AutonTurn(85, false));
    	addSequential(new AutonWait(0.25));
       	addSequential(new UltraGyroMoveStraight(15,0.65,true));
       	addSequential(new EncGyroPlease(1000,1000,1.0,false));
       	addSequential(new AutonTurn(180,false));*/
    }
}
