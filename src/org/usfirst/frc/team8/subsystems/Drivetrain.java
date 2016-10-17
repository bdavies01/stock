package org.usfirst.frc.team8.subsystems;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.usfirst.frc.team8.robot.HAL.*;

import org.usfirst.frc.team8.commands.TeleopDrive;
import org.usfirst.frc.team8.robot.Constants;
import org.usfirst.frc.team8.robot.OI;

public class Drivetrain extends Subsystem implements PIDSource {
	private final RobotDrive robotDrive;
	
	public enum Gear {
		LOW,
		HIGH;
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	public Drivetrain() {
		super("Drivetrain");
		leftFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftFront.reverseSensor(false);
		leftBack.changeControlMode(TalonControlMode.Follower);
		leftBack.set(leftFront.getDeviceID());
		
		leftDriveEncoder.setDistancePerPulse(-0.184);
		rightDriveEncoder.setDistancePerPulse(0.184);
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		
		spartanBoard.calibrate();
		
		rightFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightFront.reverseSensor(true);
		rightBack.changeControlMode(TalonControlMode.Follower);
		rightBack.set(rightFront.getDeviceID());
		
		robotDrive = new RobotDrive(leftFront, rightFront);
		robotDrive.setSafetyEnabled(false);
	}
	
	public void arcade(double driveSpeed, double turnSpeed) {
		leftFront.changeControlMode(TalonControlMode.PercentVbus);
		rightFront.changeControlMode(TalonControlMode.PercentVbus);
		robotDrive.arcadeDrive(driveSpeed, turnSpeed);
	}
	
	public void tank(double left, double right) {
		leftFront.changeControlMode(TalonControlMode.PercentVbus);
		rightFront.changeControlMode(TalonControlMode.PercentVbus);
		robotDrive.tankDrive(left, right);
	}
	
	public void shiftTo(Gear gear) {
		switch (gear) {
		default:
		case HIGH: 
			//enable brake
			driveShifter.set(Value.kForward);
			break;
		case LOW:
			//disable brake
			driveShifter.set(Value.kReverse);
			break;
		}
	}
	
	public void shift() {
		if(getGear() == Gear.HIGH) {
			shiftTo(Gear.LOW);
		} else {
			shiftTo(Gear.HIGH);
		}
	}
	
	public void resetEncoders() {
		leftFront.setPosition(0);
		rightFront.setPosition(0);
	}
	
	public Gear getGear() {
		switch(driveShifter.get()) {
		case kForward:
			return Gear.HIGH;
		default:
		case kReverse:
			return Gear.LOW;
		}
	}
	
	public double getEncoderRotations() {
		double rotations;
		if(leftFront.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent) {
			return 0;
		}
		if(rightFront.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent) {
			return 0;
		}
		try {
			rotations = leftFront.getPosition() + rightFront.getPosition() / 2.0;
		} catch (Throwable e) {
			System.out.println("Couldn't get the drive encoder rotations");
			rotations = 0;
		}
		return rotations;
	}
	
	public double getVelocity() {
		double velocity;
		if(leftFront.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent) {
			return 0;
		} 
		if(rightFront.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusNotPresent) {
			return 0;
		}
		try {
			velocity = leftFront.getSpeed() + rightFront.getSpeed() / 2.0;
		} catch (Throwable e) {
			System.out.println("Couldn't get the drive velocity");
			velocity = 0;
		}
		return velocity;
	}
	
	public double getInches() {
		return getEncoderRotations() * Constants.driveWheelCircumference;
	}
	
	public double getSpeed() {
		return getVelocity() * Constants.driveWheelCircumference / 12.0 / 60.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TeleopDrive(OI.driveStickValue, OI.turnStickValue, ()-> OI.toggleAutoShift.get(), () -> OI.shiftDown.get(), () -> OI.shiftUp.get(), () ->OI.quickTurn.get()));
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getInches();
	}
	
	public void enableBrake() {
		setBrake(true);
	}
	
	public void disableBrake() {
		setBrake(false);
	}
	
	public void setBrake(boolean brake) {
		leftFront.enableBrakeMode(brake);
		leftBack.enableBrakeMode(brake);
		rightFront.enableBrakeMode(brake);
		rightBack.enableBrakeMode(brake);
	}
	
	public void holdPosition() {
		leftFront.setProfile(1);
		leftFront.changeControlMode(TalonControlMode.Position);
		leftFront.setSetpoint(leftFront.getPosition());
		
		rightFront.setProfile(1);
		rightFront.changeControlMode(TalonControlMode.Position);
		rightFront.setSetpoint(rightFront.getPosition());
	}

}
