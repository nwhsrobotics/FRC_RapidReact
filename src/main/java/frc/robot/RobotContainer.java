// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveAutoCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IndexLoadCommand;
import frc.robot.commands.IndexShootCommand;
import frc.robot.commands.IndexUnloadCommand;
import frc.robot.commands.IntakeBeaterTeleopCommand;
import frc.robot.commands.RobotAutoCommandGroupA;
import frc.robot.commands.RobotAutoCommandGroupB;
import frc.robot.commands.RobotAutoCommandGroupC;
import frc.robot.commands.RobotAutoCommandGroupD;
import frc.robot.commands.RobotAutoCommandGroupE;
import frc.robot.commands.DriveBackwardCommand;
import frc.robot.commands.DriveForwardBallAutoCommand;
import frc.robot.commands.DriveForwardCommand;
import frc.robot.commands.DriveSwitchCommand;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.IntakeRaiseCommand;
import frc.robot.commands.IntakeLowerCommand;
import frc.robot.commands.IntakeOnCommand;
import frc.robot.commands.ShooterModeChangeCommand;
import frc.robot.commands.ShooterHighModeCommand;
import frc.robot.commands.ShooterLowModeCommand;
import frc.robot.commands.ShooterOffCommand;
import frc.robot.commands.ShooterSetAutoCommand;
import frc.robot.commands.ShooterSpeedAdjustCommand;
import frc.robot.commands.ToggleHighGearCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  private SendableChooser<SequentialCommandGroup> autoChooser;
  private SequentialCommandGroup m_autoChooser;
  
  
  
  // The robot's subsystems and commands are defined here...
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(m_visionSubsystem); //matthew did this :))) i like hotdogs 
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexSubsystem m_indexSubsystem = new IndexSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem(m_visionSubsystem);
  
  
  private final IndexLoadCommand m_indexLoadCommand = new IndexLoadCommand(m_indexSubsystem);
  private final IndexShootCommand m_indexShootCommand = new IndexShootCommand(m_indexSubsystem, m_shooterSubsystem);
  private final IndexUnloadCommand m_indexUnloadCommand = new IndexUnloadCommand(m_indexSubsystem);
  private final ToggleIntakeCommand m_toggleIntakeCommand = new ToggleIntakeCommand(m_intakeSubsystem);

  
  private final IntakeRaiseCommand m_intakeRaiseCommand = new IntakeRaiseCommand(m_intakeSubsystem);
  private final IntakeLowerCommand m_intakeLowerCommand = new IntakeLowerCommand(m_intakeSubsystem);
  private final ShooterModeChangeCommand m_shooterModeChangeCommand = new ShooterModeChangeCommand(m_shooterSubsystem);
  private final ShooterHighModeCommand m_shooterHighModeCommand = new ShooterHighModeCommand(m_shooterSubsystem);
  private final ShooterLowModeCommand m_shooterLowModeCommand = new ShooterLowModeCommand(m_shooterSubsystem);
  private final ShooterOffCommand m_shooterOffCommand = new ShooterOffCommand(m_shooterSubsystem);
  private final ShooterSetAutoCommand m_shooterSetAutoFalseCommand = new ShooterSetAutoCommand(m_shooterSubsystem, false);
  private final ShooterSetAutoCommand m_shooterSetAutoTrueCommand = new ShooterSetAutoCommand(m_shooterSubsystem, true);
  private final ShooterSpeedAdjustCommand m_shooterSpeedIncreaseCommand = new ShooterSpeedAdjustCommand(m_shooterSubsystem, 50);
  private final ShooterSpeedAdjustCommand m_shooterSpeedDecreaseCommand = new ShooterSpeedAdjustCommand(m_shooterSubsystem, -50);
  private final DriveSwitchCommand m_DriveSwitchCommand = new DriveSwitchCommand(m_driveSubsystem, m_visionSubsystem);

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
  private final XboxController m_joy1 = new XboxController(1);

  private final JoystickButton m_joy0_a = new JoystickButton(m_joy0, 1);
  private final JoystickButton m_joy0_y = new JoystickButton(m_joy0, 4);
  private final JoystickButton m_joy0_leftBumper = new JoystickButton(m_joy0, 5);
  private final JoystickButton m_joy0_rightBumper = new JoystickButton(m_joy0, 6);
  private final JoystickButton m_joy0_menu = new JoystickButton(m_joy0, 8);
  private final JoystickButton m_joy0_rightJoyButton = new JoystickButton(m_joy0, 10);

  private final POVButton m_joy0_pov0 = new POVButton(m_joy0, 0);
  private final POVButton m_joy0_pov45 = new POVButton(m_joy0, 45);
  private final POVButton m_joy0_pov90 = new POVButton(m_joy0, 90);
  private final POVButton m_joy0_pov135 = new POVButton(m_joy0, 135);
  private final POVButton m_joy0_pov180 = new POVButton(m_joy0, 180);
  private final POVButton m_joy0_pov225 = new POVButton(m_joy0, 225);
  private final POVButton m_joy0_pov270 = new POVButton(m_joy0, 270);
  private final POVButton m_joy0_pov315 = new POVButton(m_joy0, 315);

  private final JoystickButton m_joy1_a = new JoystickButton(m_joy1, 1);
  private final JoystickButton m_joy1_b = new JoystickButton(m_joy1, 2);
  private final JoystickButton m_joy1_x = new JoystickButton(m_joy1, 3);
  private final JoystickButton m_joy1_y = new JoystickButton(m_joy1, 4);
  private final JoystickButton m_joy1_LeftBumper = new JoystickButton(m_joy1, 5);
  private final JoystickButton m_joy1_RightBumper = new JoystickButton(m_joy1, 6);
  private final JoystickButton m_joy1_back = new JoystickButton(m_joy1, 7);
  private final JoystickButton m_joy1_menu = new JoystickButton(m_joy1, 8);

  

  private final POVButton m_joy1_pov0 = new POVButton(m_joy1, 0);
  private final POVButton m_joy1_pov90 = new POVButton(m_joy1, 90);
  private final POVButton m_joy1_pov180 = new POVButton(m_joy1, 180);
  private final POVButton m_joy1_pov270 = new POVButton(m_joy1, 270);

  
  private final ToggleHighGearCommand m_toggleHighGear = new ToggleHighGearCommand(m_driveSubsystem);
  private final DriveForwardCommand m_driveForwardCommand = new DriveForwardCommand(m_driveSubsystem, m_joy0);
  private final IntakeBeaterTeleopCommand m_intakeBeaterOn = new IntakeBeaterTeleopCommand(m_intakeSubsystem, true, true);
  private final IntakeBeaterTeleopCommand m_intakeBeaterOff = new IntakeBeaterTeleopCommand(m_intakeSubsystem, false, true);
  private final IntakeBeaterTeleopCommand m_intakeBeaterReverse = new IntakeBeaterTeleopCommand(m_intakeSubsystem, true, false);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);


  private final DriveAutoCommand m_driveAutoCommand;
  private final RobotAutoCommandGroupA m_sequentialAutoCommandA;
  private final RobotAutoCommandGroupB m_sequentialAutoCommandB;
  private final RobotAutoCommandGroupC m_sequentialAutoCommandC;
  private final RobotAutoCommandGroupD m_sequentialAutoCommandD;
  private final RobotAutoCommandGroupE m_sequentialAutoCommandE;
  private final DriveForwardBallAutoCommand m_driveForwardBallAutoCommand;


  
  private Trajectory m_traj_1;
  private Trajectory m_traj_2;
  private Trajectory m_traj_4;
  
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Trajectory traj_1, Trajectory traj_2, Trajectory traj_3, Trajectory traj_4, Trajectory traj_5, Trajectory traj_6) {
    
    
    //autoChooser.addDefault("Auto Mode A", m_autoCommand);
    //this is where all of the Drive Commands are created and set
    m_driveSubsystem.setDefaultCommand(m_driveForwardCommand);                     // Pass in trajectory 1 2 3 based on input

    m_driveAutoCommand = new DriveAutoCommand(m_driveSubsystem, traj_1);           // TODO errors need to be fixed cannot build code
    m_sequentialAutoCommandA = new RobotAutoCommandGroupA(m_driveSubsystem, m_visionSubsystem, m_shooterSubsystem, m_indexSubsystem,  traj_4, traj_5);
    m_sequentialAutoCommandB = new RobotAutoCommandGroupB(m_driveSubsystem,m_visionSubsystem, m_shooterSubsystem, m_indexSubsystem, m_intakeSubsystem, traj_2, traj_3);
    m_sequentialAutoCommandC = new RobotAutoCommandGroupC(m_driveSubsystem, m_shooterSubsystem ,m_indexSubsystem, m_visionSubsystem, m_intakeSubsystem, traj_4, traj_5, traj_6);
    m_sequentialAutoCommandD = new RobotAutoCommandGroupD(m_driveSubsystem, traj_4);
    m_sequentialAutoCommandE = new RobotAutoCommandGroupE(m_driveSubsystem, m_shooterSubsystem ,m_indexSubsystem, m_visionSubsystem, m_intakeSubsystem, traj_4, traj_5, traj_6);
    m_driveForwardBallAutoCommand = new DriveForwardBallAutoCommand(m_driveSubsystem, m_visionSubsystem);

    
    
    autoChooser = new SendableChooser<SequentialCommandGroup>();
    
    autoChooser.setDefaultOption("Auto Mode A", m_sequentialAutoCommandA);
    
    autoChooser.addOption("Auto Mode B", m_sequentialAutoCommandB);
    autoChooser.addOption("Auto Mode C", m_sequentialAutoCommandC);
    autoChooser.addOption("Auto Mode D", m_sequentialAutoCommandD);
    autoChooser.addOption("Auto Mode E", m_sequentialAutoCommandE);

    
    SmartDashboard.putData("Auto Mode", autoChooser);
    m_traj_1 = traj_1;
    m_traj_2 = traj_2;
    m_traj_4 = traj_4;
    
    

    // Configure the button btindings
    configureButtonBindings();
  }
  /*public DriveForwardCommand getM_DriveForwardCommand(){
    return m_driveForwardCommand;
  }*/
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // assign index*Command
    // joy0_a.whenPressed(m_updateIndexPositionCommand);

    m_joy1_a.whenPressed(m_shooterOffCommand);
    m_joy1_b.whenPressed(m_shooterLowModeCommand);
    m_joy1_y.whenPressed(m_shooterHighModeCommand);
    m_joy1_x.whenPressed(m_indexLoadCommand);
    m_joy1_RightBumper.whenPressed(m_indexShootCommand);
    m_joy1_LeftBumper.whenPressed(m_indexUnloadCommand);
    m_joy1_back.whenPressed(m_shooterSetAutoFalseCommand);
    m_joy1_menu.whenPressed(m_shooterSetAutoTrueCommand);

    m_joy1_pov0.whenPressed(m_shooterSpeedIncreaseCommand);
    m_joy1_pov180.whenPressed(m_shooterSpeedDecreaseCommand);

    m_joy0_a.whenPressed(m_intakeLowerCommand);
    m_joy0_y.whenPressed(m_intakeRaiseCommand);
    m_joy0_rightBumper.whenPressed(m_intakeBeaterOn);
    m_joy0_rightBumper.whenReleased(m_intakeBeaterOff);

    m_joy0_leftBumper.whenPressed(m_intakeBeaterReverse);
    m_joy0_leftBumper.whenReleased(m_intakeBeaterOff);
    m_joy0_rightJoyButton.whenPressed(m_toggleHighGear);

    m_joy0_pov0.whileHeld(m_climbCommand0);
    m_joy0_pov45.whileHeld(m_climbCommand45);
    m_joy0_pov90.whileHeld(m_climbCommand90);
    m_joy0_pov135.whileHeld(m_climbCommand135);
    m_joy0_pov180.whileHeld(m_climbCommand180);
    m_joy0_pov225.whileHeld(m_climbCommand225);
    m_joy0_pov270.whileHeld(m_climbCommand270);
    m_joy0_pov315.whileHeld(m_climbCommand315);
    
    m_joy0_menu.whenPressed(m_DriveSwitchCommand);

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
     // return new InfiniteRechargeAutoCommandGroup(m_intakeSubsystem, m_driveSubsystem, m_visionSubsystem, m_indexSubsystem, m_shooterSubsystem);
      //return new GalacticChallengeCommandGroup(m_intakeSubsystem, m_driveSubsystem, m_visionSubsystem, m_storageSubsystem);
      //return new RapidReactgetAutoCommandGroup(m_intakeSubsystem, m_driveSubsystem, m_visionSubsystem, m_indexSubsystem, m_shooterSubsystem);
  
      /*
      m_autoChooser = autoChooser.getSelected();
      // An ExampleCommand will run in autonomous
      return m_autoChooser;
      */
    // An ExampleCommand will run in autonomous
    if (autoChooser.getSelected().equals(m_sequentialAutoCommandB)){
      SmartDashboard.putString("Current Command Selected", "Auto Mode B");
      m_driveSubsystem.resetOdometry(m_traj_2.getInitialPose());
    } else if (autoChooser.getSelected().equals(m_sequentialAutoCommandA)){
      SmartDashboard.putString("Current Command Selected", "Auto Mode A");
      m_driveSubsystem.resetOdometry(m_traj_4.getInitialPose());
    } else if (autoChooser.getSelected().equals(m_sequentialAutoCommandC)){
      SmartDashboard.putString("Current Command Selected", "Auto Mode C");
      m_driveSubsystem.resetOdometry(m_traj_4.getInitialPose());
    } else if (autoChooser.getSelected().equals(m_sequentialAutoCommandD)){
      SmartDashboard.putString("Current Command Selected", "Auto Mode D");
      m_driveSubsystem.resetOdometry(m_traj_4.getInitialPose());
    } else if (autoChooser.getSelected().equals(m_sequentialAutoCommandE)){
      SmartDashboard.putString("Current Command Selected", "Auto Mode E");
      m_driveSubsystem.resetOdometry(m_traj_4.getInitialPose());
    }
    
    return autoChooser.getSelected();
  }
}
