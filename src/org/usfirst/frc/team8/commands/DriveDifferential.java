package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;

import org.usfirst.frc.team8.lib.SynchronousPID;

/**
*@author bertd
*/
public class DriveDifferential extends Command {
	
	private SynchronousPID pid;
	public DriveDifferential() {
		super("Drive Differential");
		pid = new SynchronousPID();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
		
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
