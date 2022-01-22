// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private static final int CAN_ID_LEFT_FRONT = 2;
  private static final int CAN_ID_LEFT_BACK = 3;
  private static final int CAN_ID_RIGHT_FRONT = 4;
  private static final int CAN_ID_RIGHT_BACK = 5;
  private CANSparkMax m_leftFront = new CANSparkMax(CAN_ID_LEFT_FRONT, MotorType.kBrushless);
  private CANSparkMax m_leftBack = new CANSparkMax(CAN_ID_LEFT_BACK, MotorType.kBrushless);
  private CANSparkMax m_rightFront = new CANSparkMax(CAN_ID_RIGHT_FRONT, MotorType.kBrushless);
  private CANSparkMax m_rightBack = new CANSparkMax(CAN_ID_RIGHT_BACK, MotorType.kBrushless);
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

