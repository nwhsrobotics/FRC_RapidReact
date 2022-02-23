// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexSubsystem;

public class IndexSpeedTestCommand extends CommandBase {
  /** Creates a new IndexSpeedTestCommand. */

  private IndexSubsystem m_indexSubsystem;
  private double m_currentPosition_m = 0.0;
  private double m_endPosition_m = 0.0;
  private double m_speed = 0.0;
  private boolean m_done = false;

  public IndexSpeedTestCommand(IndexSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_indexSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_done = false;
    m_currentPosition_m = m_indexSubsystem.getPosition_m();
      m_endPosition_m = m_currentPosition_m + 2.0;
      m_speed = IndexSubsystem.Constants.SPEED_FORWARD_TEST_MPS;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double nextPosition_m = m_currentPosition_m + (m_speed * Constants.SECONDS_PER_TICK);
    if (m_speed > 0.0) {
      if (nextPosition_m > m_endPosition_m) {
        nextPosition_m = m_endPosition_m;
        m_done = true;
      }
    } else {
      if (nextPosition_m < m_endPosition_m) {
        nextPosition_m = m_endPosition_m;
        m_done = true;
      }
    }

    m_indexSubsystem.setPosition_m(nextPosition_m);
    m_currentPosition_m = nextPosition_m;
  }

  // Called once the command ends or is interrupted.
   // TODO update state of the indexer.
    @Override
  public void end(boolean interrupted) {
 }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_done;
  }
}
