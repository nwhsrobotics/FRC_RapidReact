// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.List;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*public class DriveSubsystem extends SubsystemBase {
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

  // Creates a new DriveSubsystem. 
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
   
  }
  public void setDrivePower(double power, double turn){
    if (m_leftFront != null || m_leftBack != null || m_rightFront != null || m_rightBack != null){
     
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

  public void setVel(double fwd, double turn) {
     m_fwdVel = fwd;
     m_turnVel = turn;

  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}*/

public class DriveSubsystem extends SubsystemBase {
  // Creates a new DriveSubsystem. 

  private CANSparkMax m_leftFront = new CANSparkMax(Constants.kLeftMotor1Port, MotorType.kBrushless);
  private CANSparkMax m_leftBack = new CANSparkMax(Constants.kLeftMotor2Port, MotorType.kBrushless);
  private CANSparkMax m_rightFront = new CANSparkMax(Constants.kRightMotor1Port, MotorType.kBrushless);
  private CANSparkMax m_rightBack = new CANSparkMax(Constants.kRightMotor2Port, MotorType.kBrushless);
  private RelativeEncoder m_rightFrontEncoder;
  private RelativeEncoder m_rightBackEncoder;
  private RelativeEncoder m_leftFrontEncoder;
  private RelativeEncoder m_leftBackEncoder;

  private MotorControllerGroup m_left;
  private MotorControllerGroup m_right;
  private DifferentialDrive m_robotDrive;

  private static boolean m_isTeleop = false;

  private static final double POWER_FACTOR = 0.8;
  private static final double TURN_FACTOR = 0.65;
  private static final double VISION_AUTO_THRESHOLD = 0.01;

// The gyro sensor
private final AHRS m_gyro = new AHRS(SerialPort.Port.kUSB);

// Odometry class for tracking robot pose
private final DifferentialDriveOdometry m_odometry;
private VisionSubsystem m_visionSubsystem;
private double m_power;
private double m_turn;
private int runtime = 0;
  public DriveSubsystem(VisionSubsystem visionSubsystem) {
    m_visionSubsystem = visionSubsystem;
    m_leftFront.setIdleMode(IdleMode.kBrake);
    m_rightFront.setIdleMode(IdleMode.kBrake);
    m_leftBack.setIdleMode(IdleMode.kBrake);
    m_rightBack.setIdleMode(IdleMode.kBrake);
    m_leftFront.setInverted(false);
    m_leftBack.setInverted(false);
    m_rightFront.setInverted(true);
    m_rightBack.setInverted(true);
    SmartDashboard.putBoolean("gyro connection", m_gyro.isConnected());
    m_left  = new MotorControllerGroup(m_leftFront, m_leftBack);
    m_right = new MotorControllerGroup(m_rightFront, m_rightBack);
    m_robotDrive = new DifferentialDrive(m_left, m_right);
    m_robotDrive.setDeadband(0.05);
    m_robotDrive.setSafetyEnabled(false);
    m_rightFrontEncoder = m_rightFront.getEncoder();
    m_rightBackEncoder = m_rightBack.getEncoder();
    m_leftFrontEncoder = m_leftFront.getEncoder();
    m_leftBackEncoder = m_leftBack.getEncoder();
    m_leftBackEncoder.setPosition(0);
    m_leftFrontEncoder.setPosition(0);
    m_rightBackEncoder.setPosition(0);
    m_rightFrontEncoder.setPosition(0);

    /*m_rightFrontEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
    m_rightFrontEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
    m_rightBackEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
    m_rightBackEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
    m_leftFrontEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
    m_leftFrontEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
    m_leftBackEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
    m_leftBackEncoder.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);*/
  
  
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
   // m_left.setInverted(true);

    // Sets the distance per pulse for the encoders

    resetEncoders();
    Rotation2d r = m_gyro.getRotation2d();
    m_odometry = new DifferentialDriveOdometry(r);
    System.out.printf("Inital heading equals %f", r.getRadians());

  }

  @Override
  public void periodic() {
    //System.out.println("left side velocity " + getLeftEncoderSpeed() + " right side velocity " + getRightEncoderSpeed());
    SmartDashboard.putNumber("right velocity", getRightEncoderSpeed());
    SmartDashboard.putNumber("left velocity", getLeftEncoderSpeed());
     // Update the odometry in the periodic block
     m_odometry.update(
      m_gyro.getRotation2d(), getLeftEncoderDistance(), getRightEncoderDistance());
      
    SmartDashboard.putNumber("right encoder distance", getRightEncoderDistance());
    SmartDashboard.putNumber("left encoder distance", getLeftEncoderDistance());
    
    SmartDashboard.putNumber("Odometry X: ", m_odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("Odometry Y: ", m_odometry.getPoseMeters().getY());
    SmartDashboard.putNumber("Odometry Degrees: ", m_odometry.getPoseMeters().getRotation().getDegrees());

    SmartDashboard.putBoolean("Teleop", m_isTeleop);

    if(m_isTeleop){
      m_robotDrive.arcadeDrive(m_power * POWER_FACTOR, m_turn * TURN_FACTOR, true);
    }
  }  
  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public String toString(){
    return("This is the drive susbsystem");
  }
  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderSpeed(), getRightEncoderSpeed());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   * THIS IS USED TO CONTROL THE DRIVE GLOBALLY FROM THE COMMANDS
   */
  public void arcadeDrive(double fwd, double rot) {
    m_robotDrive.arcadeDrive(fwd * POWER_FACTOR, rot * TURN_FACTOR);
  }

  

  public void setDrivePower(double power, double turn){
    if (m_leftFront != null || m_leftBack != null || m_rightFront != null || m_rightBack != null){
     
      m_power = power;
      m_turn = turn;
    }
  }
   /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    //m_leftMotors.setVoltage(leftVolts);
    m_left.setVoltage(leftVolts);
    m_right.setVoltage(rightVolts); 
    m_robotDrive.feed();
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    m_leftBackEncoder.setPosition(0);
    m_leftFrontEncoder.setPosition(0);
    m_rightBackEncoder.setPosition(0);
    m_rightFrontEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
  }
  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_robotDrive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
  public double getRightEncoderDistance(){
    return (m_rightFrontEncoder.getPosition())*Constants.kEncoderDistancePerPulse;
    
  }
  public double getLeftEncoderDistance(){
    return (m_leftFrontEncoder.getPosition())*Constants.kEncoderDistancePerPulse; 
    
  }
  public double getLeftEncoderSpeed(){
    return ((m_leftFrontEncoder.getVelocity())*Constants.kEncoderDistancePerPulse)/60;
  }
  
  public double getRightEncoderSpeed(){
    return ((m_rightFrontEncoder.getVelocity())*Constants.kEncoderDistancePerPulse)/60;
  }

  public static void isTeleop(boolean b) {
    m_isTeleop = b;
  }

  public void centerDrive() {
    runtime += 1;
    m_isTeleop = true;
    double ball_center_x = m_visionSubsystem.getBallCenterX();
    if (ball_center_x >= 0){
      if (ball_center_x >= (0.5 + VISION_AUTO_THRESHOLD)){
        m_robotDrive.arcadeDrive(0.0, -0.3);
        SmartDashboard.putBoolean("Align", false);
      } else if (ball_center_x <= (0.5 - VISION_AUTO_THRESHOLD)){
        m_robotDrive.arcadeDrive(0.0, 0.3);
        SmartDashboard.putBoolean("Align", false);
      } else {
        SmartDashboard.putBoolean("Align", true);
      }
    } else {
      
      m_robotDrive.arcadeDrive(0.0, 0.3);
    }
    SmartDashboard.putNumber("Runtime", runtime);
   
  }

}

