// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class IntakeRaiseCommand extends CommandBase {
  private static final double SPEED_DEG_PER_TICK = 50.0/50.0;//90 degs / 50 ticks per second (3 seconds)
  private IntakeSubsystem m_intake;
  private double m_endPosition;
  private double m_speed;
  private double m_currentPosition;
  private int m_clock;
  private static final int INTAKE_TIME_TIMOUT = 175;
  /** Creates a new IntakeLowerCommand. */
  public IntakeRaiseCommand(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = intake;
    //addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*
    m_currentPosition = m_intake.getPosition_deg();
    m_endPosition = IntakeSubsystem.UP_POSITION_DEG;
    m_speed = SPEED_DEG_PER_TICK;
    */
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_intake.setTargetPosition_deg(IntakeSubsystem.UP_POSITION_DEG);
    /*
    m_currentPosition += m_speed;
    
    //OLD INTAKE
    
    if (((m_speed < 0.0) && (m_currentPosition < m_endPosition)) || 
        ((m_speed > 0.0) && (m_currentPosition > m_endPosition))) {
      m_currentPosition = m_endPosition;
    }
    
    m_intake.setPosition_deg(m_currentPosition);
    m_clock += 1;
    //SmartDashboard.putNumber("Intake Clock", m_clock);
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //need to reset the position once we are done
    //m_intake.resetArmEncoder_UP_POS();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: Implement this isFinished function to check for the motor current
    //return (m_intake.getIntakeCurrent() > 1);

    
    //Retaining the OLD INTAKE Code
    //return m_currentPosition == m_endPosition;
    return true;

    /*
    if (m_clock >= INTAKE_TIME_TIMOUT){
      return true;
    }
    */
    //return !m_intake.getIntakeSwitch(); //new intake code to use the switch
  }
}
