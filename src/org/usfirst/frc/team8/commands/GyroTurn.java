package org.usfirst.frc.team8.commands;

import org.usfirst.frc.team8.lib.SynchronousPID;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.spartanBoard;
import static org.usfirst.frc.team8.robot.HAL.robotTable;

import edu.wpi.first.wpilibj.command.Command;

/**
*@author bertd
*/
public class GyroTurn extends Command {
	private SynchronousPID pid;
	private double angle;
	public GyroTurn(double angle) {
		requires(drivetrain);
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		pid = new SynchronousPID();
		pid.setSetpoint(angle);
		spartanBoard.reset();
		pid.setPID(robotTable.getNumber("pvalue", 0), robotTable.getNumber("ivalue", 0), robotTable.getNumber("dvalue", 0));
	}
	@Override
	protected void execute() {
		double speed = pid.calculate(spartanBoard.getAngle());
		System.out.println("error: " + pid.getError());
		System.out.println("speed: " + speed);
		drivetrain.tank(-speed, speed);
	}
	@Override
	protected boolean isFinished() {
		return false;
		
	}
	@Override
	protected void end() {
	}
	@Override
	protected void interrupted() {
	}
}
