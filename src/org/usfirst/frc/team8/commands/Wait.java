package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class Wait extends Command {

	private double time;
	private double starttime;
	public Wait(double timeinmillis) {
		this.time = timeinmillis;
	}
	
	@Override
	protected void initialize() {
		this.starttime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		System.out.println("waiting...");
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - starttime > time;
		
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
