// Copyright () FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  private static final int CAN_ID_RIGHTARM = 20;
  private static final int CAN_ID_LEFTARM = 21;
  private static final int CAN_ID_RIGHTSHOULDER = 22;
  private static final int CAN_ID_LEFTSHOULDER = 23;
  private CANSparkMax m_rightarmMotor = new CANSparkMax(CAN_ID_RIGHTARM, MotorType.kBrushless);
  private CANSparkMax m_leftarmMotor = new CANSparkMax(CAN_ID_LEFTARM, MotorType.kBrushless);
  private CANSparkMax m_rightshoulderMotor = new CANSparkMax(CAN_ID_RIGHTSHOULDER, MotorType.kBrushless);
  private CANSparkMax m_leftshoulderMotor = new CANSparkMax(CAN_ID_LEFTSHOULDER, MotorType.kBrushless);


  private RelativeEncoder m_rightarmEncoder = null;
  private RelativeEncoder m_leftarmEncoder = null;
  private RelativeEncoder m_rightshoulderEncoder = null;
  private RelativeEncoder m_leftshoulderEncoder = null;


  private SparkMaxPIDController m_rightarmPID = null;
  private SparkMaxPIDController m_leftarmPID = null;
  private SparkMaxPIDController m_rightshoulderPID = null;
  private SparkMaxPIDController m_leftshoulderPID = null;

  
  private static boolean m_enabled = false;
  private double m_upDownPosition = 0.0;
  private double m_backForwardPosition = 0.0;
  private static final double SPEED_UP_DOWN = 1.0;
  private static final double SPEED_BACK_FORWARD = 1.0;
// TO DO LIST: FIX REAL SPEED(THE 1.0 VALUES!)
  
  /** Creates a new ClimbSubsystem. 
   * @param IdleMode 
   * @param ControlType */
  public ClimbSubsystem() {
    if ((m_rightarmMotor != null) && (m_leftarmMotor != null) && (m_rightshoulderMotor != null) && (m_leftshoulderMotor != null)){
      m_enabled = true;
    }
    if(!m_enabled){
      return;
    }
    m_rightarmEncoder = m_rightarmMotor.getEncoder();
    m_leftarmEncoder = m_leftarmMotor.getEncoder();
    m_rightshoulderEncoder = m_rightshoulderMotor.getEncoder();
    m_leftshoulderEncoder = m_leftshoulderMotor.getEncoder();
  
  
    m_rightarmPID = m_rightarmMotor.getPIDController();
    m_leftarmPID = m_leftarmMotor.getPIDController();
    m_rightshoulderPID = m_rightshoulderMotor.getPIDController();
    m_leftshoulderPID = m_leftshoulderMotor.getPIDController();

    m_rightarmEncoder.setPosition(0);
    m_rightarmMotor.setIdleMode(IdleMode.kBrake);
    m_rightarmPID.setP(0.01);
    m_rightarmPID.setI(0.0);
    m_rightarmPID.setD(0.0);
    m_rightarmPID.setIZone(0.0);
    m_rightarmPID.setFF(0.0);
    m_rightarmPID.setOutputRange(-0.5, 0.5); //TODO - enable full power
    m_rightarmPID.setReference(0.0, ControlType.kPosition);

    m_leftarmEncoder.setPosition(0);
    m_leftarmMotor.setIdleMode(IdleMode.kBrake);
    m_leftarmPID.setP(0.01);
    m_leftarmPID.setI(0.0);
    m_leftarmPID.setD(0.0);
    m_leftarmPID.setIZone(0.0);
    m_leftarmPID.setFF(0.0);
    m_leftarmPID.setOutputRange(-0.5, 0.5); //TODO - enable full power
    m_leftarmPID.setReference(0.0, ControlType.kPosition);

    m_rightshoulderEncoder.setPosition(0);
    m_rightshoulderMotor.setIdleMode(IdleMode.kBrake);
    m_rightshoulderPID.setP(0.01);
    m_rightshoulderPID.setI(0.0);
    m_rightshoulderPID.setD(0.0);
    m_rightshoulderPID.setIZone(0.0);
    m_rightshoulderPID.setFF(0.0);
    m_rightshoulderPID.setOutputRange(-0.5, 0.5); //TODO - enable full power
    m_rightshoulderPID.setReference(0.0, ControlType.kPosition);

    m_leftshoulderEncoder.setPosition(0);
    m_leftshoulderMotor.setIdleMode(IdleMode.kBrake);
    m_leftshoulderPID.setP(0.01);
    m_leftshoulderPID.setI(0.0);
    m_leftshoulderPID.setD(0.0);
    m_leftshoulderPID.setIZone(0.0);
    m_leftshoulderPID.setFF(0.0);
    m_leftshoulderPID.setOutputRange(-0.5, 0.5); //TODO - enable full power
    m_leftshoulderPID.setReference(0.0, ControlType.kPosition);
  }

  @Override
  public void periodic() {
    if (!m_enabled){
      return;
    }
    // This method will be called once per scheduler run
  }

  public void moveUp() {
    m_upDownPosition += SPEED_UP_DOWN;
  }

  public void moveForward() {
  }

  public void moveDown() {
  }

  public void moveBack() {
  }
  



}
