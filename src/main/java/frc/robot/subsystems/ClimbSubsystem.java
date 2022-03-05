// Copyright () FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
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

  private RelativeEncoder m_rightarmEncoder = m_rightarmMotor.getEncoder();
  private RelativeEncoder m_leftarmEncoder = m_leftarmMotor.getEncoder();
  private RelativeEncoder m_rightshoulderEncoder = m_rightshoulderMotor.getEncoder();
  private RelativeEncoder m_leftshoulderEncoder = m_leftshoulderMotor.getEncoder();


  private final SparkMaxPIDController m_pidController_ArmMotor1 = m_rightarmMotor.getPIDController();
  private final SparkMaxPIDController m_pidController_ArmMotor2 = m_leftarmMotor.getPIDController();
  private final SparkMaxPIDController m_pidController_ArmMotor3 = m_rightshoulderMotor.getPIDController();
  private final SparkMaxPIDController m_pidController_ArmMotor4 = m_leftshoulderMotor.getPIDController();

  
  private static boolean m_enabled = false;
  private double m_upDownPosition = 0.0;
  private double m_backForwardPosition = 0.0;
  private static final double SPEED_UP_DOWN = 1.0;
  private static final double SPEED_BACK_FORWARD = 1.0;
// TO DO LIST: FIX REAL SPEED(THE 1.0 VALUES!)
  
  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {
    if ((m_rightarmMotor != null) && (m_leftarmMotor != null) && (m_rightshoulderMotor != null) && (m_leftshoulderMotor != null)){
      m_enabled = true;
    }

    m_encoder.setPosition(0);
    m_rightarmMotor.setIdleMode(IdleMode.kBrake);

    private RelativeEncoder m_enabled = false;
    m_rightarmMotor.setP(0.1);
    m_encoder = m_rightarmMotor1.getEncoder();
    m_pidController.setI(0);

  }

  @Override
  public void periodic() {
    if (!m_enabled){
      return;
    }
    // This method will be called once per scheduler run
  }

  public void moveUp() {
<<<<<<< Updated upstream
    m_upDownPosition += SPEED_UP_DOWN;
  }

  public void moveForward() {
  }

  public void moveDown() {
=======
    m_upDown_m += SPEED_UP_DOWN_mps/TICKS_PER_SECOND;
    if (m_upDown_m> MAX_UP_DOWN_m){
      m_upDown_m = MAX_UP_DOWN_m;
    }
  }

  public void moveForward() {
    m_backForward_m += SPEED_BACK_FORWARD_mps/TICKS_PER_SECOND;
    if (m_backForward_m> MAX_BACK_FORWARD_m){
      m_backFroward_m = MAX_BACK_FORWARD
    }

  }

  public void moveDown() {
    m_upDown_m -= SPEED_UP_DOWN_mps/TICKS_PER_SECOND;
    if (m_upDown_m< 0.0){
      m_upDown_m = 0.0;
    }
>>>>>>> Stashed changes
  }

  public void moveBack() {
  }
  

  D_pad
   // if Joystick button= pressed then move x=0.75 in position XboxController ;

}
