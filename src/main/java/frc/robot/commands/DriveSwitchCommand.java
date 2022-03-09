// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class DriveSwitchCommand extends CommandBase {
  /** Creates a new DriveSwitchCommand. */
  private DriveSubsystem m_DriveSubsystem;
  private VisionSubsystem m_VisionSubsystem;
  public DriveSwitchCommand(DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
    addRequirements(driveSubsystem); 
    m_DriveSubsystem = driveSubsystem;
    m_VisionSubsystem = visionSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_DriveSubsystem.setReversed(!m_DriveSubsystem.isReversed());
    m_VisionSubsystem.setReversed(m_DriveSubsystem.isReversed());
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
