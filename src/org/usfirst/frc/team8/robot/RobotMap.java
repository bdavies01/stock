package org.usfirst.frc.team8.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
/**
 * 
 * @author bertd
 *
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	public static int leftFrontMotor = 1;
	public static int rightFrontMotor = 2;
	public static int leftBackMotor = 3;
	public static int rightBackMotor = 4;
	
	public static int shooterArmMotor = 8;
	public static int breacherArmMotor = 5;
	
	public static int leftIntakeMotor = 0;
	public static int rightIntakeMotor = 1;
	
	public static int grabberSolenoidA = 3;
	public static int grabberSolenoidB = 4;
	
	public static int driveShifterA = 0;
	public static int driveShifterB = 7;
	
	public static int shooterLoadA = 5;
	public static int shooterLoadB = 2;
	
	public static int shooterLockA = 6;
	public static int shooterLockB = 1;
	
	public static int leftEncoderA = 2;
	public static int leftEncoderB = 3;
	
	public static int rightEncoderA = 0;
	public static int rightEncoderB = 1;
}
