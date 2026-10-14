// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Motor speed: -1.0 to +1.0
  private static final double kMotorSpeed = 0.75;

  // Xbox controller on USB port 0
  private final XboxController m_driverController = new XboxController(0);

  // SPARK MAX on CAN ID 1
  // kBrushless is correct for a NEO / other brushless motor
  private final SparkMax m_motor = new SparkMax(1, MotorType.kBrushless);

  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();

    // Protect against a null selection
    if (m_autoSelected == null) {
      m_autoSelected = kDefaultAuto;
    }

    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;

      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    m_motor.set(0.0);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (m_driverController.getAButton()) {
      // Hold A = forward
      m_motor.set(kMotorSpeed);

    } else if (m_driverController.getBButton()) {
      // Hold B = reverse
      m_motor.set(-kMotorSpeed);

    } else {
      // Neither button held = stop
      m_motor.set(0.0);
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    m_motor.set(0.0);
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    m_motor.set(0.0);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}