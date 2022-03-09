// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class IntakeOnCommand extends CommandBase {
  private IntakeSubsystem m_intake;
  private boolean m_isOn;


  /** Creates a new IntakeOnCommand. */
  public IntakeOnCommand(IntakeSubsystem intake, boolean isOn) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake=intake;
    m_isOn = isOn;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    m_intake.setBeaterOn(m_isOn);
    //m_intake.setBeaterSpeed(m_speed);
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
