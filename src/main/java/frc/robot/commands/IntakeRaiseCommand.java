// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class IntakeRaiseCommand extends CommandBase {
  private static final double SPEED_DEG_PER_TICK = 10.0/50.0;//90 degs / 50 ticks per second (3 seconds)
  private IntakeSubsystem m_intake;
  private double m_endPosition;
  private double m_speed;
  private double m_currentPosition;
  /** Creates a new IntakeLowerCommand. */
  public IntakeRaiseCommand(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = intake;
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_endPosition = IntakeSubsystem.UP_POSITION_DEG;
      m_speed = SPEED_DEG_PER_TICK;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentPosition += m_speed;
    if (((m_speed < 0.0) && (m_currentPosition < m_endPosition)) || 
        ((m_speed > 0.0) && (m_currentPosition > m_endPosition))) {
      m_currentPosition = m_endPosition;
    }
    m_intake.setPosition_deg(m_currentPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_currentPosition == m_endPosition;
  }
}
