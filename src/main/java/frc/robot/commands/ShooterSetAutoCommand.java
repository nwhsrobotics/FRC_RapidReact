// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetAutoCommand extends CommandBase {
  /** Creates a new ShooterSetAutoCommand. */
  private ShooterSubsystem m_shooterSubsystem;
  private boolean autoMode;
  public ShooterSetAutoCommand(ShooterSubsystem shooterSubsystem, boolean setAuto) {

    addRequirements(shooterSubsystem);
    m_shooterSubsystem = shooterSubsystem;
    autoMode = setAuto;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooterSubsystem.setAutoMode(autoMode);
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
