package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.robot.HAL;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class GrabberToggle extends Command {
	private boolean up;
	
	public GrabberToggle(boolean up) {
		this.up = up;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if(up) {
			HAL.grabberSolenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			HAL.grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
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
