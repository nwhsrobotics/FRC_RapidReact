// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
  
  //added by Joey - 1/22/22
  private static final int CAN_ID_INDEX = 10;
  private CANSparkMax m_indexMotor = new CANSparkMax(CAN_ID_INDEX, MotorType.kBrushless);
  
  private static boolean m_enabled = false;

  /** Creates a new IndexSubsystem. */
  public IndexSubsystem() {
    if (m_indexMotor != null){
      m_enabled = true;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      return;
    }
    // regular code goes here
  }
}
