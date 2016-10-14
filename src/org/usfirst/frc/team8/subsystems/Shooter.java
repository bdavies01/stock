package org.usfirst.frc.team8.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import static org.usfirst.frc.team8.robot.HAL.shooterArm;

import org.usfirst.frc.team8.commands.TeleopShooter;
import org.usfirst.frc.team8.robot.OI;

public class Shooter extends Subsystem{

	public Shooter() {
		super("Shooter");
		shooterArm.enableBrakeMode(false);
		shooterArm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		//shooterArm.changeControlMode(TalonControlMode.Position);
		//shooterArm.setPID(Constants.shooterPositionP, Constants.shooterPositionI, Constants.shooterPositionD);
		//shooterArm.setReverseSoftLimit(Constants.shooterReverseLimit);
		//shooterArm.setForwardSoftLimit(Constants.shooterForwardLimit);
		shooterArm.reverseOutput(false);
		shooterArm.configMaxOutputVoltage(0.971 * 254 / 330);
		shooterArm.reverseSensor(false);
	}
	
	public void moveAtSpeed(double speed) {
		shooterArm.changeControlMode(TalonControlMode.PercentVbus);
		shooterArm.set((((22.2/5) * Math.pow(speed, 5)) - (11.1 * Math.pow(speed, 4)) + ((22.2/3) * Math.pow(speed, 3))));
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopShooter(OI.shooterAxis));
	}

}
