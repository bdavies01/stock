package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class Wait extends Command {

	private double time;
	public Wait(double time) {
		this.time = time;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return this.timeSinceInitialized() > time;
		
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
