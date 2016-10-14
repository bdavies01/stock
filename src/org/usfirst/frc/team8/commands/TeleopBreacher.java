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
	private double bert;
	public TeleopBreacher(DoubleSupplier breacherAxis) {
		requires(breacher);
		this.breacherAxis = breacherAxis;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		bert = breacherAxis.getAsDouble() * 0.28;
		//breacherArm.set(((22.2/5) * Math.pow(bert, 5)) - (11.1 * Math.pow(bert, 4)) + ((22.2/3) * Math.pow(bert, 3)));
		breacherArm.set(Math.sin(Math.sin(Math.sin(bert))));
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
