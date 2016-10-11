package org.usfirst.frc.team8.commands;

import java.util.function.DoubleSupplier;
import static org.usfirst.frc.team8.robot.HAL.shooterArm;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class TeleopShooter extends Command {

	private DoubleSupplier yAxis;
	public TeleopShooter(DoubleSupplier yAxis) {
		this.yAxis = yAxis;
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooterArm.set(yAxis.getAsDouble());
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
