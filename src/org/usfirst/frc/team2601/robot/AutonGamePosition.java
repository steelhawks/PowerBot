package org.usfirst.frc.team2601.robot;

import edu.wpi.first.wpilibj.DriverStation.Alliance;

public enum AutonGamePosition {

	LEFT_RED,
	LEFT_BLUE,
	RIGHT_RED,
	RIGHT_BLUE;
	
	public static AutonGamePosition getInstance(String gameData, Alliance alliance) {

		return AutonGamePosition.LEFT_BLUE;
	}
	
	@Override
	public String toString() {
		return "AUTON_" + this.name();
	}
	
}
