package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.commands.SetState.StatefulSubsystem;
import static org.usfirst.frc.team8.robot.HAL.leftIntake;
import static org.usfirst.frc.team8.robot.HAL.rightIntake;
public class Intake extends StatefulSubsystem<Intake.State> {
	
	public Intake() {
		leftIntake.setInverted(false);
		leftIntake.setSafetyEnabled(false);
		rightIntake.setInverted(true);
		rightIntake.setSafetyEnabled(false);
	}
	
	public enum State {
		INTAKE,
		EXPEL,
		STOPPED
	}
	@Override
	public void setState(State state) {
		switch(state) {
		case INTAKE:
			intake();
			break;
		case EXPEL:
			expel();
			break;
		case STOPPED:
			stop();
			break;
		default:
				
		}
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void intake() {
		leftIntake.set(0.5);
		rightIntake.set(0.5);
	}
	
	public void expel() {
		leftIntake.set(-0.5);
		rightIntake.set(-0.5);
	}
	
	public void stop() {
		leftIntake.set(0);
		rightIntake.set(0);
	}
}
