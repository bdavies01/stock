package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;

/**
*@author bertd
*/
public class DriveDifferential extends Command {
	
	private double time;
	private double differential;
	private double driveSpeed;
	public DriveDifferential(double time, double driveSpeed, double differential) {
		super("Drive Differential");
		this.time = time;
		this.differential = differential;
		this.driveSpeed = driveSpeed;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		drivetrain.arcade(driveSpeed, differential);
	}

	@Override
	protected boolean isFinished() {
		return time > this.timeSinceInitialized(); 
		
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
