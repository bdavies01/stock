package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.shooterLoad;
/**
*@author bertd
*/
public class ShooterRetract extends Command {

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooterLoad.set(DoubleSolenoid.Value.kReverse);
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
