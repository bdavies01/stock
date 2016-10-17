package org.usfirst.frc.team8.lib;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author bertd
 *
 */
public class IntakeButton extends Trigger {
	private GenericHID stick;

	public IntakeButton(GenericHID stick) {
		this.stick = stick;
	}

	@Override
	public boolean get() {
		return Math.abs(stick.getRawAxis(2)) > 0.9;
	}

	public void whenPressed(final Command command) {
		whenActive(command);
	}

	/**
	 * Constantly starts the given command while the button is held.
	 *
	 * {@link Command#start()} will be called repeatedly while the button is
	 * held, and will be canceled when the button is released.
	 *
	 * @param command
	 *            the command to start
	 */
	public void whileHeld(final Command command) {
		whileActive(command);
	}

	/**
	 * Starts the command when the button is released $
	 * 
	 * @param command
	 *            the command to start
	 */
	public void whenReleased(final Command command) {
		whenInactive(command);
	}

	/**
	 * Toggles the command whenever the button is pressed (on then off then on)
	 * $
	 * 
	 * @param command
	 *            the command to start
	 */
	public void toggleWhenPressed(final Command command) {
		toggleWhenActive(command);
	}

	/**
	 * Cancel the command when the button is pressed $
	 * 
	 * @param command
	 *            the command to start
	 */
	public void cancelWhenPressed(final Command command) {
		cancelWhenActive(command);
	}
}
