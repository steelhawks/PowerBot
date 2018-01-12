package org.usfirst.frc.team2601.robot.subsystems;

public class Subsystems {

	public static final Drivetrain drivetrain = DrivetrainBuild.build();
	
	public static NIDECSubsystem nidec = new NIDECSubsystem();
	
	public static Arms arms = new Arms();
	
	public static Scaler scaler = new Scaler();
	
	public static Elevator elevator = new Elevator();
	

}
