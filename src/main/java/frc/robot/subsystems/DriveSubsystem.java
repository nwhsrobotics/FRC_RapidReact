// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  //2,3,4,5,

  private CANSparkMax m_leftFront = new CANSparkMax(Constants.IDs.CAN.DRIVE_LEFT_FRONT, MotorType.kBrushless);
  private CANSparkMax m_leftBack = new CANSparkMax(Constants.IDs.CAN.DRIVE_LEFT_BACK, MotorType.kBrushless);
  private CANSparkMax m_rightFront = new CANSparkMax(Constants.IDs.CAN.DRIVE_RIGHT_FRONT, MotorType.kBrushless);
  private CANSparkMax m_rightBack = new CANSparkMax(Constants.IDs.CAN.DRIVE_RIGHT_BACK, MotorType.kBrushless);

  private static final double POWER_FACTOR = 0.8;
  private static final double TURN_FACTOR = 0.65;
  public boolean driveExist;

  private static boolean m_enabled = false;

  private MotorControllerGroup m_left;
  private MotorControllerGroup m_right;
  private DifferentialDrive m_drive;

  private boolean motorRun = false;
  private double m_turn = 0.0;
  private double m_power = 0.0;
 // private boolean reverseDrive = false;
  private double m_fwdVel;
  private double m_turnVel;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    if ((m_leftFront != null) && (m_leftBack != null) && (m_rightFront != null) && (m_rightBack != null)){
      m_enabled = true;
      m_leftFront.setIdleMode(IdleMode.kBrake);
      m_leftBack.setIdleMode(IdleMode.kBrake);
      m_rightFront.setIdleMode(IdleMode.kBrake);
      m_rightBack.setIdleMode(IdleMode.kBrake);
      m_leftFront.setInverted(true);
      m_leftBack.setInverted(true);
      m_left  = new MotorControllerGroup(m_leftFront, m_leftBack);
      m_right = new MotorControllerGroup(m_rightFront, m_rightBack);
      m_drive = new DifferentialDrive(m_left, m_right);
      m_drive.setDeadband(0.15);
      m_drive.setSafetyEnabled(false);
    }
  }

  public void teleopInit(){
    m_drive.setSafetyEnabled(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_drive.arcadeDrive(m_power * POWER_FACTOR, m_turn * TURN_FACTOR, true);
    if (!m_enabled){
      return;
    }
   /*  if(motorRun == true){
      m_power += 0.01;
      if(m_power >= 0.5){
        m_power = 0.5;
      }
    }
    else{
      m_power -= 0.01;
      if(m_power <= 0.0){
        m_power = 0.0;
      }
   }
   m_leftFront.set(m_power);
   m_leftBack.set(m_power);
   m_rightFront.set(m_power);
   m_rightBack.set(m_power);*/
  }
  public void setDrivePower(double power, double turn){
    if (m_leftFront != null || m_leftBack != null || m_rightFront != null || m_rightBack != null){
      /*if(reverseDrive){
        m_power = -power;
        m_turn = turn;
      }
    
      else{
        m_power = power;
        m_turn = turn;
      }
      */
      m_power = power;
      m_turn = -turn;
    }
  }
  public boolean getCurrentState(){
    return motorRun;
  }

  public void drive(boolean currentState){
    motorRun = currentState;
  }

  public void setPower(double d){
    m_power = d;
  }

  /*public boolean Reverse(){
    reverseDrive = !reverseDrive;
    return reverseDrive;
  }
  */

  public void setVel(double fwd, double turn) {
     m_fwdVel = fwd;
     m_turnVel = turn;

  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}


