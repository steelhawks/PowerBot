package org.usfirst.frc.team2601.robot.commands.AutonCommands;

import org.usfirst.frc.team2601.robot.commands.ArmPivot.AutoPivot;
import org.usfirst.frc.team2601.robot.commands.arm.RollerIntake;
import org.usfirst.frc.team2601.robot.commands.arm.RollerOuttake;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonTurn;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevator;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoElevatorTimeout;
import org.usfirst.frc.team2601.robot.commands.elevator.AutoLimitElevator;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.AutonWait;
import org.usfirst.frc.team2601.robot.commands.IntakeForward;
import org.usfirst.frc.team2601.robot.subsystems.ArmPivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSwitchLRLPos1 extends CommandGroup {

    public ScaleSwitchLRLPos1() {
    	Robot.drivetrain.frontLeftM.setSafetyEnabled(false);
		Robot.drivetrain.midLeftM.setSafetyEnabled(false);
		Robot.drivetrain.frontRightM.setSafetyEnabled(false);
		Robot.drivetrain.midRightM.setSafetyEnabled(false);
		Robot.drivetrain.frontLeftM.setExpiration(120);
		Robot.drivetrain.midLeftM.setExpiration(120);
		Robot.drivetrain.frontRightM.setExpiration(120);
		Robot.drivetrain.midRightM.setExpiration(120);
		addSequential(new AutoPivot(950,false));//Drop the arms down
		addSequential(new EncGyroPlease(6000,6000,1.0,true));//Forward towards switch
    	addSequential(new AutonWait(0.5));//Delay
		addSequential(new ShiftGear());//Shift low to slow it down to turn
    	addSequential(new AutonTurn(88, false));//Turn right in low 88 to face switch
    	addSequential(new EncGyroPlease(400,400,1.0,true));//Go in towards the switch
    	addSequential(new RollerOuttake(1.5,false));//Shoot the cube
    	addSequential(new AutoLimitElevator(6500,false));//Lower the elevator
    	addSequential(new EncGyroPlease(400,400,1.0,false));//Move back 
    	addSequential(new AutonTurn(90, true));//Turn 90 degrees 
   }
    
}
