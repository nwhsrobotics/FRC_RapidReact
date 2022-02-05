// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexLoadCommand;
import frc.robot.commands.IndexShootCommand;
import frc.robot.commands.IndexUnloadCommand;
import frc.robot.commands.DriveBackwardCommand;
import frc.robot.commands.DriveForwardCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(); //matthew did this :))) i like hotdogs 
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexSubsystem m_indexSubsystem = new IndexSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  
  private final IndexLoadCommand m_indexLoadCommand = new IndexLoadCommand(m_indexSubsystem);
  private final IndexShootCommand m_indexShootCommand = new IndexShootCommand(m_indexSubsystem);
  private final IndexUnloadCommand m_indexUnloadCommand = new IndexUnloadCommand(m_indexSubsystem);

  //private final DriveBackwardCommand m_driveBackwardCommand = new DriveBackwardCommand(m_driveSubsystem);

  private final XboxController m_joy0 = new XboxController(0);
  private final JoystickButton joy0_a = new JoystickButton(m_joy0, 1);
  private final JoystickButton joy0_b = new JoystickButton(m_joy0, 2);

  private final DriveForwardCommand m_driveForwardCommand = new DriveForwardCommand(m_driveSubsystem,m_joy0);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveForwardCommand);
    // Configure the button bindings
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
    // joy0_b.whenPressed(m_driveForwardCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
