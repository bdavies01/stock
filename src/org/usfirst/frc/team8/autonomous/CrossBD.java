package org.usfirst.frc.team8.autonomous;

import org.usfirst.frc.team8.commands.ShiftTo;
import org.usfirst.frc.team8.commands.Wait;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author bertd
 *
 */
public class CrossBD extends CommandGroup {
	public CrossBD() {
		addSequential(new Wait(1));
		addSequential(new ShiftTo(Gear.LOW));
		addSequential(new Wait(1));
		addSequential(new Drive(false, 1.5, 1));
	}
}
