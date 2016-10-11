
package org.usfirst.frc.team8.robot;

import org.usfirst.frc.team8.commands.PeriodicUpdater;

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
		
		HAL.leftFront.enableBrakeMode(false);
		HAL.leftBack.enableBrakeMode(false);
		HAL.rightFront.enableBrakeMode(false);
		HAL.rightBack.enableBrakeMode(false);
		HAL.shooterArm.enableBrakeMode(true);
		HAL.breacherArm.enableBrakeMode(true);
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
        autonomousCommand = null;
        String autoPath = HAL.table.getString("autopath", "20pt");
        switch(autoPath) {
        case "20pt":
        	//submit 20 pt
        case "b/d": 
        	//submit bd
        case "low bar":
        	//submit low bar
        }
    	
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
