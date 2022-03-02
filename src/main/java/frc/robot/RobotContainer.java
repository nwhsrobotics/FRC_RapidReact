// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveAutoCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexLoadCommand;
import frc.robot.commands.IndexShootCommand;
import frc.robot.commands.IndexUnloadCommand;
import frc.robot.commands.RobotAutoCommand;
import frc.robot.commands.DriveBackwardCommand;
import frc.robot.commands.DriveForwardCommand;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.ShooterModeChangeCommand;
import frc.robot.commands.ShooterSetAutoCommand;
import frc.robot.commands.ShooterSpeedAdjustCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(); //matthew did this :))) i like hotdogs 
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexSubsystem m_indexSubsystem = new IndexSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(m_visionSubsystem);
  
  
  private final IndexLoadCommand m_indexLoadCommand = new IndexLoadCommand(m_indexSubsystem);
  private final IndexShootCommand m_indexShootCommand = new IndexShootCommand(m_indexSubsystem, m_shooterSubsystem);
  private final IndexUnloadCommand m_indexUnloadCommand = new IndexUnloadCommand(m_indexSubsystem);
  private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_intakeSubsystem);
  private final ShooterModeChangeCommand m_shooterModeChangeCommand = new ShooterModeChangeCommand(m_shooterSubsystem);
  private final ShooterSetAutoCommand m_shooterSetAutoFalseCommand = new ShooterSetAutoCommand(m_shooterSubsystem, false);
  private final ShooterSetAutoCommand m_shooterSetAutoTrueCommand = new ShooterSetAutoCommand(m_shooterSubsystem, true);
  private final ShooterSpeedAdjustCommand m_shooterSpeedIncreaseCommand = new ShooterSpeedAdjustCommand(m_shooterSubsystem, 50);
  private final ShooterSpeedAdjustCommand m_shooterSpeedDecreaseCommand = new ShooterSpeedAdjustCommand(m_shooterSubsystem, -50);


  //private final DriveBackwardCommand m_driveBackwardCommand = new DriveBackwardCommand(m_driveSubsystem);

  private final ClimbCommand m_climbCommand0 = new ClimbCommand(m_climbSubsystem, 0);
  private final ClimbCommand m_climbCommand45 = new ClimbCommand(m_climbSubsystem, 45);
  private final ClimbCommand m_climbCommand90 = new ClimbCommand(m_climbSubsystem, 90);
  private final ClimbCommand m_climbCommand135 = new ClimbCommand(m_climbSubsystem, 135);
  private final ClimbCommand m_climbCommand180 = new ClimbCommand(m_climbSubsystem, 180);
  private final ClimbCommand m_climbCommand225 = new ClimbCommand(m_climbSubsystem, 225);
  private final ClimbCommand m_climbCommand270 = new ClimbCommand(m_climbSubsystem, 270);
  private final ClimbCommand m_climbCommand315 = new ClimbCommand(m_climbSubsystem, 315);


  private final XboxController m_joy0 = new XboxController(0);
  private final JoystickButton joy0_a = new JoystickButton(m_joy0, 1);
  private final JoystickButton joy0_b = new JoystickButton(m_joy0, 2);
  private final JoystickButton joy0_y = new JoystickButton(m_joy0, 4);
  private final JoystickButton joy0_LeftBumper = new JoystickButton(m_joy0, 5);
  private final JoystickButton joy0_RightBumper = new JoystickButton(m_joy0, 6);

  private final POVButton m_pov0 = new POVButton(m_joy0, 0);
  private final POVButton m_pov45 = new POVButton(m_joy0, 45);
  private final POVButton m_pov90 = new POVButton(m_joy0, 90);
  private final POVButton m_pov135 = new POVButton(m_joy0, 135);
  private final POVButton m_pov180 = new POVButton(m_joy0, 180);
  private final POVButton m_pov225 = new POVButton(m_joy0, 225);
  private final POVButton m_pov270 = new POVButton(m_joy0, 270);
  private final POVButton m_pov315 = new POVButton(m_joy0, 315);

  private final DriveForwardCommand m_driveForwardCommand = new DriveForwardCommand(m_driveSubsystem,m_joy0);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveAutoCommand m_driveAutoCommand;
  private final RobotAutoCommand m_sequentialAutoCommand;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Trajectory traj_1) {
    m_driveSubsystem.setDefaultCommand(m_driveForwardCommand);
    m_driveAutoCommand = new DriveAutoCommand(m_driveSubsystem, traj_1);
    m_sequentialAutoCommand = new RobotAutoCommand(m_driveSubsystem, traj_1);
    // Configure the button btindings
    configureButtonBindings();
  }
  public DriveForwardCommand getM_DriveForwardCommand(){
    return m_driveForwardCommand;
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // assign index*Command
    // joy0_a.whenPressed(m_updateIndexPositionCommand);

    joy0_a.whenPressed(m_shooterSetAutoTrueCommand);
    joy0_b.whenPressed(m_shooterModeChangeCommand);
    joy0_y.whenPressed(m_shooterSetAutoFalseCommand);


    m_pov0.whileHeld(m_climbCommand0);
    m_pov45.whileHeld(m_climbCommand45);
    m_pov90.whileHeld(m_climbCommand90);
    m_pov135.whileHeld(m_climbCommand135);
    m_pov180.whileHeld(m_climbCommand180);
    m_pov225.whileHeld(m_climbCommand225);
    m_pov270.whileHeld(m_climbCommand270);
    m_pov315.whileHeld(m_climbCommand315);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_sequentialAutoCommand;
  }
}
