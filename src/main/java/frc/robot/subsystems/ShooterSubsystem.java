// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private boolean m_flywheelStatus = false;
  private static final int CAN_ID_FLYWHEEL = 15;
  private static final int CAN_ID_HOOD = 16;
  private CANSparkMax m_flywheelMotor = new CANSparkMax(CAN_ID_FLYWHEEL, MotorType.kBrushless);
  private CANSparkMax m_hoodMotor = new CANSparkMax(CAN_ID_HOOD, MotorType.kBrushless);
  private SparkMaxPIDController m_flywheelpidController;
  private RelativeEncoder m_flywheelencoder;


  private static boolean m_enabled = false;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    if ((m_flywheelMotor != null) && (m_hoodMotor != null)) {
      m_enabled = true;
    }
    m_flywheelpidController = m_flywheelMotor.getPIDController();
    m_flywheelencoder = m_flywheelMotor.getEncoder();

    m_flywheelencoder.setPosition(0);
    m_flywheelMotor.setIdleMode(IdleMode.kBrake);

    m_flywheelpidController.setP(0.1);
    m_flywheelpidController.setI(0);
    m_flywheelpidController.setD(0);
    m_flywheelpidController.setIZone(0);
    m_flywheelpidController.setFF(0);
    m_flywheelpidController.setOutputRange(-0.2, 0.2);

  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      return;
    }
  }

  public boolean getFlywheelStatus(){
    return m_flywheelStatus;

  } 

  public void setFlywheel(double speed) {
    if (speed == 0.0){
      m_flywheelStatus = false;
    } else {
      m_flywheelStatus = true;
    }

    //TODO: Implement the setReference method here to move the motor to the desired speed
  }

}
