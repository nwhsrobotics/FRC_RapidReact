// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {

  public enum IndexerState{
    EMPTY,
    LOADED_1,
    LOADED_2,
    ARMED_1;
  }

  private IndexerState m_state = IndexerState.EMPTY;
  
  //added by Joey - 1/22/22
  private static final int CAN_ID_INDEX = 3;
  private CANSparkMax m_indexMotor = new CANSparkMax(CAN_ID_INDEX, MotorType.kBrushless);
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;
  
  private static boolean m_enabled = false;

  /** Creates a new IndexSubsystem. */
  public IndexSubsystem() {
    if (m_indexMotor != null){
      m_enabled = true;
    }

    m_pidController = m_indexMotor.getPIDController();
    m_encoder = m_indexMotor.getEncoder();

    m_encoder.setPosition(0);
    m_indexMotor.setIdleMode(IdleMode.kBrake);

    m_pidController.setP(0.1);
    m_pidController.setI(0);
    m_pidController.setD(0);
    m_pidController.setIZone(0);
    m_pidController.setFF(0);
    m_pidController.setOutputRange(-0.2, 0.2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      return;
    }
    // regular code goes here
  }

  public void runToPosition(int position){
    m_pidController.setReference(position, ControlType.kPosition);
  }

  public double getPosition_deg() {
    return 0.0;
  }

  public void setPosition_deg(double nextPosition_deg) {}

  public IndexerState getState(){
    return m_state;
  }

  public void setState(IndexerState state){
    m_state = state;
  }
}
