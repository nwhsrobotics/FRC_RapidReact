// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ToggleShootCommand extends CommandBase {
  private ShooterSubsystem m_shooterSubsystem;
  /** Creates a new ToggleShootCommand. */
  public ToggleShootCommand(ShooterSubsystem subsystem) {
    addRequirements(subsystem);
    m_shooterSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (m_shooterSubsystem.getFlywheelStatus()==false){
      m_shooterSubsystem.setFlywheel(.5); 
      
    } else{
      m_shooterSubsystem.setFlywheel(0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
