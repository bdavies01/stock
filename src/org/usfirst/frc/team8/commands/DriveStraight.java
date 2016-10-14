package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.leftDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.rightDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.spartanBoard;

import org.usfirst.frc.team8.lib.SynchronousPID;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;

/**
*@author bertd
*/
public class DriveStraight extends Command {
	
	private SynchronousPID drivePid;
	private SynchronousPID turnPid;
	private double wait = 0.2;
	private double lastCheck = 0.0;
	private double timeAtSetpoint = 0.0;
	private double maxTime = 0.0;
	private double distance;
	
	public DriveStraight(double distance) {
		super("Drive Straight");
		this.drivePid = new SynchronousPID();
		this.turnPid = new SynchronousPID();
		this.maxTime = Double.MAX_VALUE;
		this.distance = distance;
		requires(drivetrain);
	}
	
	public DriveStraight(double distance, double time, double speedLimit) {
		super("Drive Straight");
		this.drivePid = new SynchronousPID();
		this.drivePid.setOutputRange(-speedLimit, speedLimit);
		this.maxTime = time;
		this.turnPid = new SynchronousPID();
		this.distance = distance;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		spartanBoard.reset();
		drivetrain.shiftTo(Gear.LOW);
		drivePid.setSetpoint(distance);
		turnPid.setSetpoint(0);
	}

	@Override
	protected void execute() {
		if(this.timeSinceInitialized() > 0.15) {
			drivetrain.arcade(drivePid.calculate(leftDriveEncoder.getDistance() + rightDriveEncoder.getDistance() / 2.0), turnPid.calculate(spartanBoard.getAngle()));
		} else {
			System.out.println("Waiting for reset " + this.timeSinceInitialized());
		}
	}

	@Override
	protected boolean isFinished() {
		if(this.timeSinceInitialized() > maxTime) {
			return true;
		}
		if(drivePid.onTarget(1.5)) {
			timeAtSetpoint += this.timeSinceInitialized() - lastCheck;
		} else {
			timeAtSetpoint = 0.0;
		}
		lastCheck = this.timeSinceInitialized();
		return timeAtSetpoint >= wait;
	}

	@Override
	protected void end() {
		drivetrain.tank(0, 0);
	}

	@Override
	protected void interrupted() {
		
	}

}
