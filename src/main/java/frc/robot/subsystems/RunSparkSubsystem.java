// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RunSparkSubsystem extends SubsystemBase {
  /** Creates a new RunSparkSubsystem. */
  private CANSparkMax m_motor1 = null;
  private SparkMaxPIDController m_pidController; 
  private RelativeEncoder m_encoder;
  private double m_position;

  public RunSparkSubsystem() {
    m_motor1 = new CANSparkMax(3, MotorType.kBrushless);
    m_pidController = m_motor1.getPIDController();
    m_encoder = m_motor1.getEncoder();
    m_encoder.setPosition(0);
    m_motor1.setIdleMode(IdleMode.kBrake);

    m_pidController.setP(0.01);
    m_pidController.setI(0);
    m_pidController.setD(0);
    m_pidController.setIZone(0);
    m_pidController.setFF(0);
    m_pidController.setOutputRange(-0.2, 0.2);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setVelocity(double velocity){

    m_pidController.setReference(velocity, ControlType.kVelocity);
  }

  
  
}
