// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class SetClimbHeightCommand extends CommandBase {
  private double m_meters;
  private ClimbSubsystem m_climb;
  private double m_currentHeight_m;

  /** Creates a new SetClimbHeightCommand. */
  public SetClimbHeightCommand(ClimbSubsystem climb, double meters) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_meters = meters;
    m_climb = climb;
    addRequirements(m_climb);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_currentHeight_m = m_climb.getHeight_m();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = m_meters - m_currentHeight_m;
    if(distance > ClimbSubsystem.SPEED_UP_DOWN_mps/ClimbSubsystem.TICKS_PER_SECOND) {
      distance = ClimbSubsystem.SPEED_UP_DOWN_mps/ClimbSubsystem.TICKS_PER_SECOND;
    } 
    if(distance < -ClimbSubsystem.SPEED_UP_DOWN_mps/ClimbSubsystem.TICKS_PER_SECOND) {
      distance = -ClimbSubsystem.SPEED_UP_DOWN_mps/ClimbSubsystem.TICKS_PER_SECOND;
    }

    m_currentHeight_m += distance;
    m_climb.setHeight_m(m_currentHeight_m);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return(m_currentHeight_m == m_meters);
  }
}
