// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ToggleShooterAlignCommand extends CommandBase {
  private static final double VISION_AUTO_THRESHOLD = 0.06;
  private VisionSubsystem m_visionSubsystem;
  private double target_center_x;
  private DriveSubsystem m_driveSubsystem;

  /** Creates a new ToggleShooterAlignCommand. */
  public ToggleShooterAlignCommand(DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = driveSubsystem; 
    m_visionSubsystem = visionSubsystem;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    target_center_x = m_visionSubsystem.getGreenCenterX();
    /* only run this if the vision is giving us output that exists */
    
    
    if (target_center_x >= 0 ){
      if (target_center_x >= (0.5 + VISION_AUTO_THRESHOLD)){

          m_driveSubsystem.arcadeDrive(0.0, 0.14);

        SmartDashboard.putBoolean("Shooter Align", false);
      } else if (target_center_x   <= (0.5 - VISION_AUTO_THRESHOLD)){
        
          m_driveSubsystem.arcadeDrive(0.0, -0.14);
   
        SmartDashboard.putBoolean("Shooter Align", false);

      } 
    } else {
      
      m_driveSubsystem.arcadeDrive(0.0, -0.14);
      
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Shooter Align", true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
