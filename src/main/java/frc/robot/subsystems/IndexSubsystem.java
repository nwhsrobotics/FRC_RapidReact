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

public class IndexSubsystem extends SubsystemBase {
  public final class Constants {
    // indexer ball speed in meters per second
    public static final double SPEED_FORWARD_TEST_MPS = 4.0;
    public static final double SPEED_FORWARD_NORMAL_MPS = 1.0;
    public static final double SPEED_BACKWARD_NORMAL_MPS = -SPEED_FORWARD_NORMAL_MPS; // TODO verify speed should be negative
    // unload direction is opposite load and shoot
    public final static double LOAD_1_M = 0.2;
    public final static double SHOOT_ARMED_M = 0.5;
    public final static double SHOOT_EMPTY_M = SHOOT_ARMED_M + LOAD_1_M;
    public final static double UNLOAD_EMPTY_M = -0.75;
    public final static double UNLOAD_LOAD_1_M = -LOAD_1_M;

    /*
    public final static double LOAD_2_M = 0.1;
    public final static double ARMED_1_M = -LOAD_2_M;

    public final static double UNLOAD_LOAD_2_M = -LOAD_2_M;
    public final static double UNLOAD_ARMED_1_M = -1.2;
    
    
    public final static double SHOOT_LOADED_2_M = 0.75;
    public final static double SHOOT_ARMED_1_M = 0.75;
    */
  }

  public enum IndexerState {
    EMPTY,
    ARMED;
  }

  private static final double GEAR_RATIO = 7.0;
  private static final double WHEEL_RADIUS_M = 2.0 * 0.0254; // 2 inches.
  private static final double METERS_PER_REV = Math.PI * WHEEL_RADIUS_M / GEAR_RATIO;

  private IndexerState m_state = IndexerState.EMPTY;

  // added by Joey - 1/22/22
  private CANSparkMax m_indexMotor = new CANSparkMax(frc.robot.Constants.IDs.CAN.INDEXER, MotorType.kBrushless);
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;

  private boolean m_enabled = false;

  private double m_pos_m = 0.0;

  /** Creates a new IndexSubsystem. */
  public IndexSubsystem() {
    if (m_indexMotor != null) {
      m_enabled = true;
    }

    System.out.println("Index configuring motor");
    m_pidController = m_indexMotor.getPIDController();
    m_encoder = m_indexMotor.getEncoder();

    m_encoder.setPosition(0);
    m_indexMotor.setIdleMode(IdleMode.kBrake);

    m_pidController.setP(0.1);
    m_pidController.setI(0);
    m_pidController.setD(0);
    m_pidController.setIZone(0);
    m_pidController.setFF(0);
    m_pidController.setOutputRange(-1.0, 1.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!m_enabled) {
      System.out.println("index disabled");
      return;
    }

    double revs = m_pos_m / METERS_PER_REV;
    //System.out.printf("Index revs = %f (m_pos_m = %f)\n", revs, m_pos_m);
    m_pidController.setReference(revs, ControlType.kPosition);
  }

  public void runToPosition(int position) {
    m_pidController.setReference(position, ControlType.kPosition);
  }

  public double getPosition_m() {
    return m_pos_m;
  }

  public void setPosition_m(double nextPosition_m) {
    m_pos_m = nextPosition_m;
  }

  public IndexerState getState() {
    return m_state;
  }

  public void setState(IndexerState state) {
    m_state = state;

    // Display number of balls loaded for operator using SmartDashboard
    int state_as_int = m_state.ordinal();
    switch (state_as_int) {
      case 0:   // Empty
        SmartDashboard.putString("Index State", "Empty");
        break;
      case 1:   // Loaded 1
        SmartDashboard.putString("Index State", "Armed");
        break;
      default:  
        SmartDashboard.putString("Index State", "Unknown");
        break;
    }
  }
}
