package org.usfirst.frc.team8.autonomous.sequences;

import org.usfirst.frc.team8.autonomous.Drive;
import org.usfirst.frc.team8.autonomous.DriveDifferential;
import org.usfirst.frc.team8.commands.GrabberToggle;
import org.usfirst.frc.team8.commands.IntakeCommand;
import org.usfirst.frc.team8.commands.SetShooterPower;
import org.usfirst.frc.team8.commands.ShooterExtend;
import org.usfirst.frc.team8.commands.ShooterUnlock;
import org.usfirst.frc.team8.commands.SuccessiveAutoAlignment;
import org.usfirst.frc.team8.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author bertd
 *
 */
public class TwentyPoint extends CommandGroup {
	
	public TwentyPoint() {
			addSequential(new SetShooterPower(false));
			addSequential(new Wait(500));
			addSequential(new SetShooterPower(0));
			addSequential(new Drive(true, 2, 0.5f));
			addSequential(new Drive(true, 1, 0.35f));
			addSequential(new DriveDifferential(true, 5, Integer.MAX_VALUE, 0.5f, 0.2f));
			addSequential(new SuccessiveAutoAlignment(0.33f));
			addSequential(new SetShooterPower(true));
			addSequential(new Wait(100));
			addSequential(new IntakeCommand(false, 0.5));
			addSequential(new ShooterExtend()); 
			addSequential(new Wait(750));
			addSequential(new GrabberToggle(true));
			addSequential(new Wait(750f));
			addSequential(new ShooterUnlock()); 
			addSequential(new Wait(2000f));
			addSequential(new SetShooterPower(0));
			addSequential(new GrabberToggle(false));
	}
}