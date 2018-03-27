/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2601.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.CenterSwitchLPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.CenterSwitchRPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleLPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleLPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleLPos3;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleRPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleRPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.DoubleScaleRPos3;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLLLPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLLLPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLLLPos3;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLRLPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLRLPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchLRLPos3;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRLRPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRLRPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRLRPos3;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRRRPos1;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRRRPos2;
import org.usfirst.frc.team2601.robot.commands.AutonCommands.ScaleSwitchRRRPos3;
import org.usfirst.frc.team2601.robot.commands.drivetrain.DiffDrive;
import org.usfirst.frc.team2601.robot.subsystems.ArmPivot;
import org.usfirst.frc.team2601.robot.subsystems.Arms;
import org.usfirst.frc.team2601.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2601.robot.subsystems.Elevator;
import org.usfirst.frc.team2601.robot.subsystems.Scaler;
import org.usfirst.frc.team2601.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot { 
	
	Constants constants = Constants.getInstance();
	
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Arms arms = new Arms();
	public static final Scaler scaler = new Scaler();
	public static final Elevator elevator = new Elevator();
	public static final ArmPivot pivot = new ArmPivot();
	public static OI m_oi = new OI();
	public static Vision vision = new Vision();
	Command m_autonomousCommand;
	SendableChooser m_chooser = new SendableChooser();
	public static Compressor compressor = new Compressor(0);
	Thread visionThread;
		
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//SmartDashboard.putNumber("constants.robotPos", constants.robotPos);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		
		/*new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				//Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				outputStream.putFrame(output);
			}
		}).start();		*/
		m_chooser.addObject("Position 1", 1);
		m_chooser.addObject("Position 2", 2);
		m_chooser.addObject("Position 3", 3);
		SmartDashboard.putData("constants.robotPosition", m_chooser);
		Robot.drivetrain.leftEnc.reset();
		Robot.drivetrain.rightEnc.reset();
		compressor.enabled();
		compressor.start();
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
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//constants.robotPos = (int) SmartDashboard.getNumber("constants.robotPos",0);
		Robot.arms.armSol.set(DoubleSolenoid.Value.kReverse);
		//320x240
		String gameData;
		Alliance currAlliance;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		currAlliance = DriverStation.getInstance().getAlliance();
		
		//All positions are the same from the perspectives of both alliances 
		//Position one double scale autonomous
		if(constants.robotPos == 1 && constants.doubleScale == true) {	
			if(gameData.charAt(1) == 'L') {//LLL or RLR
				m_autonomousCommand = new DoubleScaleLPos1();
				System.out.println("Left side double scale auton in Pos1");
			}else if(gameData.charAt(1) == 'R') {//LRL or RRR
				m_autonomousCommand = new DoubleScaleRPos1();
				System.out.println("Right side double scale auton in Pos1");
			}
		//Position two double scale autonomous
		}else if(constants.robotPos == 2 && constants.doubleScale == true && constants.onlySwitch == false) {
			if(gameData.charAt(1) == 'L') {//LLL or RLR
				m_autonomousCommand = new DoubleScaleLPos2();
			}else if(gameData.charAt(1) == 'R') {//LRL or RRR
				m_autonomousCommand = new DoubleScaleRPos2();
				System.out.println("Right side double scale auton in Pos2");
			} 
		//Position three double scale autonomous	
		}else if(constants.robotPos == 3 && constants.doubleScale == true) {
			if(gameData.charAt(1) == 'L') {//LLL or RLR
				m_autonomousCommand = new DoubleScaleLPos3();
				System.out.println("Left side double scale auton in Pos3");
			}else if(gameData.charAt(1) == 'R') {//LRL or RRR
				m_autonomousCommand = new DoubleScaleRPos3();
				System.out.println("Right side double scale auton in Pos3");	
			}
		//Position one scale switch autonomous 
		}else if(constants.robotPos == 1 && constants.doubleScale == false) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {//LLL 
				m_autonomousCommand = new ScaleSwitchLLLPos1();
				System.out.println("LLL scale switch auton in Pos1");
			}else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {//LRL
				m_autonomousCommand = new ScaleSwitchLRLPos1();
				System.out.println("LRL scale switch auton in Pos1");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {//RLR
				m_autonomousCommand = new ScaleSwitchRLRPos1();
				System.out.println("RLR scale switch auton in Pos1");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {//RRR
				m_autonomousCommand = new ScaleSwitchRRRPos1();
				System.out.println("RRR scale switch auton in Pos1");
			}
		//Position two scale switch autonomous
		}else if(constants.robotPos == 2 && constants.doubleScale == false && constants.onlySwitch == false) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {//LLL 
				m_autonomousCommand = new ScaleSwitchLLLPos2();
				System.out.println("LLL scale switch auton in Pos2");
			}else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {//LRL
				m_autonomousCommand = new ScaleSwitchLRLPos2();
				System.out.println("LRL scale switch auton in Pos2");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {//RLR
				m_autonomousCommand = new ScaleSwitchRLRPos2();
				System.out.println("RLR scale switch auton in Pos2");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {//RRR
				m_autonomousCommand = new ScaleSwitchRRRPos2();
				System.out.println("RRR scale switch auton in Pos2");
			}
		//Position three scale switch autonomous
		}else if(constants.robotPos == 3 && constants.doubleScale == false) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {//LLL 
				m_autonomousCommand = new ScaleSwitchLLLPos3();
				System.out.println("LLL scale switch auton in Pos3");
			}else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {//LRL
				m_autonomousCommand = new ScaleSwitchLRLPos3();
				System.out.println("LRL scale switch auton in Pos3");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {//RLR
				m_autonomousCommand = new ScaleSwitchRLRPos3();
				System.out.println("RLR scale switch auton in Pos3");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {//RRR
				m_autonomousCommand = new ScaleSwitchRRRPos3();
				System.out.println("RRR scale switch auton in Pos3");
			}
		//Position 2 CenterSwitch
		}else if(constants.robotPos == 2 && constants.doubleScale == false && constants.onlySwitch == true) {
			if(gameData.charAt(0) == 'L') {//LLL 
				m_autonomousCommand = new CenterSwitchLPos2();
			}else if(gameData.charAt(0) == 'R') {//RLR
				m_autonomousCommand = new CenterSwitchRPos2();
			}
		}else {
			//CrossAutoLine
		}
		// schedule the autonomous command (example)
	if (m_autonomousCommand != null) {
		m_autonomousCommand.start();
	}
}
		
			
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//compressor.start();
	}

	@Override
	public void teleopInit() {
		Robot.arms.armSol.set(DoubleSolenoid.Value.kForward);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
