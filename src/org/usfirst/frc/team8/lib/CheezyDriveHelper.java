package org.usfirst.frc.team8.lib;
import org.usfirst.frc.team8.robot.Constants;
import org.usfirst.frc.team8.robot.HAL;
import org.usfirst.frc.team8.robot.OI;

public class CheezyDriveHelper {
	double oldWheel, quickStopAccumulator;
	private final double deadband = 0.02;
	
	public void cheezyDrive(double throttle, double wheel, boolean isHighGear) {
		double wheelNonLinearity;
		
		boolean isQuickTurn = OI.quickTurn.get();
		throttle = handleDeadband(throttle, deadband);
		wheel = handleDeadband(wheel, deadband);
		double negativeInertia = wheel - oldWheel;
		oldWheel = wheel;
		
		if(isHighGear) {
			wheelNonLinearity = 0.6;
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		} else {
			wheelNonLinearity = 0.5;
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		}
		
		double leftSpeed, rightSpeed, overPower;
		double sensitivity;
		double angularPower, linearPower;
		
		double negativeInertiaAccumulator = 0.0;
		double negativeInertiaScalar;
		
		if(isHighGear) {
			if(Math.abs(wheel) > 0.65) {
				negativeInertiaScalar = 6.5;
			} else {
				negativeInertiaScalar = 4.5;
			}
			sensitivity = Constants.driveTurnSensitivityHigh;
		} else {
			if(wheel * negativeInertia > 0) {
				negativeInertiaScalar = 2.5;
			} else {
				if(Math.abs(wheel) > 0.65) {
					negativeInertiaScalar = 5.5;
				} else {
					negativeInertiaScalar = 3.0;
				}
			}
			sensitivity = Constants.driveTurnSensitivityLow;
		}
		
		double negativeInertiaPower = negativeInertia * negativeInertiaScalar;
		negativeInertiaAccumulator += negativeInertiaPower;
		wheel = wheel + negativeInertiaAccumulator;
		
		if(negativeInertiaAccumulator > 1) {
			negativeInertiaAccumulator -= 1;
		} else if(negativeInertiaAccumulator < -1) {
			negativeInertiaAccumulator += 1;
		} else {
			negativeInertiaAccumulator = 0;
		}
		
		linearPower = throttle;
		
		if(isQuickTurn) {
			if(Math.abs(linearPower) > 0.2) {
				double alpha = 0.1;
				double wheelLimit = Math.min(Math.max(wheel, -1.0), 1.0);
				quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * wheelLimit * 5;
			}
			overPower = 1.0;
			sensitivity = 1.0;
			angularPower = wheel;
			
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			} else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			} else {
				quickStopAccumulator = 0.0;
			}
		}
		
		rightSpeed = leftSpeed = linearPower;
		leftSpeed += angularPower;
		rightSpeed -= angularPower;
		
		if (leftSpeed > 1.0) {
			rightSpeed -= overPower * (leftSpeed - 1.0);
			leftSpeed = 1.0;
		} else if (rightSpeed > 1.0) {
			leftSpeed -= overPower * (rightSpeed - 1.0);
			rightSpeed = 1.0;
		} else if (leftSpeed < -1.0) {
			rightSpeed += overPower * (-1.0 - leftSpeed);
			leftSpeed = -1.0;
		} else if (rightSpeed < -1.0) {
			leftSpeed += overPower * (-1.0 - rightSpeed);
			rightSpeed = -1.0;
		}
		HAL.drivetrain.tank(leftSpeed, rightSpeed);
	}
	
	public double handleDeadband(double val, double deadband) {
		return Math.abs(val) > Math.abs(deadband) ? val : 0.0;
	}
}
