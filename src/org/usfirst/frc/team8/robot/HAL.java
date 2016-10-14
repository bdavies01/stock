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

public class HAL {
	public static CANTalon leftFront  = new CANTalon(1);
	public static CANTalon leftBack = new CANTalon(3);
	public static CANTalon rightFront = new CANTalon(2);
	public static CANTalon rightBack = new CANTalon(4);
	
	public static CANTalon shooterArm = new CANTalon(8);
	public static CANTalon breacherArm = new CANTalon(5);
	
	public static VictorSP leftIntake = new VictorSP(0);
	public static VictorSP rightIntake = new VictorSP(1);
	
	public static DoubleSolenoid grabberSolenoid = new DoubleSolenoid(3, 4);
	public static DoubleSolenoid driveShifter = new DoubleSolenoid(0, 7);
	public static DoubleSolenoid shooterLoad = new DoubleSolenoid(5, 2);
	public static DoubleSolenoid shooterLock = new DoubleSolenoid(6, 1);
	
	public static Encoder leftDriveEncoder = new Encoder(2, 3);
	public static Encoder rightDriveEncoder = new Encoder(0, 1);
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
