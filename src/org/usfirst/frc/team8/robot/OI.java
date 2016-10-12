package org.usfirst.frc.team8.robot;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

import org.usfirst.frc.team8.commands.SetState;
import org.usfirst.frc.team8.commands.ShiftTo;
import org.usfirst.frc.team8.commands.ShooterExtend;
import org.usfirst.frc.team8.commands.ShooterLock;
import org.usfirst.frc.team8.commands.ShooterRetract;
import org.usfirst.frc.team8.commands.ShooterUnlock;
import org.usfirst.frc.team8.lib.ExpelButton;
import org.usfirst.frc.team8.lib.IntakeButton;
import org.usfirst.frc.team8.subsystems.Drivetrain.Gear;
import org.usfirst.frc.team8.subsystems.Grabber;
import org.usfirst.frc.team8.subsystems.Intake;
import static org.usfirst.frc.team8.robot.HAL.intake;
import static org.usfirst.frc.team8.robot.HAL.grabber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public static DoubleUnaryOperator deadband = (x) -> {
		return Math.abs(x) > Constants.inputDeadband ? x : 0;
	};
	
	public static Joystick driveStick = new Joystick(0);
	public static Button autoAlignment = new JoystickButton(driveStick, 2);
	public static Button shiftUp = new JoystickButton(driveStick, 4);
	public static Button shiftDown = new JoystickButton(driveStick, 6);
	public static Button toggleAutoShift = new JoystickButton(driveStick, 7);
	public static Button quickTurn = new JoystickButton(driveStick, 1);
	public static DoubleSupplier driveStickValue = () -> deadband.applyAsDouble(driveStick.getY());
	
	public static Joystick turnStick = new Joystick(1);
	public static DoubleSupplier turnStickValue = () -> deadband.applyAsDouble(turnStick.getX());
	
	public static Joystick operatorStick = new Joystick(4);
	
	public static double shooterAxis = -deadband.applyAsDouble(operatorStick.getRawAxis(2)); //xbox left y axis
	public static double breacherAxis = deadband.applyAsDouble(operatorStick.getRawAxis(5));
	
	public static Button xboxA = new JoystickButton(operatorStick, 1);
	public static Button xboxB = new JoystickButton(operatorStick, 2);
	public static Button xboxX = new JoystickButton(operatorStick, 3);
	public static Button xboxY = new JoystickButton(operatorStick, 4);
	public static Button xboxLB = new JoystickButton(operatorStick, 5);
	public static Button xboxRB = new JoystickButton(operatorStick, 6);
	public static IntakeButton xboxLT = new IntakeButton(operatorStick);
	public static ExpelButton xboxRT = new ExpelButton(operatorStick);
	
	public OI() {
		shiftUp.whenPressed(new ShiftTo(Gear.LOW));
		shiftDown.whenPressed(new ShiftTo(Gear.HIGH));
		
		xboxLT.whileHeld(new SetState<Intake.State>(intake, Intake.State.INTAKE, Intake.State.STOPPED));
		xboxRT.whileHeld(new SetState<Intake.State>(intake, Intake.State.EXPEL, Intake.State.STOPPED));
		
		xboxA.whenPressed(new ShooterLock());
		xboxB.whenPressed(new ShooterRetract());
		xboxX.whenPressed(new ShooterExtend());
		xboxY.whenPressed(new ShooterUnlock());
		
		xboxLB.whileHeld(new SetState<Grabber.State>(grabber, Grabber.State.RAISED));
		xboxLB.whenReleased(new SetState<Grabber.State>(grabber, Grabber.State.LOWERED));
	}
	
}

