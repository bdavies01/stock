package org.usfirst.frc.team8.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import static org.usfirst.frc.team8.robot.HAL.shooterArm;

import org.usfirst.frc.team8.commands.TeleopShooter;
import org.usfirst.frc.team8.robot.Constants;
import org.usfirst.frc.team8.robot.OI;

public class Shooter extends Subsystem{

	public Shooter() {
		super("Shooter");
		shooterArm.enableBrakeMode(true);
		shooterArm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterArm.changeControlMode(TalonControlMode.Position);
		shooterArm.setPID(Constants.shooterPositionP, Constants.shooterPositionI, Constants.shooterPositionD);
		shooterArm.setReverseSoftLimit(Constants.shooterReverseLimit);
		shooterArm.setForwardSoftLimit(Constants.shooterForwardLimit);
		shooterArm.reverseOutput(false);
		shooterArm.reverseSensor(false);
	}
	
	public enum State {
		RETRACTING,
		EXTENDING, 
		LOCKING,
		UNLOCKING
	}
	
	public void moveAtSpeed(double speed) {
		shooterArm.changeControlMode(TalonControlMode.PercentVbus);
		shooterArm.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopShooter(() -> OI.operatorStick.getRawAxis(5) * -1));
	}

}
