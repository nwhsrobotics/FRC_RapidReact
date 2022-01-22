// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private static final int CAN_ID_FLYWHEEL = 15;
  private static final int CAN_ID_HOOD = 16;
  private CANSparkMax m_flywheelMotor = new CANSparkMax(CAN_ID_FLYWHEEL, MotorType.kBrushless);
  private CANSparkMax m_hoodMotor = new CANSparkMax(CAN_ID_HOOD, MotorType.kBrushless);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
