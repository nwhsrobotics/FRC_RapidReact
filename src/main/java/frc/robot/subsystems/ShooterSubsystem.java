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

public class ShooterSubsystem extends SubsystemBase {
  private boolean m_flywheelStatus = false;
  private static final int CAN_ID_FLYWHEEL = 15;
  private static final int CAN_ID_FLYWHEEL2 = 16;
  private CANSparkMax m_flywheelMotor = new CANSparkMax(CAN_ID_FLYWHEEL, MotorType.kBrushless);
  private SparkMaxPIDController m_flywheelpidController;
  private RelativeEncoder m_flywheelencoder;
  private CANSparkMax m_flywheel2Motor = new CANSparkMax(CAN_ID_FLYWHEEL2, MotorType.kBrushless);
  private SparkMaxPIDController m_flywheel2pidController;
  private RelativeEncoder m_flywheel2encoder;
  private double m_speed_rpm = 0.0;


  private static boolean m_enabled = false;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    if ((m_flywheelMotor != null) && (m_flywheel2Motor != null)) {
      m_enabled = true;
    }
    m_flywheelpidController = m_flywheelMotor.getPIDController();
    m_flywheelencoder = m_flywheelMotor.getEncoder();

    m_flywheelencoder.setPosition(0);
    m_flywheelMotor.setIdleMode(IdleMode.kCoast);

    m_flywheelpidController.setP(0.0002);
    m_flywheelpidController.setI(0);
    m_flywheelpidController.setD(0);
    m_flywheelpidController.setIZone(0);
    m_flywheelpidController.setFF(0.000178);
    m_flywheelpidController.setOutputRange(-1.0, 1.0);

    m_flywheel2pidController = m_flywheel2Motor.getPIDController();
    m_flywheel2encoder = m_flywheel2Motor.getEncoder();

    m_flywheel2encoder.setPosition(0);
    m_flywheel2Motor.setIdleMode(IdleMode.kCoast);

    m_flywheel2pidController.setP(0.0002);
    m_flywheel2pidController.setI(0);
    m_flywheel2pidController.setD(0);
    m_flywheel2pidController.setIZone(0);
    m_flywheel2pidController.setFF(0.000178);
    m_flywheel2pidController.setOutputRange(-1.0, 1.0);

    m_flywheelpidController.setReference(0.0, ControlType.kVelocity);
    m_flywheel2pidController.setReference(0.0, ControlType.kVelocity);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      return;
    }

    //Set moters for m_speed_rpm
    m_flywheelpidController.setReference(m_speed_rpm, ControlType.kVelocity);
    m_flywheel2pidController.setReference(-m_speed_rpm, ControlType.kVelocity);
  }

  public boolean getFlywheelStatus(){
    return m_flywheelStatus;

  } 

  public void setFlywheel_rpm(double speed_rpm) {
    if (speed_rpm == 0.0){
      m_flywheelStatus = false;
    } else {
      m_flywheelStatus = true;
    }
    m_speed_rpm = speed_rpm;

    //TODO: Implement the setReference method here to move the motor to the desired speed
  }

}
