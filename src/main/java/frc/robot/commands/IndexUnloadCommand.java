// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IndexSubsystem.IndexerState;

public class IndexUnloadCommand extends CommandBase {

  private static final double SECONDS_PER_TICK = 1.0 / 50.0;
  // indexer wheel speed in degrees per second
  private static final double UNLOAD_SPEED = -30.0; // TODO verify speed should be negative
  /** Creates a new IndexLoadCommand. */
  
  private IndexSubsystem m_indexSubsystem;
  private double m_currentPosition_deg = 0.0;
  private double m_endPosition_deg = 0.0;
  private final double UNLOAD_EMPTY_DEGREES = -0.0; // unload direction is opposite load and shoot
  private final double UNLOAD_LOAD_1_DEGREES = -0.0;
  private final double UNLOAD_LOAD_2_DEGREES = -0.0;
  private final double UNLOAD_ARMED_1_DEGREES = -0.0;
  private final double UNLOAD_DEGREES_PER_TICK = 0.0;
  private double m_speed = 0.0;
  private boolean m_done = false;

  public IndexUnloadCommand(IndexSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_indexSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_done = false;

    IndexerState state = m_indexSubsystem.getState();
    m_currentPosition_deg = m_indexSubsystem.getPosition_deg();
    
    switch (state){
      case EMPTY:
        m_endPosition_deg = m_currentPosition_deg + UNLOAD_EMPTY_DEGREES;
        m_speed = UNLOAD_SPEED;

        // set state
        m_indexSubsystem.setState(IndexerState.EMPTY);
        break; 

      case LOADED_1:
        m_endPosition_deg = m_currentPosition_deg + UNLOAD_LOAD_1_DEGREES;
        m_speed = UNLOAD_SPEED;
        
        // set state
        m_indexSubsystem.setState(IndexerState.EMPTY);
        break;

      case LOADED_2:
        m_endPosition_deg = m_currentPosition_deg + UNLOAD_LOAD_2_DEGREES;
        m_speed = UNLOAD_SPEED;

        // set state
        m_indexSubsystem.setState(IndexerState.LOADED_1);
        break;

      case ARMED_1:
        m_endPosition_deg = m_currentPosition_deg + UNLOAD_ARMED_1_DEGREES;
        m_speed = UNLOAD_SPEED;

        // set state
        m_indexSubsystem.setState(IndexerState.EMPTY);
        break;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double nextPosition_deg = m_currentPosition_deg + (m_speed * SECONDS_PER_TICK);
    if(m_speed > 0.0){
      if (nextPosition_deg > m_endPosition_deg) {
        nextPosition_deg = m_endPosition_deg;
        m_done = true;
      }
    }
    else{
      if (nextPosition_deg < m_endPosition_deg) {
        nextPosition_deg = m_endPosition_deg;
        m_done = true;
      }
    }
    
    m_indexSubsystem.setPosition_deg(nextPosition_deg);
    m_currentPosition_deg = nextPosition_deg;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_done;
  }
}
