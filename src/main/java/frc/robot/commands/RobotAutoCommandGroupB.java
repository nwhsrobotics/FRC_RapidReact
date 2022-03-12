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
public class RobotAutoCommandGroupB extends SequentialCommandGroup {
  /** Creates a new RobotAutoCommandGroupB. */
  public RobotAutoCommandGroupB(DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem, ShooterSubsystem shooterSubsystem, IndexSubsystem indexSubsystem, IntakeSubsystem intakeSubsystem, Trajectory dTraj_2, Trajectory dTraj_3) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ShooterHighModeCommand(shooterSubsystem),
    new WaitCommand(2),
    new IndexShootCommand(indexSubsystem, shooterSubsystem),
    new ShooterOffCommand(shooterSubsystem),
    new IntakeLowerCommand(intakeSubsystem),
    new IntakeOnCommand(intakeSubsystem, true),
    new DriveAutoCommand(driveSubsystem, dTraj_2).getRamseteCommand(),
    new IntakeOnCommand(intakeSubsystem, false),
    new IndexLoadCommand(indexSubsystem),
    new DriveAutoCommand(driveSubsystem, dTraj_3).getRamseteCommand(),
    new ShooterHighModeCommand(shooterSubsystem),
    new WaitCommand(2),
    new IndexShootCommand(indexSubsystem, shooterSubsystem),
    new ShooterOffCommand(shooterSubsystem)
    // end of Ideal Path
    );
  }
}
