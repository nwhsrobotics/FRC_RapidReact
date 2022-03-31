// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RobotAutoCommandGroupE extends SequentialCommandGroup {
  /** Creates a new RobotAutoCommandGroupE. */
  public RobotAutoCommandGroupE(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IndexSubsystem indexSubsystem, VisionSubsystem visionSubsystem, IntakeSubsystem intakeSubsystem, Trajectory dTraj_4, Trajectory dTraj_5, Trajectory dTraj_6) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new IndexLoadCommand(indexSubsystem),
      new IntakeLowerCommand(intakeSubsystem),
      new DriveAutoCommand(driveSubsystem, dTraj_4).getRamseteCommand(),
      new ShooterHighModeCommand(shooterSubsystem),
      new WaitCommand(2),
      new IndexShootCommand(indexSubsystem, shooterSubsystem),
      new IntakeOnCommand(intakeSubsystem, true),
      new WaitCommand(0.3), //this is so that the alignment right after does not interfere with the initial shot
      new AlignDriveAutoCommand(driveSubsystem, visionSubsystem),
      new DriveAutoCommand(driveSubsystem, dTraj_5).getRamseteCommand(),
      new IndexLoadCommand(indexSubsystem),
      new DriveAutoCommand(driveSubsystem, dTraj_6).getRamseteCommand(),
      new AlignShooterAutoCommand(driveSubsystem, visionSubsystem),
      //new WaitCommand(0.7),
      new IndexShootCommand(indexSubsystem, shooterSubsystem),
      new IntakeOnCommand(intakeSubsystem, false),
      new ShooterOffCommand(shooterSubsystem)

    );
  }
}