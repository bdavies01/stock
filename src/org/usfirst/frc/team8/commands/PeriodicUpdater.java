package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.robot.HAL;

import edu.wpi.first.wpilibj.command.Command;

public class PeriodicUpdater extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		HAL.table.putNumber("matchtime", HAL.ds.getMatchTime());
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
