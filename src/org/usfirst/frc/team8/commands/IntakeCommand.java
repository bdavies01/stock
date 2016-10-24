package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.robot.HAL;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class IntakeCommand extends Command {
	private double time;
	private boolean intake;
	/**
	 * 
	 * @param time in seconds
	 */
	public IntakeCommand(boolean intake, double time) {
		this.time = time;
		this.intake = intake;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		HAL.leftIntake.set(intake ? 0.5 : -0.5);
		HAL.rightIntake.set(intake ? 0.5 : -0.5);
	}

	@Override
	protected boolean isFinished() {
		return this.timeSinceInitialized() > time;
	}

	@Override
	protected void end() {
		HAL.leftIntake.set(0);
		HAL.rightIntake.set(0);
	}

	@Override
	protected void interrupted() {
	}

}
