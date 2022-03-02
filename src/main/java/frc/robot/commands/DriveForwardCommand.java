// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardCommand extends CommandBase {
  private boolean currentState;
  private DriveSubsystem m_DriveSubsystem;
  private final int DRIVE_THROTTLE_AXIS = 1;
  private final int DRIVE_TURN_AXIS = 4;
  private XboxController m_joy;
  private DriveSubsystem m_drive;

  /** Creates a new DriveForwardCommand. */
  public DriveForwardCommand(DriveSubsystem mSubsystem, XboxController joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_DriveSubsystem = mSubsystem;
    m_joy = joy;
    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_DriveSubsystem.setDrivePower(-m_joy.getRawAxis(DRIVE_THROTTLE_AXIS), m_joy.getRawAxis(DRIVE_TURN_AXIS));
    
    /**currentState = m_DriveSubsystem.getCurrentState();
    if(currentState == true){
      currentState = false;
    }
    else if (currentState == false){
      currentState = true;
    }
    m_DriveSubsystem.drive(currentState);*/
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
