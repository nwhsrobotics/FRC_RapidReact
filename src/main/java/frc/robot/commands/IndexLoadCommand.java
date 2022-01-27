// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexSubsystem;

public class IndexLoadCommand extends CommandBase {
  /** Creates a new IndexLoadCommand. */
  
  private IndexSubsystem m_indexSubsystem;
  private int m_currentPosition_deg = 0;
  private int m_endPosition_deg = 0;
  private final int LOAD_DEGREES = 0;
  private final int LOAD_DEGREES_PER_TICK = 0;
  
  public IndexLoadCommand(IndexSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_indexSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_currentPosition_deg = m_indexSubsystem.getPosition_deg();
    m_endPosition_deg = m_currentPosition_deg + LOAD_DEGREES;
    // TODO check for state machine at already full.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int nextPosition_deg = m_currentPosition_deg + LOAD_DEGREES_PER_TICK;
    if (nextPosition_deg > m_endPosition_deg) {
      nextPosition_deg = m_endPosition_deg;
    }
    m_indexSubsystem.setPosition_deg(nextPosition_deg);
    m_currentPosition_deg = nextPosition_deg;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // TODO update state of the indexer.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_currentPosition_deg >= m_endPosition_deg);
  }
}
