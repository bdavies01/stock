
package org.usfirst.frc.team8.robot;

import org.usfirst.frc.team8.autonomous.Drive;
import org.usfirst.frc.team8.autonomous.NullCommand;
import org.usfirst.frc.team8.autonomous.sequences.TwentyPoint;
import org.usfirst.frc.team8.commands.PeriodicUpdater;
import static org.usfirst.frc.team8.robot.HAL.drivetrain;
import static org.usfirst.frc.team8.robot.HAL.shooter;
import static org.usfirst.frc.team8.robot.HAL.breacher;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

/**
 * 
 * @author bertd
 *
 */
public class Robot extends IterativeRobot {
	public static OI oi;

    Command autonomousCommand;
    Command periodicUpdater = new PeriodicUpdater();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @SuppressWarnings("unused")
    @Override
    public void robotInit() {
    	HAL hal = new HAL();
    	Constants cosntants = new Constants();
		oi = new OI();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    @Override
    public void disabledInit(){
    	if(!periodicUpdater.isRunning()) {
    		periodicUpdater.start();
    	}
    }
	
    @Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (!periodicUpdater.isRunning()) {
        	periodicUpdater.start();
        }
		
		drivetrain.disableBrake();
		shooter.enableBrake();
		breacher.enableBrake();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    @Override
    public void autonomousInit() {
        String autoPath = HAL.robotTable.getString("autopath", "none");
        switch(autoPath) {
        case "none":
        	autonomousCommand = new NullCommand();
        case "20pt":
        	autonomousCommand = new TwentyPoint();
        case "b/d": 
        	autonomousCommand = new Drive(true, 5, 100, 0.5);
        case "low bar":
        	//submit low bar
        default:
        	autonomousCommand = new NullCommand();
        }
    	autonomousCommand = new TwentyPoint();
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        }
        
        if (!periodicUpdater.isRunning()) {
        	periodicUpdater.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
        	autonomousCommand.cancel();
        }
        
        if(!periodicUpdater.isRunning()) {
        	periodicUpdater.start();
        }
        drivetrain.enableBrake();
        shooter.disableBrake();
        breacher.disableBrake();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
