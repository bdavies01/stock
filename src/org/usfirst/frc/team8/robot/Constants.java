package org.usfirst.frc.team8.robot;

public class Constants {
	public static double driveWheelDiameter = 6.0;
	public static double driveWheelCircumference = driveWheelDiameter * Math.PI;
	public static double inputDeadband = 0.1;
	public static double driveTurnSensitivityHigh = 0.75;
	public static double driveTurnSensitivityLow = 0.6;
	
	public static double shooterPositionP = 0.1;
	public static double shooterPositionI = 0.01;
	public static double shooterPositionD = 0.001;
	public static double shooterReverseLimit = -1.0;
	public static double shooterForwardLimit = 1.0;
	
	public static double pixelsPerDistance = 42.394;
	public static double acceptablePixelError = 5.0;
}
