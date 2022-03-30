// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeBeaterTeleopCommand extends CommandBase {
  /** Creates a new IntakeBeaterTeleopCommand. */
  private IntakeSubsystem m_intakeSubsystem;
  private boolean m_on;
  private boolean m_forward;

  public IntakeBeaterTeleopCommand(IntakeSubsystem intakeSubsystem, boolean on, boolean forward) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);
    m_intakeSubsystem = intakeSubsystem;
    m_on = on;
    m_forward = forward;
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.setBeaterOn(m_on);
    System.out.println("Setting the beater to: " + m_on);
    m_intakeSubsystem.setBeaterForward(m_forward);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
