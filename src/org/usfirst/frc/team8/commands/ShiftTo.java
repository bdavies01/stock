package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.robot.HAL;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author bertd
 *
 */
public class ShiftTo extends Command {
	private final Gear gear;
	public ShiftTo(Gear gear) {
		super("ShiftTo");
		this.gear = gear;
	}
	@Override
	protected void initialize() {
		HAL.drivetrain.shiftTo(gear);
		
	}
	@Override
	protected void execute() {
		HAL.drivetrain.shiftTo(gear);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
