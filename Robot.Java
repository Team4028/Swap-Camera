package org.usfirst.frc.team4028.robot;

import org.usfirst.frc.team4028.robot.LogitechF310Map;
import java.time.Duration;
import java.time.Instant;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Joystick _operaterGamepad;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	String _cameraSwitch;
	MakeItWork _cameraServer;
	boolean _isButtonAPressedLastScan = false;
	/* *
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		_cameraServer = new MakeItWork("cam0");
		_operaterGamepad = new Joystick (0);

	}



	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
		{
		}
		

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		boolean isButtonAPressedThisScan = false;
		
		if(_operaterGamepad.getRawButton(LogitechF310Map.A_BUTTON))
		{
			isButtonAPressedThisScan = true;
		}
		boolean isButtonAJustPressed = (isButtonAPressedThisScan && !_isButtonAPressedLastScan);
		
		if(isButtonAJustPressed && _cameraServer.GetString() == "cam2") 
		{
			_cameraServer.SwapCamera("cam0");
		}
		else if(isButtonAJustPressed && _cameraServer.GetString() == "cam0")
		{
			_cameraServer.SwapCamera("cam1");
		}
		else if(isButtonAJustPressed && _cameraServer.GetString() == "cam1")
		{
			_cameraServer.SwapCamera("cam2");
		}
		_isButtonAPressedLastScan = isButtonAPressedThisScan;
	}
				

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
