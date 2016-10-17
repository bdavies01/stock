package org.usfirst.frc.team8.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team8.lib.CheezyDriveHelper;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author bertd
 *
 */
public class TeleopDrive extends Command {

	private final CheezyDriveHelper cdh;
	private final DoubleSupplier driveStick;
	public final DoubleSupplier turnStick;
	public final BooleanSupplier toggleAutoShift;
	public final BooleanSupplier isLowGear;
	public final BooleanSupplier isHighGear;
	public final BooleanSupplier isQuickTurn;
	private boolean autoShift;
	private boolean dirty;
	private Gear gear;
	
	public TeleopDrive(DoubleSupplier driveStick, DoubleSupplier turnStick, BooleanSupplier toggleAutoShift, BooleanSupplier isLowGear, BooleanSupplier isHighGear, BooleanSupplier isQuickTurn) {
		super("Teleop Drive");
		requires(drivetrain);
		this.cdh = new CheezyDriveHelper();
		this.driveStick = driveStick;
		this.turnStick = turnStick;
		this.toggleAutoShift = toggleAutoShift;
		this.isLowGear = isLowGear;
		this.isHighGear = isHighGear;
		this.isQuickTurn = isQuickTurn;
		this.autoShift = false;
		this.gear = Gear.LOW;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(this.toggleAutoShift.getAsBoolean() && !dirty) {
			autoShift = !autoShift;
			dirty = true;
		} else if(!this.toggleAutoShift.getAsBoolean()) {
			dirty = false;
		}
		
		if(this.autoShift) {
			if(Math.abs(drivetrain.getSpeed()) > 3.0 && Math.abs(driveStick.getAsDouble()) < 0.2) {
				gear = Gear.HIGH;
			} else if(Math.abs(drivetrain.getSpeed()) < 2.0 && Math.abs(driveStick.getAsDouble()) < 0.2) {
				gear = Gear.LOW;
			}
		} else {
			if(isLowGear.getAsBoolean()) {
				gear = Gear.LOW;
			}
			if(isHighGear.getAsBoolean()) {
				gear = Gear.HIGH;
			}
		}
		drivetrain.shiftTo(gear);
		cdh.cheezyDrive(driveStick.getAsDouble(), turnStick.getAsDouble(), isQuickTurn.getAsBoolean());
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.tank(0, 0);
	}

	@Override
	protected void interrupted() {
		drivetrain.tank(0, 0);
	}

}
