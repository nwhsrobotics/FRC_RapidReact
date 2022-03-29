// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterHighModeCommand extends CommandBase {
  private ShooterSubsystem m_shooterSubsystem;

  
  /** Creates a new ShooterHighModeCommand. */
  public ShooterHighModeCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public ShooterHighModeCommand(ShooterSubsystem subsystem) {
    addRequirements(subsystem);
    m_shooterSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("Auto Command", "Shooter High Command");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Setting speed high");
    m_shooterSubsystem.setFlywheel_rpm(m_shooterSubsystem.getHighSpeed(), IndexSubsystem.Constants.SHOOT_SPIN_RPM);
    m_shooterSubsystem.setIsHighEditing(true);
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
