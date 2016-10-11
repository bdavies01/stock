package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.shooterLock;

/**
*@author bertd
*/
public class ShooterUnlock extends Command {

	public ShooterUnlock() {
		
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		shooterLock.set(DoubleSolenoid.Value.kReverse);
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
