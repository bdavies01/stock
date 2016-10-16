package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.visionTable;
import static org.usfirst.frc.team8.robot.HAL.leftDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.rightDriveEncoder;
import static org.usfirst.frc.team8.robot.HAL.robotTable;

import org.usfirst.frc.team8.lib.SynchronousPID;
import org.usfirst.frc.team8.robot.HAL;
import org.usfirst.frc.team8.robot.OI;

/**
*@author bertd
*/
public class SingleAlign extends Command {

	private SynchronousPID pid = new SynchronousPID();
	private double xDisplacement = 0.0;
	private double waiting = 0.0;
	public SingleAlign() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		this.xDisplacement = visionTable.getNumber("skewangle", 100000);
		pid.setOutputRange(-1, 1);
		pid.setSetpoint(0.0);
		//pid.setPID(0.5, 0, 0.5);
		pid.setPID(robotTable.getNumber("pvalue", 0), robotTable.getNumber("ivalue", 0), robotTable.getNumber("dvalue", 0));
		leftDriveEncoder.reset();
		HAL.spartanBoard.reset();
		rightDriveEncoder.reset();
	}

	@Override
	protected void execute() {
		if(xDisplacement != 100000) {
			//double encError = Constants.pixelsPerDistance * (leftDriveEncoder.getDistance() - rightDriveEncoder.getDistance() /2);
			double speed = pid.calculate((xDisplacement - HAL.spartanBoard.getAngle()));
			//System.out.println(xDisplacement - encError);
			System.out.println("xdisplacement: " + xDisplacement);
			System.out.println("spt: " + HAL.spartanBoard.getAngle());
			System.out.println("error: " + pid.getError());
			System.out.println("speed " + speed);
			drivetrain.tank(-speed, speed);
			if(pid.onTarget(1)) {
				waiting = this.timeSinceInitialized();
			}
		} else {
			
		}
	}

	@Override
	protected boolean isFinished() {
		if(OI.commandCancelButton.get()) {
			return true;
		}
		return pid.onTarget(1) && this.timeSinceInitialized() - waiting > 1.5;
		
	}

	@Override
	protected void end() {
		System.out.println("ended");
		drivetrain.tank(0, 0);
	}

	@Override
	protected void interrupted() {
		
	}

}
