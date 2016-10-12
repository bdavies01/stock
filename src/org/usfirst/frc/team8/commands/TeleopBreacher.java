package org.usfirst.frc.team8.commands;

import static org.usfirst.frc.team8.robot.HAL.breacher;
import static org.usfirst.frc.team8.robot.HAL.breacherArm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;;

/**
*@author bertd
*/
public class TeleopBreacher extends Command {
	private final DoubleSupplier breacherAxis;
	private double sensitivity;
	public TeleopBreacher(DoubleSupplier breacherAxis) {
		requires(breacher);
		this.breacherAxis = breacherAxis;
		this.sensitivity = 0.75;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		breacherArm.set(breacherAxis.getAsDouble() * sensitivity);
	}

	@Override
	protected boolean isFinished() {
		return false;
		
	}

	@Override
	protected void end() {
		breacherArm.set(0);
	}

	@Override
	protected void interrupted() {
		breacherArm.set(0);
	}
}
