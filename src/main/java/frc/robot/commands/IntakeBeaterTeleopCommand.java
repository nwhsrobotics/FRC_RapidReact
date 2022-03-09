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
  private XboxController m_joy;
  public IntakeBeaterTeleopCommand(IntakeSubsystem intakeSubsystem, XboxController joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);
    m_intakeSubsystem = intakeSubsystem;
    m_joy = joy;
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rightTriggerAxis = m_joy.getRightTriggerAxis();
    double leftTriggerAxis = m_joy.getLeftTriggerAxis();

    if (rightTriggerAxis > 0.5){
      m_intakeSubsystem.setBeaterOn(true);
      m_intakeSubsystem.setBeaterForward(true);
    } else if (leftTriggerAxis > 0.5){
      m_intakeSubsystem.setBeaterOn(true);
      m_intakeSubsystem.setBeaterForward(false);
    } else {
      m_intakeSubsystem.setBeaterOn(false);
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
