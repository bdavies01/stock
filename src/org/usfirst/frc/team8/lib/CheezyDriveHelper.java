package org.usfirst.frc.team8.lib;
import org.usfirst.frc.team8.robot.HAL;

public class CheezyDriveHelper {
	private double old_wheel, quick_stop_acc;
	private final double DEADBAND = 0.02;
	
	public void cheezyDrive(double throttle, double wheel, boolean isQuickTurn) {
		boolean quick_turn = isQuickTurn;
		
		double wheel_non_linear;
		
		if(Math.abs(wheel) < DEADBAND) {
			wheel = 0;
		}
		if(Math.abs(throttle) < DEADBAND) {
			throttle = 0;
		}
		
		double neg_inertia = wheel - old_wheel;
		old_wheel = wheel;

		wheel_non_linear = 0.6f;
		wheel = Math.sin(Math.PI / 2.0 * wheel_non_linear * wheel) / Math.sin(Math.PI / 2.0 * wheel_non_linear);
		wheel = Math.sin(Math.PI / 2.0 * wheel_non_linear * wheel) / Math.sin(Math.PI / 2.0 * wheel_non_linear);
		wheel = Math.sin(Math.PI / 2.0 * wheel_non_linear * wheel) / Math.sin(Math.PI / 2.0 * wheel_non_linear);

		
		double left_PWM, right_PWM, over_power;
		double sensitivity;
		double angular_power;
		double linear_power;
		
		double neg_inertia_acc = 0.0;
		double neg_inertia_k;
		
		neg_inertia_k = 4.0;
		sensitivity = 0.75;
		
		double neg_inertia_power = neg_inertia * neg_inertia_k;
		neg_inertia_acc += neg_inertia_power;
		
		wheel += neg_inertia_acc;
		if(neg_inertia_acc > 1) {
			neg_inertia_acc -= 1;
		}
		else if(neg_inertia_acc < -1) {
			neg_inertia_acc += 1;
		}
		else {
			neg_inertia_acc = 0;
		}
		linear_power = throttle;
		
		if(quick_turn) {
			if(Math.abs(linear_power) < 0.2) {
				double alpha = 0.1;
				// Assumed that wheel limit is a reverse deadband 
				double wheel_limit = Math.min(Math.max(wheel, -1.0), 1.0);
				quick_stop_acc = (1 - alpha) * quick_stop_acc + alpha * wheel_limit * 5;
			}
			over_power = 1.0;
			sensitivity = 1.0;
			angular_power = wheel;
		}
		else {
			over_power = 0.0;
			angular_power = Math.abs(throttle) * wheel * sensitivity - quick_stop_acc;
			if(quick_stop_acc > 1) {
				quick_stop_acc -= 1;
			}
			else if(quick_stop_acc < -1) {
				quick_stop_acc += 1;
			}
			else {
				quick_stop_acc = 0;
			}
		}
		right_PWM = left_PWM = linear_power;
		left_PWM += angular_power;
		right_PWM -= angular_power;
		
		if(left_PWM > 1.0) {
			right_PWM -= over_power * (left_PWM - 1.0);
			left_PWM = 1.0;
		} else if (right_PWM > 1.0) {
			left_PWM -= over_power * (right_PWM - 1.0);
			right_PWM = 1.0; 
		} else if(left_PWM < -1.0) {
			right_PWM += over_power * (-1.0 - left_PWM);
			left_PWM = -1.0;
		} else if(right_PWM < -1.0) {
			left_PWM += over_power * (-1.0 - right_PWM);
			right_PWM = -1.0;
		}
		HAL.drivetrain.tank(-right_PWM, -left_PWM);
	}
	
	public double handleDeadband(double val, double deadband) {
		return Math.abs(val) > Math.abs(deadband) ? val : 0.0;
	}
}
