// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RobotAutoCommandGroupF extends SequentialCommandGroup {
  /** Creates a new RobotAutoCommandGroupF. */
  public RobotAutoCommandGroupF(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IndexSubsystem indexSubsystem, IntakeSubsystem intakeSubsystem, ClimbSubsystem climbSubsystem, Trajectory dTraj_PA_PT1, Trajectory dTraj_PA_PT2) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetClimbHeightCommand(climbSubsystem, ClimbSubsystem.AUTO_CLIMB_RAISE_m), //0.102 meters is 4 inches raise the climb during startup
      new IndexLoadCommand(indexSubsystem),
      new IntakeLowerCommand(intakeSubsystem),
      new DriveAutoCommand(driveSubsystem, dTraj_PA_PT1).getRamseteCommand(),
      new ShooterHighModeCommand(shooterSubsystem),
      new WaitCommand(1.0),
      new IndexShootCommand(indexSubsystem, shooterSubsystem),
      new WaitCommand(0.5),
      new ShooterOffCommand(shooterSubsystem),
      new DriveAutoCommand(driveSubsystem, dTraj_PA_PT2).getRamseteCommand()

    );
  }
}