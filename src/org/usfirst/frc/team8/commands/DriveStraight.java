package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.driveEncoderA;
import static org.usfirst.frc.team8.robot.HAL.driveEncoderB;

import org.usfirst.frc.team8.lib.SynchronousPID;

/**
*@author bertd
*/
public class DriveStraight extends Command {
	
	private SynchronousPID pid;
	public DriveStraight() {
		super("Drive");
		this.pid = new SynchronousPID();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		driveEncoderA.reset();
		driveEncoderB.reset();
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
