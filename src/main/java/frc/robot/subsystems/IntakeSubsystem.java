// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.Constants.IDs;

import com.revrobotics.CANSparkMax;
import com.revrobotics.jni.CANSWDLJNI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private static final double BEATER_ON_SPEED = 0.2;
  private static final double BEATER_OFF_SPEED = 0.0;
  private boolean m_beaterOn = false;
  private CANSparkMax m_beaterMotor = new CANSparkMax (IDs.CAN.INTAKE_BEATER);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_beaterOn){
      m_beaterMotor.set(BEATER_ON_SPEED);
    }
    else {
      m_beaterMotor.set(BEATER_OFF_SPEED);
    }
  }

  public void setBeaterOn(boolean on) {
    m_beaterOn = on;
  }     
}
