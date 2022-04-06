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
  private Trajectory m_traj_PA_PT1;
  private Trajectory m_traj_PA_PT2;
  private Trajectory m_traj_PA_PT3;
  private Trajectory m_traj_PA_PT4;

  private Trajectory m_traj_PB_PT1;
  private Trajectory m_traj_PB_PT2;
  private Trajectory m_traj_PB_PT3;
  private Trajectory m_traj_PB_PT4;
  private Trajectory m_traj_PB_PT5;
  private Trajectory m_traj_PB_PT6;

  private Trajectory m_traj_PC_PT1;
  private Trajectory m_traj_PC_PT2;
  private Trajectory m_traj_PC_PT3;
  private Trajectory m_traj_PC_PT4;

  private Trajectory m_traj_JustDrive;


  private static boolean isTeleop = false;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //CameraServer.startAutomaticCapture(0);
    //CameraServer.startAutomaticCapture(1);

    //Starting Point A
    String str_PA_PT1_JSON = "paths/PathA_PT1.wpilib.json";
    String str_PA_PT2_JSON = "paths/PathA_PT2.wpilib.json";
    String str_PA_PT3_JSON = "paths/PathA_PT3.wpilib.json";
    String str_PA_PT4_JSON = "paths/PathA_PT4.wpilib.json";

    //Starting Point B
    String str_PB_PT1_JSON = "paths/PathB_PT1.wpilib.json";
    String str_PB_PT2_JSON = "paths/PathB_PT2.wpilib.json";
    String str_PB_PT3_JSON = "paths/PathB_PT3.wpilib.json";
    String str_PB_PT4_JSON = "paths/PathB_PT4.wpilib.json";
    String str_PB_PT5_JSON = "paths/PathB_PT5.wpilib.json";
    String str_PB_PT6_JSON = "paths/PathB_PT6.wpilib.json";


    //Starting Point C
    String str_PC_PT1_JSON = "paths/PathC_PT1.wpilib.json";
    String str_PC_PT2_JSON = "paths/PathC_PT2.wpilib.json";
    String str_PC_PT3_JSON = "paths/PathC_PT3.wpilib.json";
    String str_PC_PT4_JSON = "paths/PathC_PT4.wpilib.json";


    //Just Drive Path
    String str_JustDrive_JSON = "paths/JustDrive.wpilib.json";
   

    //creating our trajectory objects
    m_traj_PA_PT1 = new Trajectory();
    m_traj_PA_PT2 = new Trajectory();
    m_traj_PA_PT3 = new Trajectory();
    m_traj_PA_PT4 = new Trajectory();

    m_traj_PB_PT1 = new Trajectory();
    m_traj_PB_PT2 = new Trajectory();
    m_traj_PB_PT3 = new Trajectory();
    m_traj_PB_PT4 = new Trajectory();
    m_traj_PB_PT5 = new Trajectory();
    m_traj_PB_PT6 = new Trajectory();

    m_traj_PC_PT1 = new Trajectory();
    m_traj_PC_PT2 = new Trajectory();
    m_traj_PC_PT3 = new Trajectory();
    m_traj_PC_PT4 = new Trajectory();

    m_traj_JustDrive = new Trajectory();
    

    
    try {
      //creating paths
      Path path_PA_PT1 = Filesystem.getDeployDirectory().toPath().resolve(str_PA_PT1_JSON);
      Path path_PA_PT2 = Filesystem.getDeployDirectory().toPath().resolve(str_PA_PT2_JSON);
      Path path_PA_PT3 = Filesystem.getDeployDirectory().toPath().resolve(str_PA_PT3_JSON);
      Path path_PA_PT4 = Filesystem.getDeployDirectory().toPath().resolve(str_PA_PT4_JSON);

      Path path_PB_PT1 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT1_JSON);
      Path path_PB_PT2 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT2_JSON);
      Path path_PB_PT3 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT3_JSON);
      Path path_PB_PT4 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT4_JSON);
      Path path_PB_PT5 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT5_JSON);
      Path path_PB_PT6 = Filesystem.getDeployDirectory().toPath().resolve(str_PB_PT6_JSON);

      Path path_PC_PT1 = Filesystem.getDeployDirectory().toPath().resolve(str_PC_PT1_JSON);
      Path path_PC_PT2 = Filesystem.getDeployDirectory().toPath().resolve(str_PC_PT2_JSON);
      Path path_PC_PT3 = Filesystem.getDeployDirectory().toPath().resolve(str_PC_PT3_JSON);
      Path path_PC_PT4 = Filesystem.getDeployDirectory().toPath().resolve(str_PC_PT4_JSON);

      Path path_JustDrive = Filesystem.getDeployDirectory().toPath().resolve(str_JustDrive_JSON);


      


      // creating out trajectory objects
      m_traj_PA_PT1 = TrajectoryUtil.fromPathweaverJson(path_PA_PT1);
      m_traj_PA_PT2 = TrajectoryUtil.fromPathweaverJson(path_PA_PT2);
      m_traj_PA_PT3 = TrajectoryUtil.fromPathweaverJson(path_PA_PT3);
      m_traj_PA_PT4 = TrajectoryUtil.fromPathweaverJson(path_PA_PT4);

      m_traj_PB_PT1 = TrajectoryUtil.fromPathweaverJson(path_PB_PT1);
      m_traj_PB_PT2 = TrajectoryUtil.fromPathweaverJson(path_PB_PT2);
      m_traj_PB_PT3 = TrajectoryUtil.fromPathweaverJson(path_PB_PT3);
      m_traj_PB_PT4 = TrajectoryUtil.fromPathweaverJson(path_PB_PT4);
      m_traj_PB_PT5 = TrajectoryUtil.fromPathweaverJson(path_PB_PT5);
      m_traj_PB_PT6 = TrajectoryUtil.fromPathweaverJson(path_PB_PT6);

      m_traj_PC_PT1 = TrajectoryUtil.fromPathweaverJson(path_PC_PT1);
      m_traj_PC_PT2 = TrajectoryUtil.fromPathweaverJson(path_PC_PT2);
      m_traj_PC_PT3 = TrajectoryUtil.fromPathweaverJson(path_PC_PT3);
      m_traj_PC_PT4 = TrajectoryUtil.fromPathweaverJson(path_PC_PT4);

      m_traj_JustDrive = TrajectoryUtil.fromPathweaverJson(path_JustDrive);


    
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + str_PA_PT1_JSON, ex.getStackTrace());
    }

    // TODO:: do we need some failure logic if the trajectory fails to open before creating the robotContainer??

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    /*Path List 
     * Path A - Part 1
     * Path A - Part 2
     * Path A - Part 3
     * Path A - Part 4
     * 
     * Path B - Part 1
     * Path B - Part 2
     * Path B - Part 3
     * Path B - Part 4
     * Path B - Part 5
     * Path B - Part 6
     * 
     * Path C - Part 1
     * Path C - Part 2
     * Path C - Part 3
     * 
     * Path Just Drive
    */
    m_robotContainer = new RobotContainer(m_traj_PA_PT1, m_traj_PA_PT2, m_traj_PA_PT3, m_traj_PA_PT4, 
                                        m_traj_PB_PT1, m_traj_PB_PT2, m_traj_PB_PT3, m_traj_PB_PT4, m_traj_PB_PT5, m_traj_PB_PT6, 
                                        m_traj_PC_PT1, m_traj_PC_PT2, m_traj_PC_PT3, m_traj_PC_PT4,
                                        m_traj_JustDrive);

    
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
