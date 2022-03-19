// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
//import frc.robot.Constants.IDs;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {
  
  //intake arm motor
  private CANSparkMax m_intakeArmMotor = new CANSparkMax(Constants.IDs.CAN.INTAKE_MOTOR_ARM, MotorType.kBrushless);
  private SparkMaxPIDController m_intakeArmPidController;
  private RelativeEncoder m_intakeArmEncoder;

  public static final double DOWN_POSITION_DEG = 0.0;
  public static final double UP_POSITION_DEG = 115.0;
  boolean m_isUp = true;
  private double m_currentPosition_deg = UP_POSITION_DEG;
  public static final double COUNTS_PER_DEG = 100.0/360.0;// 100:1 gear ratio, 360 deg/rev
  

  // beater motor
  private static final double BEATER_ON_SPEED = 0.5; // was 0.5
  private static final double BEATER_OFF_SPEED = 0.0;
  private boolean m_beaterOn = false;
  private CANSparkMax m_beaterMotor = new CANSparkMax(Constants.IDs.CAN.INTAKE_BEATER, MotorType.kBrushless);
  private boolean m_forward;
  private DigitalInput m_intakeSwitchInput = new DigitalInput(0);


  private static boolean m_enabled = false;
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    if (m_intakeArmMotor != null && m_beaterMotor != null){
      m_enabled = true;
    }

    //using PID for IntakeArm Motor
    m_intakeArmPidController = m_intakeArmMotor.getPIDController();
    m_intakeArmEncoder = m_intakeArmMotor.getEncoder();

    m_intakeArmEncoder.setPosition(0);
    m_intakeArmMotor.setIdleMode(IdleMode.kBrake);

    m_intakeArmPidController.setP(0.05);
    m_intakeArmPidController.setI(0);
    m_intakeArmPidController.setD(0);
    m_intakeArmPidController.setIZone(0);
    m_intakeArmPidController.setFF(0);
    m_intakeArmPidController.setOutputRange(-0.5, 0.5);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run\
    if (m_currentPosition_deg <= DOWN_POSITION_DEG){
      //power off when down
      m_intakeArmPidController.setReference(0.0, ControlType.kVoltage);
      //System.out.printf("Intake Voltage: %f \n", 0.0);
      SmartDashboard.putNumber("Encoder Reference", -1.0);
    } else {
      //regular pid control when not down
      m_intakeArmPidController.setReference(toEncoder(m_currentPosition_deg), ControlType.kPosition);//todo: resume here
      SmartDashboard.putNumber("Encoder Reference", toEncoder(m_currentPosition_deg));
      //System.out.printf("Intake arm : %f, %d \n",m_currentPosition_deg, toEncoder(m_currentPosition_deg));
    }
    SmartDashboard.putNumber("Intake Position", m_intakeArmEncoder.getPosition());

    if (m_beaterOn){
      if (m_forward){
        m_beaterMotor.set(-BEATER_ON_SPEED);
      } else {
        m_beaterMotor.set(BEATER_ON_SPEED);
      }
    }
    else {
      m_beaterMotor.set(BEATER_OFF_SPEED);
    }

    SmartDashboard.putBoolean("Intake Switch", m_intakeSwitchInput.get());
 
  }


  private int toEncoder(double position_deg) {
    double value = -(position_deg - UP_POSITION_DEG)*COUNTS_PER_DEG;
    return (int)value;
  }

  
  public boolean isUp() {
    return m_isUp;
  }


  public void setIsUp(boolean isUp){
    m_isUp = isUp;
  }
  

  public double getPosition_deg() {
    return m_currentPosition_deg;
  }

public void setPosition_deg(double position) {
  m_currentPosition_deg = position;
  SmartDashboard.putNumber("currentPosition: ", position);
}

    
  

  public void setBeaterOn(boolean on) {
    m_beaterOn = on;
  }

  public void setBeaterForward(boolean forward) {
    m_forward = forward;
  }     

  public double getIntakeCurrent(){
    return m_intakeArmMotor.getOutputCurrent();
  }
  

  public void resetArmEncoder_UP_POS() {
    //TODO: Uncomment this when we are ready
    this.setPosition_deg(UP_POSITION_DEG);
    m_intakeArmEncoder.setPosition(0);
  }

  public boolean getIntakeSwitch(){
    //returns true when the circuit is open
    //returns false when the intake is pressed up against the intake
    return m_intakeSwitchInput.get();
  }
}
