package org.usfirst.frc.team8.autonomous;

import static org.usfirst.frc.team8.robot.HAL.grabber;

import org.usfirst.frc.team8.commands.AutoAlignment;
import org.usfirst.frc.team8.commands.DriveDifferential;
import org.usfirst.frc.team8.commands.DriveStraight;
import org.usfirst.frc.team8.commands.SetState;
import org.usfirst.frc.team8.commands.ShooterRetract;
import org.usfirst.frc.team8.commands.Wait;
import org.usfirst.frc.team8.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwentyPoint extends CommandGroup {
	public TwentyPoint() {
		//addSequential(new DriveStraight(Double.MAX_VALUE, 2, 0.5));
		//addSequential(new DriveStraight(Double.MAX_VALUE, 1, 0.35));
		//addSequential(new DriveDifferential(5, 0.5, 0.2));
		addSequential(new AutoAlignment());
		//shooter up
		addSequential(new ShooterRetract());
		addSequential(new Wait(0.75));
		addSequential(new SetState<Grabber.State>(grabber, Grabber.State.RAISED, Grabber.State.LOWERED, 8));
		//grabber up
	}
}
