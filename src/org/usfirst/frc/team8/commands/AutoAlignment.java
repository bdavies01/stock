package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.leftDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.rightDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.visionTable;

import org.usfirst.frc.team8.lib.SynchronousPID;
import org.usfirst.frc.team8.robot.Constants;
import org.usfirst.frc.team8.robot.OI;

/**
 * @author bertd
 */
public class AutoAlignment extends Command {
	private double speedLimit;
	private double xDisplacement;
	private double error;
	private int counter;
	private boolean turningAngle;
	private boolean done;
	private SynchronousPID pid;

	public AutoAlignment() {
		super("Auto Alignment");
		requires(drivetrain);
		this.speedLimit = 0.5;
		pid = new SynchronousPID();
	}

	public AutoAlignment(double speedLimit) {
		requires(drivetrain);
		this.speedLimit = speedLimit;
		pid = new SynchronousPID();
	}

	@Override
	protected void initialize() {
		turningAngle = true;
		done = false;
		xDisplacement = visionTable.getNumber("xdisplacement", 100000);
		error = Double.MAX_VALUE;
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		pid.setPID(0.1, 0, 0);
		pid.setOutputRange(-speedLimit, speedLimit);
		counter = 0;
	}

	@Override
	protected void execute() {
		if (turningAngle) {
			double encoderDisplacement = Constants.pixelsPerDistance
					* (leftDriveEncoder.getDistance() - rightDriveEncoder.getDistance()) / 2;
			error = xDisplacement - encoderDisplacement;
			pid.setSetpoint(0.0);
			double speed = pid.calculate(error);
			System.out.println(speed);
			drivetrain.tank(-speed, speed);
			if (Math.abs(pid.getError()) < Constants.acceptablePixelError) {
				turningAngle = false;
			} else {
				done = false;
			}
		} else {
			counter++;
		}

		if (!checkDisplacement() && !turningAngle && counter >= 60) {
			xDisplacement = visionTable.getNumber("xdisplacement", this.xDisplacement);
			turningAngle = true;
			counter = 0;
			done = false;
		} else if (checkDisplacement() && !turningAngle && counter >= 60) {
			done = true;
		}
	}

	@Override
	protected boolean isFinished() {
		if (OI.commandCancelButton.get()) {
			return true;
		} else {
			if (done) {
				return true;
			} else {
				return false;
			}
		}

	}

	@Override
	protected void end() {
		drivetrain.tank(0, 0);
	}

	@Override
	protected void interrupted() {
		drivetrain.tank(0, 0);
	}

	/**
	 * Checks to see if the xdisplacement is in an acceptable range. Returns
	 * true if acceptable, and false if not.
	 */
	public boolean checkDisplacement() {
		double tempDisplacement = visionTable.getNumber("xdisplacement", this.xDisplacement);
		if (Math.abs(tempDisplacement) < Constants.acceptablePixelError) {
			return true;
		} else {
			return false;
		}
	}

}
