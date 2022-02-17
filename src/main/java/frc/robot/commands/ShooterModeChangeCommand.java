// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.package frc.robot.commands;

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterModeChangeCommand extends CommandBase {
  private ShooterSubsystem m_shooterSubsystem;
  private enum ShooterSpeedLevel {
      OFF, LOW, MEDIUM, HIGH
  }
  private ShooterSpeedLevel currentSpeed = ShooterSpeedLevel.OFF;
  public ShooterModeChangeCommand(ShooterSubsystem subsystem) {
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
    if(currentSpeed == ShooterSpeedLevel.OFF) {
        currentSpeed = ShooterSpeedLevel.LOW;
    }
    else if(currentSpeed == ShooterSpeedLevel.LOW) {
        currentSpeed = ShooterSpeedLevel.MEDIUM;
    }
    else if(currentSpeed == ShooterSpeedLevel.MEDIUM) {
      currentSpeed = ShooterSpeedLevel.HIGH;
    }
    else if(currentSpeed == ShooterSpeedLevel.HIGH) {
        currentSpeed = ShooterSpeedLevel.OFF;
    }

    
    switch(currentSpeed) {
        case OFF:
            System.out.println("setting speed off");
            m_shooterSubsystem.setFlywheel_rpm(0.0); 
            break;
        case LOW:
            System.out.println("setting speed low");
            m_shooterSubsystem.setFlywheel_rpm(2500.0);
            break;
        case MEDIUM:
            System.out.println("setting speed medium");
            m_shooterSubsystem.setFlywheel_rpm(3500.0);
            break;
        case HIGH:
            System.out.println("setting speed high");
            m_shooterSubsystem.setFlywheel_rpm(4500.0);
            break;
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