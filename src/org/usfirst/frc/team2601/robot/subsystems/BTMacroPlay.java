package org.usfirst.frc.team2601.robot.subsystems;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

/*Code outline to implement playing back a macro recorded in BTMacroRecord
*Be sure to read out of the same file created in BTMacroRecord
*BEWARE OF: setting your motors in a different order than in BTMacroRecord and changing motor values before
*time is up. Both issues are dealt with and explained below. Also only read/write from/to the motors 
*you have fully coded for, otherwise your code will cut out for no reason. 
*In main, the try/catch structure catches any IOExceptions or FileNotFoundExceptions. Necessary to play back
*the recorded routine during autonomous
*Dennis Melamed and Melanie (sorta, she slept)
*March 22nd, 2015
*/


public class BTMacroPlay {
	Scanner scanner;
	long startTime;

	boolean onTime = true;
	double nextDouble;
	
	Constants constants = Constants.getInstance();

	public BTMacroPlay() throws FileNotFoundException
	{
		//create a scanner to read the file created during BTMacroRecord
		//scanner is able to read out the doubles recorded into recordedAuto.csv (as of 2015)
		//scanner = new Scanner(new File(Robot.autoFile));
		
		//let scanner know that the numbers are separated by a comma or a newline, as it is a .csv file
		scanner.useDelimiter(",|\\n");
		
		//lets set start time to the current time you begin autonomous
		startTime = System.currentTimeMillis();	
	}
	
	public void play()
	{
		//if recordedAuto.csv has a double to read next, then read it
		if ((scanner != null) && (scanner.hasNextDouble()))
		{
			double t_delta;
			
			//if we have waited the recorded amount of time assigned to each respective motor value,
			//then move on to the next double value
			//prevents the macro playback from getting ahead of itself and writing different
			//motor values too quickly
			if(onTime)
			{
				//take next value
				nextDouble = scanner.nextDouble();
			}
			
			//time recorded for values minus how far into replaying it we are--> if not zero, hold up
			t_delta = nextDouble - (System.currentTimeMillis()-startTime);
			
			//if we are on time, then set motor values
			if (t_delta <= 0)
			{
				//for 2015 robot. these are all the motors available to manipulate during autonomous.
				//it is extremely important to set the motors in the SAME ORDER as was recorded in BTMacroRecord
				//otherwise, motor values will be sent to the wrong motors and the robot will be unpredicatable
				Robot.drivetrain.leftGroup.set(scanner.nextDouble());
				Robot.drivetrain.rightGroup.set(scanner.nextDouble());
				
				Robot.drivetrain.shiftSol.set(Robot.drivetrain.shiftSol.get());
				//storage.robot.getToteClamp().set(storage.robot.getToteClamp().isExtended());
				
				//go to next double
				onTime = true;
			}
			//else don't change the values of the motors until we are "onTime"
			else
			{
				onTime = false;
			}
		}
		//end play, there are no more values to find
		else
		{
			this.end();
			if (scanner != null) 
			{
				scanner.close();
				scanner = null;
			}
		}
		
	}
	
	//stop motors and end playing the recorded file
	public void end()
	{
		Robot.drivetrain.leftGroup.set(0);
		Robot.drivetrain.rightGroup.set(0);
		
		//all this mess of a method does is keep the piston in the same state it ended in
		//if you want it to return to a specific point at the end of auto, change that here
		//storage.robot.getToteClamp().set(storage.robot.getToteClamp().isExtended());
		
		if (scanner != null)
		{
			scanner.close();
		}
		
	}
	
}
