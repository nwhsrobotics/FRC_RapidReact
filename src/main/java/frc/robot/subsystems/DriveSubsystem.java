// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private static final int CAN_ID_ - 1;
  private CANSparkMax m_rightFront = new CANSparkMax(CAN_ID 2, MotorType.kBrushless);
  private CANSparkMax m_leftFront = new CANSparkMax(CAN_ID_3, MotorType.kBrushless);
  private CANSparkMax m_rightBack = new CANSparkMax(CAN_ID_4, MotorType.kBrushless);
  private CANSparkMax m_leftBack = new CANSparkMax(CAN_ID_5, MotorType.kBrushless);
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


