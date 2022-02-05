// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends CommandBase {
  private ClimbSubsystem m_climbSubsystem;
  private int m_angle;

  /** Creates a new ClimbCommand. */
  public ClimbCommand(ClimbSubsystem climbSubsystem, int angle) {
    m_climbSubsystem = climbSubsystem;
    m_angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_angle == 0) {
      m_climbSubsystem.moveUp();
    }
    if (m_angle == 45) {
      m_climbSubsystem.moveUp();
      m_climbSubsystem.moveBack();
    }
    if (m_angle == 90) {
      m_climbSubsystem.moveBack();
    }
    if (m_angle == 135) {
      m_climbSubsystem.moveDown();
      m_climbSubsystem.moveBack();
    }
    if (m_angle == 180) {
      m_climbSubsystem.moveDown();
    }
    if (m_angle == 225) {
      m_climbSubsystem.moveDown();
      m_climbSubsystem.moveForward();
    }
    if (m_angle == 270) {
      m_climbSubsystem.moveForward();
    }
    if (m_angle == 315) {
      m_climbSubsystem.moveUp();
      m_climbSubsystem.moveForward();
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
