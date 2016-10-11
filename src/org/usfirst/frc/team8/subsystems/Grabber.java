package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.commands.SetState.StatefulSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import static org.usfirst.frc.team8.robot.HAL.grabberSolenoid;

public class Grabber extends StatefulSubsystem<Grabber.State>{

	public Grabber() {
		
	}
	
	public enum State {
		RAISED,
		LOWERED
	}
	
	@Override
	public void setState(State state) {
		switch(state) {
		case RAISED:
			raise();
			break;
		case LOWERED:
			lower();
			break;
		default:
		}
	}
	
	public void raise() {
		grabberSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void lower() {
		grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
