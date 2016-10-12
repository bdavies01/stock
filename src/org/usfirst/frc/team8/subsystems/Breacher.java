package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.commands.TeleopBreacher;
import org.usfirst.frc.team8.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Breacher extends Subsystem {

	public Breacher() {
		super("Breacher");
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopBreacher(() -> OI.breacherAxis));
	}

}
