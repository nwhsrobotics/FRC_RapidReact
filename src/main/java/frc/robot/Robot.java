// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private Trajectory m_trajectory;
  private Trajectory m_trajectory_confident_PT1;
  private Trajectory m_trajectory_confident_PT2; 
  private Trajectory m_trajectory_realisticPathOutOfTarmac;
  private Trajectory m_trajectory_vision_PT1;
  private Trajectory m_trajectory_vision_PT2;
  private Trajectory m_trajectory_vision_PT3;
  private Trajectory m_trajectory_TS_PT1;
  private Trajectory m_trajectory_TS_PT2;
  private Trajectory m_trajectory_TS_PT3;
  private static boolean isTeleop = false;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //CameraServer.startAutomaticCapture(0);
    //CameraServer.startAutomaticCapture(1);


    String trajectory_confident_PT1_JSON = "paths/RR_R_PT1.wpilib.json";
    m_trajectory_confident_PT1 = new Trajectory();
    String trajectory_confident_PT2_JSON = "paths/RR_R_PT2.wpilib.json";
    m_trajectory_confident_PT2 = new Trajectory();
    String trajectory_vision_PT1_JSON = "paths/TenPointVisionPart1.wpilib.json";
    m_trajectory_vision_PT1 = new Trajectory();
    String trajectory_vision_PT2_JSON = "paths/TenPointVisionPart2.wpilib.json";
    m_trajectory_vision_PT2 = new Trajectory();
    String trajectory_vision_PT3_JSON = "paths/TenPointVisionPart3.wpilib.json";
    m_trajectory_vision_PT3 = new Trajectory();

    //Top Secret Paths
    String trajectory_top_secret_PT1_JSON = "paths/TopSecret_PT1.wpilib.json";
    m_trajectory_TS_PT1 = new Trajectory();
    String trajectory_top_secret_PT2_JSON = "paths/TopSecret_PT2.wpilib.json";
    m_trajectory_TS_PT2 = new Trajectory();
    String trajectory_top_secret_PT3_JSON = "paths/TopSecret_PT3.wpilib.json";
    m_trajectory_TS_PT3 = new Trajectory();


    String trajectory_realisticPathOutOfTarmac_JSON = "paths/RealisticV3.wpilib.json";
    m_trajectory_realisticPathOutOfTarmac = new Trajectory();


    
    try {

      Path trajectoryConfidentPT1 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_confident_PT1_JSON);
      Path trajectoryConfidentPT2 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_confident_PT2_JSON);
      Path trajectoryRealistic = Filesystem.getDeployDirectory().toPath().resolve(trajectory_realisticPathOutOfTarmac_JSON);
      Path trajectoryVisionPT1 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_vision_PT1_JSON);
      Path trajectoryVisionPT2 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_vision_PT2_JSON);
      Path trajectoryVisionPT3 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_vision_PT3_JSON);

      //top secret paths
      Path trajectoryTSPT1 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_top_secret_PT1_JSON);
      Path trajectoryTSPT2 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_top_secret_PT2_JSON);
      Path trajectoryTSPT3 = Filesystem.getDeployDirectory().toPath().resolve(trajectory_top_secret_PT3_JSON);
      

      // select trajectory 1 2 3 based on input - following into RobotContainer
      m_trajectory_confident_PT1 = TrajectoryUtil.fromPathweaverJson(trajectoryConfidentPT1);
      m_trajectory_confident_PT2 = TrajectoryUtil.fromPathweaverJson(trajectoryConfidentPT2);
      m_trajectory_realisticPathOutOfTarmac = TrajectoryUtil.fromPathweaverJson(trajectoryRealistic);
      m_trajectory_vision_PT1 = TrajectoryUtil.fromPathweaverJson(trajectoryVisionPT1);
      m_trajectory_vision_PT2 = TrajectoryUtil.fromPathweaverJson(trajectoryVisionPT2);
      m_trajectory_vision_PT3 = TrajectoryUtil.fromPathweaverJson(trajectoryVisionPT3);

      m_trajectory_TS_PT1 = TrajectoryUtil.fromPathweaverJson(trajectoryTSPT1);
      m_trajectory_TS_PT2 = TrajectoryUtil.fromPathweaverJson(trajectoryTSPT2);
      m_trajectory_TS_PT3 = TrajectoryUtil.fromPathweaverJson(trajectoryTSPT3);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectory_confident_PT1_JSON, ex.getStackTrace());
    }

    // TODO:: do we need some failure logic if the trajectory fails to open before creating the robotContainer??

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    /*Path List 
     * Path 1 - Realistic out Of Tarmac
     * Path 2 - Trajectory Confident PT1
     * Path 3 - Trajectory Confident PT2
     * Path 4 - Trajectory Vision PT1
     * Path 5 - Trajectory Vision PT2
     * Path 6 - Trajectory Vision PT3
     * ND --> Path 7 - Trajectory Top Secret PT1 (FROM THE FAR LEFT OF FIELD GET ANOTHER BALL)
     * ND --> Path 8 - Trajectory Top Secret PT2 (FROM THE FAR LEFT OF FIELD GET ANOTHER BALL)
     * ND --> Path 9 - Trajectory Top Secret PT3 (FROM THE FAR LEFT OF FIELD GET ANOTHER BALL)
    */
    m_robotContainer = new RobotContainer( m_trajectory_realisticPathOutOfTarmac, m_trajectory_confident_PT1, m_trajectory_confident_PT2,
                                          m_trajectory_vision_PT1, m_trajectory_vision_PT2, m_trajectory_vision_PT3,
                                          m_trajectory_TS_PT1, m_trajectory_TS_PT2, m_trajectory_TS_PT3);

    
  }
 
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    DriveSubsystem.isTeleop(false);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // TODO:: do we need failure logic to handle not getting hung up in autonomous mode if traj fails
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    DriveSubsystem.isTeleop(false);
    isTeleop = false;
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    DriveSubsystem.isTeleop(true);
    isTeleop = true;
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

}
