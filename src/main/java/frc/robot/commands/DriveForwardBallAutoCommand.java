// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class DriveForwardBallAutoCommand extends CommandBase {
  /** Creates a new DriveForwardBallAutoCommand. */
  private VisionSubsystem m_visionSubsystem;
  private DriveSubsystem m_driveSubsystem;

  public DriveForwardBallAutoCommand(DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(visionSubsystem, driveSubsystem);
    m_visionSubsystem = visionSubsystem;
    m_driveSubsystem = driveSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_visionSubsystem.getBallDist_in() >= 50){
      m_driveSubsystem.arcadeDrive(0.1, 0.0);
    } else {
      m_driveSubsystem.arcadeDrive(0.0, 0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_visionSubsystem.getBallDist_in() >= 0) {

    
      if (m_visionSubsystem.getBallDist_in() < 50){
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
    
  }
}
