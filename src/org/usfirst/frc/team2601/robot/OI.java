/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.commands.arm.ArmPiston;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.ElevatorMotor;
import org.usfirst.frc.team2601.robot.commands.elevator.WormDrive;
import org.usfirst.frc.team2601.robot.commands.scaler.ScalerButton;
import org.usfirst.frc.team2601.robot.commands.scaler.ScalerButtonStop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
		
	public OI() {
		//Get constants
		Constants constants = Constants.getInstance();
		
		//Instantiate Joysticks
		constants.dJS = new Joystick(constants.dJSPort);
		constants.oBB = new Joystick(constants.oBBPort);
		
		//Buttons for Driver
		Button shift = new JoystickButton(constants.dJS, constants.shiftB);
		shift.whenPressed(new ShiftGear());
		
		//Buttons for Operator
		Button armPiston = new JoystickButton(constants.oBB, constants.armPistonB);
		armPiston.whenPressed(new ArmPiston());
		
		Button wormDrive = new JoystickButton(constants.oBB, constants.wormDriveB);
		wormDrive.whenPressed(new WormDrive());
		
		Button elevator = new JoystickButton(constants.oBB, constants.elevatorB);
		elevator.whenPressed(new ElevatorMotor());
		
		Button scaler = new JoystickButton(constants.oBB, constants.scalerB);
		scaler.whenActive(new ScalerButton());
		scaler.whenInactive(new ScalerButtonStop());
	}
}
