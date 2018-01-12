package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DrivetrainBuild {
		
	public final static Drivetrain build() {
			
		//Speed Controller Group
		SpeedControllerGroup leftGroup = new SpeedControllerGroup(
				new WPI_TalonSRX(Constants.frontLeftMPort), 
				new WPI_TalonSRX(Constants.midLeftMPort),
				new WPI_TalonSRX(Constants.backLeftMPort)
				);

		SpeedControllerGroup rightGroup = new SpeedControllerGroup(
				new WPI_TalonSRX(Constants.frontRightMPort), 
				new WPI_TalonSRX(Constants.midRightMPort),
				new WPI_TalonSRX(Constants.backRightMPort)
				);
		
		//Drivetrain Type (Tank)
		DifferentialDrive diffDrive = new DifferentialDrive(leftGroup, rightGroup);
		
		//Solenoid
		DoubleSolenoid shiftSol = new DoubleSolenoid(Constants.shiftSolPortOn, Constants.shiftSolPortOff);//Shifting
		//SENSORS
		//Encoders
		Encoder leftEncoder = new Encoder(Constants.leftEncPortA, Constants.leftEncPortB, false, EncodingType.k4X);
		
		Encoder rightEncoder = new Encoder(Constants.rightEncPortA, Constants.rightEncPortB, false, EncodingType.k4X);
		//Gyro
		
		AHRS gyro = new AHRS(SPI.Port.kMXP);

		double kPGyro = 0.25;
		//Ultrasonic
		AnalogInput ultraA = new AnalogInput(1);
		Ultrasonic ultra = new Ultrasonic(Constants.ultraPortIn, Constants.ultraPortOut);
		
	
		return new Drivetrain(  leftGroup ,
							    rightGroup,
							   //
							    diffDrive,
							   //
							    shiftSol,
							   //SENSORS
							   //Encoders
							    leftEncoder,
							    rightEncoder);

	}
	
}
