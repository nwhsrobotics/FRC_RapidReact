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

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  private CANSparkMax m_rightarmMotor = new CANSparkMax(Constants.IDs.CAN.CLIMB_RIGHT_ARM, MotorType.kBrushless);
  private CANSparkMax m_leftarmMotor = new CANSparkMax(Constants.IDs.CAN.CLIMB_LEFT_ARM, MotorType.kBrushless);
  private CANSparkMax m_rightshoulderMotor = new CANSparkMax(Constants.IDs.CAN.CLIMB_RIGHT_SHOULDER, MotorType.kBrushless);
  private CANSparkMax m_leftshoulderMotor = new CANSparkMax(Constants.IDs.CAN.CLIMB_LEFT_SHOULDER, MotorType.kBrushless);


  private RelativeEncoder m_rightarmEncoder = null;
  private RelativeEncoder m_leftarmEncoder = null;
  private RelativeEncoder m_rightshoulderEncoder = null;
  private RelativeEncoder m_leftshoulderEncoder = null;


  private SparkMaxPIDController m_rightarmPID = null;
  private SparkMaxPIDController m_leftarmPID = null;
  private SparkMaxPIDController m_rightshoulderPID = null;
  private SparkMaxPIDController m_leftshoulderPID = null;

  
  private static boolean m_enabled = false;
  
  private static final double MAX_UP_DOWN_m = 21.5 * 0.0254; //21 inches converted to meters
  private static final double MIN_UP_DOWN_m = 0.0 * 0.0254; 
  private static final double INITIAL_UP_DOWN = 0.0;
  
  

  
  private static final double MAX_BACK_FORWARD_m = 7.0 * 0.0254; //7 inches converted to meters
  private static final double MIN_BACK_FORWARD = 0.0;
  private static final double INITIAL_BACK_FORWARD = 0.0;

  public static final double SPEED_UP_DOWN_mps = 0.22;
  public static final double TICKS_PER_SECOND = 50;
  private static final double SPEED_BACK_FORWARD_mps = 3.5 * 0.0254; //3.5 inches per second
  private static final double GEAR_RATIO_UP_DOWN = -5.0;
  private static final double LEAD_DISTANCE_m = (0.5 * 0.0254); // (inches * m/in) half an inch to meters
  private static final double GEAR_RATIO_BACK_FORWARD = 5.0;



// TO DO LIST: FIX REAL SPEED(THE 1.0 VALUES!)
  private static final double UP_DOWN_COUNTS_PER_METER = (GEAR_RATIO_UP_DOWN/LEAD_DISTANCE_m); //TO DO LIST: FIGURE OUT REAL VALUE
  private static final double BACK_FORWARD_COUNTS_PER_METER = (GEAR_RATIO_BACK_FORWARD/LEAD_DISTANCE_m);
  private static final double METERS_TO_INCHES = 39.37;
  private static final double HEIGHT_1_m = 3.0 * 0.0254; //3 inches to meters : less than 3 inches sets to 0
  private static final double HEIGHT_2_m = 10.0 * 0.0254;
  
  
  private double m_upDown_m = INITIAL_UP_DOWN;
  private double m_backForward_m = INITIAL_BACK_FORWARD;

  /** Creates a new ClimbSubsystem. 
   * @param IdleMode 
   * @param ControlType */
  public ClimbSubsystem() {
    if ((m_rightarmMotor != null) && (m_leftarmMotor != null) && (m_rightshoulderMotor != null) && (m_leftshoulderMotor != null)) {
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
    m_rightarmPID.setP(1.0);
    m_rightarmPID.setI(0.0);
    m_rightarmPID.setD(0.0);
    m_rightarmPID.setIZone(0.0);
    m_rightarmPID.setFF(0.0);
    m_rightarmPID.setOutputRange(-1.0, 1.0); //TODO - enable full power
    m_rightarmPID.setReference(0.0, ControlType.kPosition);

    
    m_leftarmEncoder.setPosition(0);
    m_leftarmMotor.setIdleMode(IdleMode.kBrake);
    m_leftarmPID.setP(1.0);
    m_leftarmPID.setI(0.0);
    m_leftarmPID.setD(0.0);
    m_leftarmPID.setIZone(0.0);
    m_leftarmPID.setFF(0.0);
    m_leftarmPID.setOutputRange(-1.0, 1.0); 
    m_leftarmPID.setReference(0.0, ControlType.kPosition); 
    
    m_rightshoulderEncoder.setPosition(0);
    m_rightshoulderMotor.setIdleMode(IdleMode.kBrake);
    m_rightshoulderPID.setP(0.5);
    m_rightshoulderPID.setI(0.0);
    m_rightshoulderPID.setD(0.0);
    m_rightshoulderPID.setIZone(0.0);
    m_rightshoulderPID.setFF(0.0);
    m_rightshoulderPID.setOutputRange(-0.5, 0.5); //TODO - enable full power
    m_rightshoulderPID.setReference(0.0, ControlType.kPosition);

    m_leftshoulderEncoder.setPosition(0);
    m_leftshoulderMotor.setIdleMode(IdleMode.kBrake);
    m_leftshoulderPID.setP(0.5);
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
    // Covert the meters to the count
    double upDown_counts = m_upDown_m*UP_DOWN_COUNTS_PER_METER;
    // System.out.printf("upDown_counts = %f\n", upDown_counts);
    // This method will be called once per scheduler run
    double backForward_counts = m_backForward_m*BACK_FORWARD_COUNTS_PER_METER;
    m_leftarmPID.setReference(upDown_counts, ControlType.kPosition);
    m_rightarmPID.setReference(upDown_counts, ControlType.kPosition);
    m_leftshoulderPID.setReference(-backForward_counts, ControlType.kPosition);
    m_rightshoulderPID.setReference(-backForward_counts, ControlType.kPosition);
    SmartDashboard.putNumber("Climb Arm UP DOWN Pos", (m_upDown_m*METERS_TO_INCHES));
  }

  private void enforceLimits() {
    if(m_upDown_m < HEIGHT_1_m) {
      m_backForward_m = 0;
    }
    else if(m_upDown_m < HEIGHT_2_m) {
      double limit = MAX_BACK_FORWARD_m * (m_upDown_m - HEIGHT_1_m) / (HEIGHT_2_m - HEIGHT_1_m);
      if(m_backForward_m > limit) {
        m_backForward_m = limit;
      }
    }
  }

  public void moveUp() {
    m_upDown_m += SPEED_UP_DOWN_mps/TICKS_PER_SECOND;
    if (m_upDown_m > MAX_UP_DOWN_m) {
      m_upDown_m = MAX_UP_DOWN_m;
    }
    enforceLimits();
  }

  public void moveForward() {
    m_backForward_m += SPEED_BACK_FORWARD_mps/TICKS_PER_SECOND;
    if (m_backForward_m > MAX_BACK_FORWARD_m) {
      m_backForward_m = MAX_BACK_FORWARD_m; 
    }
    enforceLimits();
  }

  public void moveDown() {
    m_upDown_m -= SPEED_UP_DOWN_mps/TICKS_PER_SECOND;
    if (m_upDown_m < MIN_UP_DOWN_m) {
      m_upDown_m = MIN_UP_DOWN_m;
    }
    enforceLimits();
  }

  public void moveBack() {
    m_backForward_m -= SPEED_BACK_FORWARD_mps/TICKS_PER_SECOND;
    if (m_backForward_m < MIN_BACK_FORWARD) {
      m_backForward_m = MIN_BACK_FORWARD;
    }
    enforceLimits();
  }

public double getHeight_m() {
    return m_upDown_m;
}

public void setHeight_m(double currentHeight_m) {
  m_upDown_m = currentHeight_m;
  enforceLimits();
}
  



}
