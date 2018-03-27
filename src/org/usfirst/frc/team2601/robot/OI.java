/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants.Operator_Type;
import org.usfirst.frc.team2601.robot.commands.MoveArmPivotShoot;
import org.usfirst.frc.team2601.robot.commands.MoveArmPivotUp;
import org.usfirst.frc.team2601.robot.commands.ArmPivot.ArmPivotCommand;
import org.usfirst.frc.team2601.robot.commands.arm.ArmIntakeButton;
import org.usfirst.frc.team2601.robot.commands.arm.ArmPiston;
import org.usfirst.frc.team2601.robot.commands.arm.ArmShootButton;
import org.usfirst.frc.team2601.robot.commands.arm.ArmStopButton;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ShiftGear;
import org.usfirst.frc.team2601.robot.commands.elevator.ElDown;
import org.usfirst.frc.team2601.robot.commands.elevator.ElUp;
import org.usfirst.frc.team2601.robot.commands.elevator.ElevatorJS;
import org.usfirst.frc.team2601.robot.commands.elevator.StopElM;
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
	Constants constants = Constants.getInstance();
	
	public Joystick djs = new Joystick(constants.dJSPort);
	public Joystick pivotJS = new Joystick(2);
	public Joystick placeholder;
	
	public OI() {
		//Instantiate Joysticks
		if(constants.operatorType == Operator_Type.Gamepad) {
			constants.gamepad = new F310(constants.gamepadPort);
			placeholder = constants.gamepad;
		}else if (constants.operatorType == Operator_Type.Joystick) {
			constants.oJS = new Joystick(constants.gamepadPort);
			placeholder = constants.oJS;
		}else {
			constants.BB = new ButtonBoard(constants.gamepadPort);
			placeholder = constants.BB;
		}
		//Buttons for Driver
		Button shift = new JoystickButton(djs, constants.shiftB);
		shift.whenPressed(new ShiftGear());
		
		//Buttons for Operator
		if (constants.autonBot == false) {
			//Button scaler = new JoystickButton(placeholder, constants.scalerB);
			//scaler.whenActive(new ScalerButton());
			//scaler.whenInactive(new ScalerButtonStop());
			
			/*Button elUp = new JoystickButton(placeholder, 4);
			elUp.whenPressed(new ElUp());
			elUp.whenInactive(new StopElM());
			*/
			Button elDown = new JoystickButton(placeholder, 1);
			constants.slowBtnOn = false;
			if(constants.slowBtnOn == true) {
				elDown.whenPressed(new ElDown());
				elDown.whenInactive(new StopElM());
			}
			Button armPiston = new JoystickButton(placeholder, constants.armPistonB);
			armPiston.whenPressed(new ArmPiston());
			
			Button armPivotShoot = new JoystickButton(placeholder, constants.armPivotDownB);
			armPivotShoot.whenPressed(new MoveArmPivotShoot());
			
			Button armPivotUp = new JoystickButton(placeholder, constants.armPivotUpB);
			armPivotUp.whenPressed(new MoveArmPivotUp());
			if(constants.operatorType == Operator_Type.Joystick) {
				Button armIntake = new JoystickButton(constants.oJS, constants.armIntakeB);
				armIntake.whenActive(new ArmIntakeButton());
				armIntake.whenInactive(new ArmStopButton());
				
				Button armShoot = new JoystickButton(placeholder, constants.armShootB);
				armIntake.whenActive(new ArmShootButton());
				armIntake.whenInactive(new ArmStopButton());
			}else if (constants.operatorType == Operator_Type.Gamepad){
				Button armIntake = new JoystickButton(constants.gamepad, 6);
				Button armShoot = new JoystickButton(constants.gamepad, 5);				
				armIntake.whenActive(new ArmIntakeButton());
				armShoot.whenActive(new ArmShootButton());
				armIntake.whenInactive(new ArmStopButton());
				armShoot.whenInactive(new ArmStopButton());
			}else {
				Button armIntake = new JoystickButton(constants.BB, 9);
				Button armShoot = new JoystickButton(constants.BB, 10);
				
				if(armIntake.get() == true && armShoot.get() == false) {
					armIntake.whenActive(new ArmIntakeButton());
				}else if(armIntake.get() == false && armShoot.get() == true) {
					armShoot.whenActive(new ArmShootButton());
				}else {
					armIntake.whenInactive(new ArmStopButton());
					armIntake.whenInactive(new ArmStopButton());
				}
			}
		}
	}
}
