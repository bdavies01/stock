package org.usfirst.frc.team8.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team8.robot.HAL.robotTable;
import static org.usfirst.frc.team8.robot.HAL.ds;

/**
 * 
 * @author bertd
 *
 */
public class PeriodicUpdater extends Command {
	private boolean checkingAlliance;
	
	@Override
	protected void initialize() {
		checkingAlliance = true;
		robotTable.putNumber("match-time", 90);
		robotTable.putString("game-period", "DISABLED");
		robotTable.putBoolean("brownout-status", false);
		robotTable.putNumber("battery", 12.0);
		robotTable.putString("alliance", "invalid");
		robotTable.putString("accumulatorstate", "Disabled");
		robotTable.putString("drivetrainstate", "Disabled");
		robotTable.putString("shooterstate", "Disabled");
		robotTable.putString("breacherstate", "Disabled");
		robotTable.putString("grabberstate", "Disabled");
	}

	@Override
	protected void execute() {
		if (checkingAlliance) {
			switch (ds.getAlliance()) {
			case Blue:
				robotTable.putString("alliance", "blue");
				checkingAlliance = false;
				break;
			case Red:
				robotTable.putString("alliance", "red");
				checkingAlliance = false;
				break;
			case Invalid:
				robotTable.putString("alliance", "invalid");
			}
		}
		
		robotTable.putNumber("match-time", ds.getMatchTime());
		robotTable.putBoolean("brownout-status", ds.isBrownedOut());
		robotTable.putNumber("battery", ds.getBatteryVoltage());
		
		robotTable.putString("pvaluehandshake", Double.toString(robotTable.getNumber("pvalue", -1)));
		robotTable.putString("ivaluehandshake", Double.toString(robotTable.getNumber("ivalue", -1)));
		robotTable.putString("dvaluehandshake", Double.toString(robotTable.getNumber("dvalue", -1)));
		robotTable.putString("autopathhandshake", robotTable.getString("autopath", "None"));
		
		if (ds.isAutonomous()) {
			robotTable.putString("game-period", "Autonomous");
		} else if (ds.isDisabled()) {
			robotTable.putString("game-period", "Disabled");
		} else if (ds.isOperatorControl()) {
			robotTable.putString("game-period", "TeleOperated");
		} else if (ds.isTest()) {
			robotTable.putString("game-period", "Test");
		} else {
			robotTable.putString("game-period", "Unidentified");
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
