package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team8.robot.HAL.visionTable;

/**
*@author bertd
*/
public class Successive extends Command {
	private AutoAlignment align = new AutoAlignment();
	private double breaktime = 0;
	private boolean done = false;
	
	public Successive() {
		
	}
	
	@Override
	protected void initialize() {
		align.start();
	}

	@Override
	protected void execute() {
		if(!align.isRunning()) {
			if(visionTable.getNumber("xdisplacement", 100000) > 5 && this.timeSinceInitialized() - breaktime > 60) {
				align = new AutoAlignment();
				align.start();
			} else if(visionTable.getNumber("xdisplacement", 100000) < 5 && this.timeSinceInitialized() - breaktime > 60) {
				done = true;
			}
		} else {
			breaktime = this.timeSinceInitialized();
		}
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
