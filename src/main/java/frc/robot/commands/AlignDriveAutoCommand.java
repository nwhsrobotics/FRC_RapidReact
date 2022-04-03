// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AlignDriveAutoCommand extends CommandBase {
  /** Creates a new AlignDriveAutoCommand. */

  private DriveSubsystem m_driveSubsystem;
  private static final int CLOCK_TIMEOUT = 200;

  private VisionSubsystem m_visionSubsystem;
  private double ball_center_x;
  private boolean m_done;
  private double m_iter = 0;
  private int m_clock;


  private final double VISION_AUTO_THRESHOLD = 0.04; //this is the vision constant for when the drive should finish. If higher than quit earlier

  public AlignDriveAutoCommand(DriveSubsystem driveSubsystem, VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem, visionSubsystem);
    m_driveSubsystem = driveSubsystem;
    m_visionSubsystem = visionSubsystem;

    SmartDashboard.putBoolean("Ball Align", false);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ball_center_x = m_visionSubsystem.getBallCenterX();
    /* only run this if the vision is giving us output that exists */
    
    
    double ball_center_x = m_visionSubsystem.getBallCenterX();
    if (ball_center_x >= 0 ){
      if (ball_center_x >= (0.5 + VISION_AUTO_THRESHOLD)){

          m_driveSubsystem.arcadeDrive(0.0, 0.15);

        SmartDashboard.putBoolean("Ball Align", false);
      } else if (ball_center_x <= (0.5 - VISION_AUTO_THRESHOLD)){
        
          m_driveSubsystem.arcadeDrive(0.0, -0.15);
   
        SmartDashboard.putBoolean("Ball Align", false);

      } 
    } else {
      
      m_driveSubsystem.arcadeDrive(0.0, 0.15);
      
    }
    m_clock += 1;

   
  }
    
    
    
    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if (m_clock >= CLOCK_TIMEOUT){
      return true;
    }

    if (Math.abs(ball_center_x - 0.5) <= 0.05){
      m_driveSubsystem.arcadeDrive(0.0, 0.0);
      SmartDashboard.putBoolean("Ball Align", true);
      return true;
    } else {
      return false;
    }

    

  }
}
