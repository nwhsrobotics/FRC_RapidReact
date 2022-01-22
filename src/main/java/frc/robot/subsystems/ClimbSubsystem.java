// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase{
private static final int CAN_ID_RIGHTARM= 20;
private static final int CAN_ID_LEFTARM= 21;
private static final int CAN_ID_RIGHTSHOULDER= 22;
private static final int CAN_ID_LEFTSHOULDER= 23;
  private CANSparkMax m_rightarmMotor= new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax m_leftarmMotor= new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax m_rightshoulderMotor= new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax m_leftshoulderMotor= new CANSparkMax(1, MotorType.kBrushless);
  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
