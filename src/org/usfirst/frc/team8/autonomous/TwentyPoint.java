package org.usfirst.frc.team8.autonomous;

import static org.usfirst.frc.team8.robot.HAL.grabber;
import static org.usfirst.frc.team8.robot.HAL.intake;

import org.usfirst.frc.team8.autonomous.DriveDifferential;
import org.usfirst.frc.team8.commands.SetShooterPower;
import org.usfirst.frc.team8.commands.SetState;
import org.usfirst.frc.team8.commands.ShooterExtend;
import org.usfirst.frc.team8.commands.ShooterUnlock;
import org.usfirst.frc.team8.commands.SuccessiveAutoAlignment;
import org.usfirst.frc.team8.commands.Wait;
import org.usfirst.frc.team8.subsystems.Grabber;
import org.usfirst.frc.team8.subsystems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwentyPoint extends CommandGroup {
	
	public TwentyPoint() {
			addSequential(new SetShooterPower(false));
			addSequential(new Wait(0.5f));
			addSequential(new SetShooterPower(0));
			addSequential(new Drive(true, 2, 0.5f));
			addSequential(new Drive(true, 1, 0.35f));
			addSequential(new DriveDifferential(true, 5, Integer.MAX_VALUE, 0.5f, 0.2f));
			addSequential(new SuccessiveAutoAlignment(0.33f));
			addSequential(new SetShooterPower(true));
			addSequential(new Wait(0.1f));
			addSequential(new SetState<Intake.State>(intake, Intake.State.EXPEL));
			addSequential(new Wait(0.5f));
			addSequential(new SetState<Intake.State>(intake, Intake.State.STOPPED));
			addSequential(new ShooterExtend()); // Right now this an extension
			addSequential(new Wait(0.75f));
			addSequential(new SetState<Grabber.State>(grabber, Grabber.State.RAISED));
			addSequential(new Wait(0.75f));
			addSequential(new ShooterUnlock()); // Right now this is an unlock command
			addSequential(new Wait(2f));
			addSequential(new SetShooterPower(0));
			addSequential(new SetState<Grabber.State>(grabber, Grabber.State.LOWERED));
	}
	
}