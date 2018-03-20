package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLRLPos3 extends CommandGroup {

    public ScaleSwitchLRLPos3() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		addSequential(new EncGyroPlease(13000,13000,1.0,true));//Forward towards switch
    	addSequential(new AutonWait(0.5));//Delay
		addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, true));//Turn right in low 88
    	addSequential(new EncGyroPlease(900,900,1.0,false));
    	addSequential(new AutoPivot(25,false));
    	addSequential(new AutoElevator(30000,true));//elevator up
    	addSequential(new RollerOuttake(1.5,false));
    	addSequential(new AutoElevator(30000,false));//elevator down
    	addSequential(new EncGyroPlease(400,400,1.0,true));
    	
    	///HAVENT BEEN TESTED
    	addSequential(new AutonTurn(90, true));//Turn right the switch
    	addSequential(new EncGyroPlease(800,800,1.0,true));
    	addSequential(new AutonTurn(90, false));
    	addSequential(new AutonWait(0.2));//Delay
    	addSequential(new ShiftGear());
    	addSequential(new EncGyroPlease(7850,7850,1.0,true));
    	addSequential(new AutonWait(0.2));
    	addSequential(new AutonTurn(90,true));
    	/*addParallel(new EncGyroPlease(5500,5500,0.75,true));//Travel towards the switch
    	addSequential(new RollerIntake(4.0, false));*/
    	addSequential(new IntakeForward(5500,5500,0.75,true));
    	addSequential(new AutoElevator(10000,true));//elevator up
    	//addSequential(new AutoPivot(35,false));
    	addSequential(new RollerOuttake(1.5,false));
    }
}
