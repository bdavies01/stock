package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class ForwardDrive extends Command {

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Constants.drivetrainOrientation = 1.0;
	}

	@Override
	protected boolean isFinished() {
		return true;
		
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
