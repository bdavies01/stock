package org.usfirst.frc.team8.robot;

import org.usfirst.frc.team8.subsystems.Breacher;
import org.usfirst.frc.team8.subsystems.Drivetrain;
import org.usfirst.frc.team8.subsystems.Grabber;
import org.usfirst.frc.team8.subsystems.Intake;
import org.usfirst.frc.team8.subsystems.Shooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
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
	
	public static DoubleSolenoid grabberSolenoid = new DoubleSolenoid(4, 3);
	public static DoubleSolenoid driveShifter = new DoubleSolenoid(0, 7);
	public static DoubleSolenoid shooterLoad = new DoubleSolenoid(2, 5);
	public static DoubleSolenoid shooterLock = new DoubleSolenoid(1, 6);
	
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Breacher breacher = new Breacher();
	public static final Grabber grabber = new Grabber();
	public static final Intake intake = new Intake();
	public static final Shooter shooter = new Shooter();
	
	public static final DriverStation ds = DriverStation.getInstance();
	public static final NetworkTable table = NetworkTable.getTable("RobotTable");
	
}
