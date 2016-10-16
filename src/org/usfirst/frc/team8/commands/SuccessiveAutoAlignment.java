package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.leftDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.rightDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.visionTable;

import org.usfirst.frc.team8.robot.OI;

/**
*@author bertd
*/
public class SuccessiveAutoAlignment extends Command {
	private double speedLimit;
	private double xdisplacement;
	private double previousError;
	private boolean turningAngle;
	private double counter;
	private boolean done;
	private boolean keepGoing;

	/**
	 * This command creates turn angle commands that are called repeatedly until
	 * the desired xdisplacement is met. 
	 */
	public SuccessiveAutoAlignment() {
		requires(drivetrain);
		this.speedLimit = 1.0;
	}

	/**
	 * Overloaded constructor to allow for a speed limit while turning. 
	 * @param speed limit of the drivetrain. 
	 */
	public SuccessiveAutoAlignment(double speedLimit) {
		requires(drivetrain);
		this.speedLimit = speedLimit;
	}

	@Override
	public void initialize() {
		this.xdisplacement = visionTable.getNumber("xdisplacement", this.xdisplacement);
		this.previousError = 0.0;
		this.turningAngle = true;
		this.keepGoing = true;
		this.done = false;
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		counter = 0.0;
	}

	@Override
	public void execute() {
		keepGoing = true;
		if (turningAngle) {
			double encoderDisplacement = 42.394 * (leftDriveEncoder.getDistance()
					- rightDriveEncoder.getDistance()) / 2;
			
			double error = xdisplacement - encoderDisplacement;
			double derivative = (error - previousError) * 50;
			previousError = error;

			double leftSpeed = Math.min(
					Math.max(0.045 * error + 0.0 * derivative, -speedLimit), speedLimit);
			double rightSpeed = Math.min(
					Math.max(0.045 * error + 0.0 * derivative, -speedLimit), speedLimit);

			drivetrain.tank(leftSpeed, rightSpeed);

			if (Math.abs(error) > 5) {
				done = false;
				keepGoing = false;
			} else {
				drivetrain.tank(0, 0);
				turningAngle = false;
			}
		} else if (!turningAngle && keepGoing){
			counter++;
		}
		
		if (!checkDisplacement() && !turningAngle && counter >= 60 && keepGoing) {
			xdisplacement = visionTable.getNumber("xdisplacement", this.xdisplacement);
			leftDriveEncoder.reset();
			rightDriveEncoder.reset();
			previousError = 0.0;
			turningAngle = true;
			counter = 0.0;
			done = false;
			keepGoing = false;
			
		} else if (checkDisplacement() && !turningAngle && counter >= 60 && keepGoing) {
			done = true;
			keepGoing = false;
		}
	}
	
	@Override
	protected boolean isFinished() {
		if(OI.commandCancelButton.get()) { // Hard breakout
			return true;
		}
		return done;
	}

	/**
	 * Checks to see if the xdisplacement is in an acceptable range. Returns
	 * true if acceptable, and false if not. 
	 */
	public boolean checkDisplacement() {
		double tempDisplacement = visionTable.getNumber("xdisplacement", this.xdisplacement);
		if (Math.abs(tempDisplacement) < 5) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void interrupted() {
		
	}

	/**
	 * Overrides the stock end method. Called when the command ends
	 * and stops the drivetrain. 
	 */
	@Override
	public void end() {
		drivetrain.tank(0, 0);
	}
}
