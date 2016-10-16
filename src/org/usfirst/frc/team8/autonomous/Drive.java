package org.usfirst.frc.team8.autonomous;

import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.leftDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.rightDriveEncoder;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author robbies
*/
public class Drive extends Command {
	
	private final boolean forward; // set all the output to negative
	private final double maxDistance;  // max encoder distance
	private final double maxOutput; // max motor output
	private final double maxTime;
	private boolean done;
	private double maxTimeRelativeToEpoch; // time when the command will end
	
	/**
	 * Create a competition drive auto command.
	 * @param driveTrain	the drivetrain controller
	 * @param forward	should the motors be going forward or backward?
	 * @param maxTime	how many SECONDS should the command run for
	 * @param maxDistance	max encoder distance
	 * @param maxOutput	max motor output
	 */
	public Drive(boolean forward, int maxTime, double maxDistance,
			double maxOutput) {
		requires(drivetrain);
		this.maxTime = maxTime;
		this.forward = forward;
		this.maxDistance = maxDistance;
		this.maxOutput = maxOutput;
		
	}
	
	/**
	 * Overloaded constructor that defaults the time, 
	 * max out, and max distance.
	 */
	public Drive(boolean forward) {
		this(forward, Integer.MAX_VALUE, Double.MAX_VALUE, .5);
	}
	
	/**
	 * Same as first constructor but defaults the maxDistance
	 * to Double.MAX_VALUE.
	 */
	public Drive(boolean forward, int maxTime, double maxOutput) {
		this(forward, maxTime, Double.MAX_VALUE, maxOutput);
	}
	
	/**
	 * Defalts the time and distance to max value.
	 */
	public Drive(boolean forward, double maxOutput) {
		this(forward, Integer.MAX_VALUE, Double.MAX_VALUE, maxOutput);
	}
	
	@Override
	protected void initialize() {
		this.maxTimeRelativeToEpoch = System.currentTimeMillis() + 1000*maxTime;
		this.done = false;
	}
	
	@Override
	public void execute() {
		// Make sure we aren't over the time limit
		if (this.maxTimeRelativeToEpoch < System.currentTimeMillis()) {
			done = true;
		}
		
		if (this.forward) {
			// encoder checks 
			if(leftDriveEncoder.getDistance() > this.maxDistance) {
				done = true;
			}
			
			if(rightDriveEncoder.getDistance() > this.maxDistance) {
				done = true;
			}
		}
		else {
			// check in reverse if we are going backward.
			if(leftDriveEncoder.getDistance() < -this.maxDistance) {
				done = true;
			}
			
			if(rightDriveEncoder.getDistance() < -this.maxDistance) {
				done = true;
			}
		}
		
		if(forward) {
			drivetrain.tank(maxOutput, -maxOutput);
		} else {
			drivetrain.tank(-maxOutput, maxOutput);
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		return done;
		
	}

	@Override 
	public void interrupted() {
		
	}
	
	@Override
	public void end() {
		drivetrain.tank(0, 0);
	}
}