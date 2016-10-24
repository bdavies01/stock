package org.usfirst.frc.team8.subsystems;

import org.usfirst.frc.team8.commands.TeleopBreacher;
import org.usfirst.frc.team8.robot.OI;
import static org.usfirst.frc.team8.robot.HAL.breacherArm;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Breacher extends Subsystem {

	public Breacher() {
		super("Breacher");
		breacherArm.enableBrakeMode(false);
		breacherArm.reverseOutput(false);
		breacherArm.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopBreacher(OI.breacherAxis));
	}
	
	public void enableBrake() {
		breacherArm.enableBrakeMode(true);
	}
	
	public void disableBrake() {
		breacherArm.enableBrakeMode(false);
	}

}
