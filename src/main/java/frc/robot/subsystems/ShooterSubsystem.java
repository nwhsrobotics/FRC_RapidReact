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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private boolean m_flywheelStatus = false;
  private static final double RAMP_RATE_SEC = 1.0;
  private CANSparkMax m_flywheelMotor = new CANSparkMax(frc.robot.Constants.IDs.CAN.SHOOTER_FLYWHEEL, MotorType.kBrushless);
  private SparkMaxPIDController m_flywheelpidController;
  private RelativeEncoder m_flywheelencoder;
  private CANSparkMax m_flywheel2Motor = new CANSparkMax(frc.robot.Constants.IDs.CAN.SHOOTER_FLYWHEEL2, MotorType.kBrushless);
  private SparkMaxPIDController m_flywheel2pidController;
  private RelativeEncoder m_flywheel2encoder;
  private double m_speed_rpm = 0.0;
  private double m_offset_rpm = 0.0;
  private boolean m_autoMode = false;
  private double m_manual_speed_rpm = 0.0;
  private double m_manual_speed_rpm_high = 3500.0;
  private double m_manual_speed_rpm_low = 1500.0;
  private boolean m_is_editing_high = false;
  private VisionSubsystem m_visionSubsystem;
  
  private final double m_flywheelAutoSpeedMultiplier = 5;
  private double m_greenDist = -1;
  private final double AUTO_SPEED_CONSTANT = 30;

  private boolean m_enabled = false;
  private double m_manual_offset_rpm = 0.0;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem(VisionSubsystem visionSubsystem) {
    if ((m_flywheelMotor == null) || (m_flywheel2Motor == null)) {
      return;
    }

    m_enabled = true;

    m_visionSubsystem = visionSubsystem;

    m_flywheelpidController = m_flywheelMotor.getPIDController();
    m_flywheelencoder = m_flywheelMotor.getEncoder();

    m_flywheelencoder.setPosition(0);
    m_flywheelMotor.setIdleMode(IdleMode.kBrake);

    m_flywheelpidController.setP(0.0002);
    m_flywheelpidController.setI(0.0);
    m_flywheelpidController.setD(0);
    m_flywheelpidController.setIZone(0);
    m_flywheelpidController.setFF(0.000178);
    m_flywheelpidController.setOutputRange(-1.0, 1.0);
    m_flywheelMotor.setClosedLoopRampRate(RAMP_RATE_SEC);

    m_flywheel2pidController = m_flywheel2Motor.getPIDController();
    m_flywheel2encoder = m_flywheel2Motor.getEncoder();

    m_flywheel2encoder.setPosition(0);
    m_flywheel2Motor.setIdleMode(IdleMode.kBrake);

    m_flywheel2pidController.setP(0.0002);
    m_flywheel2pidController.setI(0.0);
    m_flywheel2pidController.setD(0);
    m_flywheel2pidController.setIZone(0);
    m_flywheel2pidController.setFF(0.000178);
    m_flywheel2pidController.setOutputRange(-1.0, 1.0);
    m_flywheel2Motor.setClosedLoopRampRate(RAMP_RATE_SEC);

    m_flywheelpidController.setReference(0.0, ControlType.kVelocity);
    m_flywheel2pidController.setReference(0.0, ControlType.kVelocity);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      return;
    }
    //m_speed_rpm = SmartDashboard.getNumber("flywheelSpeed (rpm)", 0.0);

    if (m_autoMode == true) {
      m_speed_rpm = getAutoFlywheelSpeed();
      m_offset_rpm = IndexSubsystem.Constants.SHOOT_SPIN_RPM;
    } else {
      m_speed_rpm = m_manual_speed_rpm;
      m_offset_rpm = m_manual_offset_rpm;
    }
    SmartDashboard.putBoolean("Flywheel Auto:", m_autoMode);

    SmartDashboard.putNumber("Flywheel Desired Speed:", m_speed_rpm);
    SmartDashboard.putNumber("Flywheel Actual Speed:", ((m_flywheel2encoder.getVelocity()+m_flywheelencoder.getVelocity())/2));
    SmartDashboard.putNumber("Motor Velocity", m_speed_rpm);
    
    //Set moters for m_speed_rpm
    
    m_flywheelpidController.setReference(-m_speed_rpm+m_offset_rpm, ControlType.kVelocity);
    m_flywheel2pidController.setReference(m_speed_rpm+m_offset_rpm, ControlType.kVelocity);
  
  }

  public boolean getFlywheelStatus(){
    return m_flywheelStatus;
  } 

  public void setFlywheel_rpm(double speed_rpm, double offset_rpm) {
    if (speed_rpm == 0.0){
      m_flywheelStatus = false;
    } else {
      m_flywheelStatus = true;
    }
    m_manual_speed_rpm = speed_rpm;
    SmartDashboard.putNumber("flywheelSpeed (rpm)", speed_rpm);
    m_manual_offset_rpm = offset_rpm;
    SmartDashboard.putNumber("flywheeloffset (rpm)", offset_rpm);
    //TODO: Implement the setReference method here to move the motor to the desired speed
  }

  public double getAutoFlywheelSpeed() {
    //TODO: Need to calibrate this flywheel speed to use vision (auto flywheel speed) 
    double greenCenterY = m_visionSubsystem.getGreenCenterY();
    if (greenCenterY <= 0) {
      return 0;
    }
    double autoSpeed =  Math.pow(greenCenterY, -1) * m_flywheelAutoSpeedMultiplier * m_visionSubsystem.getGreenDist_in();

    if (autoSpeed > 3000){
      return 3000;
    } else {
      return autoSpeed;
    }
    
  }

  public void setAutoMode(boolean autoMode) {
    m_autoMode = autoMode;
  }

  public double getSpeed() {
    return m_speed_rpm;
  }

  public double getHighSpeed() {
    return m_manual_speed_rpm_high;
  }

  public void setHighSpeed(double new_speed) {
    m_manual_speed_rpm_high = new_speed;
  }

  public double getLowSpeed() {
    return m_manual_speed_rpm_low;
  }

  public void setLowSpeed(double new_speed) {
    m_manual_speed_rpm_low = new_speed;
  }

  public boolean getIsEditingHigh() {
    return m_is_editing_high;
  }

  public void setIsHighEditing(boolean new_state) {
    m_is_editing_high = new_state;
  }
  
  /*
  public boolean getFlywheelControl() {
    String[] flywheelModes = {"Flywheel Switcher", "Smart Dashboard Value"};
    SmartDashboard.putStringArray("Flywheel Mode:", flywheelModes);
    
  }*/

}
