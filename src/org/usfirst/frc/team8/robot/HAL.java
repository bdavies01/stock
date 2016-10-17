package org.usfirst.frc.team8.robot;

import org.usfirst.frc.team8.subsystems.Breacher;
import org.usfirst.frc.team8.subsystems.Drivetrain;
import org.usfirst.frc.team8.subsystems.Grabber;
import org.usfirst.frc.team8.subsystems.Intake;
import org.usfirst.frc.team8.subsystems.Shooter;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * 
 * @author bertd
 *
 */
public class HAL {
	public static CANTalon leftFront  = new CANTalon(RobotMap.leftFrontMotor);
	public static CANTalon leftBack = new CANTalon(RobotMap.leftBackMotor);
	public static CANTalon rightFront = new CANTalon(RobotMap.rightFrontMotor);
	public static CANTalon rightBack = new CANTalon(RobotMap.rightBackMotor);
	
	public static CANTalon shooterArm = new CANTalon(RobotMap.shooterArmMotor);
	public static CANTalon breacherArm = new CANTalon(RobotMap.breacherArmMotor);
	
	public static VictorSP leftIntake = new VictorSP(RobotMap.leftIntakeMotor);
	public static VictorSP rightIntake = new VictorSP(RobotMap.rightIntakeMotor);
	
	public static DoubleSolenoid grabberSolenoid = new DoubleSolenoid(RobotMap.grabberSolenoidA, RobotMap.grabberSolenoidB);
	public static DoubleSolenoid driveShifter = new DoubleSolenoid(RobotMap.driveShifterA, RobotMap.driveShifterB);
	public static DoubleSolenoid shooterLoad = new DoubleSolenoid(RobotMap.shooterLoadA, RobotMap.shooterLoadB);
	public static DoubleSolenoid shooterLock = new DoubleSolenoid(RobotMap.shooterLockA, RobotMap.shooterLockB);
	
	public static Encoder leftDriveEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);
	public static Encoder rightDriveEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
	public static ADXRS450_Gyro spartanBoard = new ADXRS450_Gyro();
	
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Breacher breacher = new Breacher();
	public static final Grabber grabber = new Grabber();
	public static final Intake intake = new Intake();
	public static final Shooter shooter = new Shooter();
	
	public static final DriverStation ds = DriverStation.getInstance();
	public static final NetworkTable robotTable = NetworkTable.getTable("RobotTable");
	public static final NetworkTable visionTable = NetworkTable.getTable("visiondata");
	
}
