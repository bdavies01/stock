package org.usfirst.frc.team8.autonomous.sequences;

import org.usfirst.frc.team8.autonomous.Drive;
import org.usfirst.frc.team8.commands.ShiftTo;
import org.usfirst.frc.team8.commands.Wait;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author bertd
 *
 */
public class CrossLowBar extends CommandGroup {
	public CrossLowBar() {
		addSequential(new Wait(1000));
		addSequential(new ShiftTo(Gear.LOW));
		addSequential(new Wait(1000));
		addSequential(new Drive(true, 5, 100, 0.5));
	}
}
