package org.usfirst.frc.team8.commands;

import java.util.function.DoubleSupplier;
import static org.usfirst.frc.team8.robot.HAL.shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class TeleopShooter extends Command {

	private DoubleSupplier yAxis;
	public TeleopShooter(DoubleSupplier yAxis) {
		requires(shooter);
		this.yAxis = yAxis;
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooter.moveAtSpeed((((22.2/5) * Math.pow(yAxis.getAsDouble(), 5)) - (11.1 * Math.pow(yAxis.getAsDouble(), 4)) + ((22.2/3) * Math.pow(yAxis.getAsDouble(), 3))));
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
