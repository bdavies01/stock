package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.shooter;
import static org.usfirst.frc.team8.robot.HAL.shooterArm;

/**
*@author bertd
*/
public class SetShooterPower extends Command {

	private double power;
	public SetShooterPower(double power) {
		requires(shooter);
		this.power = power;
	}
	
	public SetShooterPower(boolean up) {
		requires(shooter);
		this.power = up ? 0.971 * 254 / 330 : -0.971 * 254 / 330;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooterArm.set(power);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
