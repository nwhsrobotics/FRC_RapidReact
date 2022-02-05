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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RunSparkSubsystem extends SubsystemBase {
  /** Creates a new RunSparkSubsystem. */
  private CANSparkMax m_motor1 = null;
  private SparkMaxPIDController m_pidController; 
  private RelativeEncoder m_encoder;
  private double m_velocity = 0;
  private boolean runTime = false;
  private static final double INDEX_RAMPRATE = 1.5;

  public RunSparkSubsystem() {
    m_motor1 = new CANSparkMax(3, MotorType.kBrushless);
    m_pidController = m_motor1.getPIDController();
    m_encoder = m_motor1.getEncoder();
    //m_encoder.setPosition(0);
    
    m_motor1.setIdleMode(IdleMode.kBrake);
    
    m_pidController.setP(0.0002);
    m_pidController.setI(0.0000001);
    m_pidController.setD(0);
    m_pidController.setIZone(0);
    m_pidController.setFF(0.00005);
    m_pidController.setOutputRange(-0.2, 0.2);
    m_pidController.setReference(0.0, ControlType.kVelocity);
    
    m_motor1.setClosedLoopRampRate(INDEX_RAMPRATE);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (runTime){
      //m_motor1.set(m_velocity);
      m_pidController.setReference(m_velocity, ControlType.kVelocity);
      SmartDashboard.putNumber("RPM of the Motor: ", (m_encoder.getVelocity()/7));
      SmartDashboard.putNumber("Voltage Applied: ", m_motor1.getBusVoltage());
      //m_pidController.setReference(m_velocity, ControlType.kVelocity);
    }
  }

  public void setVelocity(double velocity){
    runTime = true;
    m_velocity = velocity;
    
  }

  
  
}
