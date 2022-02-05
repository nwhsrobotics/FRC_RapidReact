// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RunSparkSubsystem;

public class RunSparkCommand extends CommandBase {
  /** Creates a new RunSparkCommand. */
  private final RunSparkSubsystem m_sparkSubsystem;
  private double RUN_VEL = 70;
  
  public RunSparkCommand(RunSparkSubsystem subsystem) {
    m_sparkSubsystem = subsystem;
    addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    m_sparkSubsystem.setVelocity(RUN_VEL);
    System.out.println("Executed the Run Position Command");
    RUN_VEL = RUN_VEL + 20;
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
